import React, {useState} from 'react';

export default function Login(){
    const [step, setStep] = useState('Credenciais');
    const [email, setEmail] = useState('usuario01@gmail.com');
    const [senha, setSenha] = useState('senha123');
    const [tempToken, setTempToken] = useState('');
    const [totpCode, setTotpCode] = useState('');
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);

    //Envio de Credenciais
    const handleCredentialsSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setLoading(true);

        try{
            const resposta = await fetch('http://localhost:5000/api/login', {
                method: 'POST',
                headers:{'Content-Type':'application/json'},
                body:JSON.stringify({email, senha}),
            });
        
            const data = await resposta.json();

            if (!resposta.ok){
                throw new Error(data.error || 'Erro ao realizar login.');
            }

            if(data.require_2fa){
                setTempToken(data.temp_Token);
                setStep('2 FATORES'); //Inserção 2fa
            }else{
                
            }
        }
    }
}
