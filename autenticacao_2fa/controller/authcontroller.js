const jwt = require('jsonwebtoken');
const speakeasy = require('speakeasy');
const {findUserByEmail, findUserById, verifyPassword} = require('./database');
const JWT_SECRET = 'chave do web token';

//Validação de email e senha
exports.login = (req, res) =>{
    const {email, senha} = req.body;
    const usuario = findUserByEmail(email);

    if(!usuario || !verifyPassword(senha, usuario.senha)) {
        return res.status(401).json({error: 'E-mail ou senha inválidos.'});
    }
    //2FA ativado, token disponivel por 2 minutos   
    if(usuario.is_2fa_enabled){
        const tempToken = jwt.sign({ usuarioId: usuario.id, type: '2fa_pending'}, JWT_SECRET, {expiresIn: '2m'});
        return res.json({
            requires_2fa: true,
            temp_token: tempToken
        });
    }

    //2FA desativado, token fixo 
    const authToken = jwt.sign({usuarioId: usuario.id}, JWT_SECRET, {expiresIn: '1h'});
    return res.json({
        requires_2fa: false,
        token: authToken
    });
};

//Validacao no 2FA aplicativo
exports.verify2FA = (req, res) =>{
    const {temp_token, token_2fa} = req.body;
    if(!temp_token || !token_2fa){
        return res.status(400).json({error: 'Parâmetros insuficientes!'});
    } 

    try{
        //Decodifica e valida o token temporario
        const decode = jwt.verify(temp_token, JWT_SECRET);
        if(decode.type !== '2fa_pending'){
            return res.status(401).json({error: 'Token inválido!'});
        }

        const usuario = findUserById(decode.usuarioId);
        if(!usuario){
            return res.status(404).json({error: 'Usuario não encontrado!'});
        }

        //confere no terminal se o codigo bate
        const codigoServidor = speakeasy.totp({
            secret: usuario.secret_key,
            encoding: 'base32'
        });
        console.log('Código digitado:', token_2fa);
        console.log('Código esperado pelo servidor:', codigoServidor);


        //Valida o código de 6 digitos
        const verifica = speakeasy.totp.verify({
            secret: usuario.secret_key, 
            encoding: 'base32',
            token: token_2fa,
            window: 1 
        });
        if (!verifica){
            return res.status(400).json({error: 'Código 2FA incorreto ou expirado.'});
        }

        //2FA validado
        const authToken = jwt.sign({usuarioId: usuario.id}, JWT_SECRET, {expiresIn: '1h'});
        return res.json({success:true, token: authToken});
    }catch(error){
        return res.status(401).json({error: 'Sessão expirada. Faça login novamente!'});
    }
};
