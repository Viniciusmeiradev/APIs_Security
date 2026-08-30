const express = require('express');
const cors = require ('cors');
const {login, verify2FA} = require('./authcontroller');

const app = express();
app.use(cors());
app.use(express.json());

app.post('/api/login', login);
app.post('/api/verify2FA', verify2FA);

const PORT = 5000;
app.listen(PORT,() => {
    console.log(`Servidor rodando na porta ${PORT}`);
});