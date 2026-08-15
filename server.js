require('dotenv').config();

const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const axios = require('axios');
const SECRET_KEY = process.env.RECAPTCHA_SECRET_KEY;

//configurar o ejs como motor de visualização
app.set('view engine', 'ejs');


//processa formulario
app.use(bodyParser.urlencoded({extended: true}));


//tornar público os arquivos
app.use(express.static(__dirname + '/public'));


//Rota para exibir formulario
app.get('/', (req, res) => {
    res.render('login');
});


//Rota de login com reCAPTCHA
app.post('/login', async(req, res) => {
    const{username, password, recaptchaToken} = req.body;

    //Verificar token reCAPTCHA no Google
    const response = await axios.post(`https://www.google.com/recaptcha/api/siteverify`, null,
        { params:{secret: SECRET_KEY, response: recaptchaToken
        }
    }
);
const {success, score} = response.data;
if(!success || score < 0.5) {
    return res.send('Falha na verificação reCAPTCHA. Tente Novamente.');
}
res.send(`Login Bem-Sucedido! Bem-Vindo, ${username}!`);
});

//Iniciar o servidor
app.listen(3000, () => console.log('Servidor rodando em http://localhost:3000'));
