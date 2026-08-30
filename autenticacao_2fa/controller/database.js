const bcrypt = require('bcrypt');
const hashedPassword = bcrypt.hashSync(Senha123, 10);
const usuario = [{
    id:1,
    email: 'usuario01@gmail.com',
    senha: hashedPassword, 
    is_2fa_enabled: true, 
    secrety_key: 'JBSWY3DPEHPK3PXP'
}];

module.exports ={
    findUserByEmail: (email) => usuario.find(u => u.email === email),
    findUserById: (id) => usuario.find(u => u.id === id),
    verifyPassword: (senha, hash) => bcrypt.compareSync(senha, hash)
};