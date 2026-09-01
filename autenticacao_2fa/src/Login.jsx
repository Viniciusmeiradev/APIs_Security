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
                localStorage("authToken", data.token);
                alert('Login efetuado com sucesso!');
                window.location.href('/menu');
            }
        }catch(error){
            setError(error.message);
        }finally{
            setLoading(false);
        }
    };

    //Envio do código
    const handle2FASubmit = async (e) =>
        e.preventDefault();
        setError('');
        setLoading(true);

        try{
            const resposta = await fetch('http://localhost:5000/api/verify-2fa',{
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    temp_Token: tempToken,
                    token_2fa: totpCode,
                }),
            });

            const data = await resposta.json();

            if (!resposta.ok){
                throw new Error(data.error || 'Código 2FA inválido!');
            }
            localStorage.setItem('authToken', data.token);
            alert('Autenticação de 2 fatores concluídas com sucesso!');
            window.location.href('/menu');
        }catch (error){
            setError(error.message);
        }finally{
            setLoading(false);
        }
    };

    return (
        <div className="login-container">
            <div className="login-card">
                <h2>{step === 'Credenciais' ? 'Acessar Sistema' : 'Confirmação em 2 Etapas'}</h2>
                {error && <div className="error-alert">{error}</div>}
                {step === 'Credenciais' ? (<form onSubmit={handleCredentialsSubmit}>
                    <div className="form-group">
                        <label>E-mail</label>
                        <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} require />
                    </div>

                    <div className="form-group">
                        <label>Senha</label>
                        <input type="password" value={senha} onChange={(e) => setSenha(e.target.value)} require/>
                    </div>

                    <button type="submit" disabled={loading}>{loading ? 'Verificando...' : 'Entrar'}</button>
                </form>
                ): (
                    <form onSubmit={handle2FASubmit}>
                        <p className="instrucao">Abra o seu aplicativo autenticador (Google Authenticator) e digite o código de 6 dígitos gerado para a sua conta.</p>
                        <div className="form-group">
                            <label>Código de 6 dígitos</label>
                            <input type="text" value={totpCode} onChange={(e) => setTotpCode(e.target.value)} maxLength={6} pattern="/d{6}" autoComplete="one-time-code" placeholder="000000" autoFocus required/>
                        </div>

                        <button type="submit" disabled={loading}>{loading ? 'Validando...' : 'Confirmar Código'}</button>
                        <button type="submit" className="back-btn" onClick={() => {setStep('Credenciais'); setTotpCode(''); setError('');}}>Voltar ao login</button>
                    </form>
                )}
            </div>
        </div>
    );
}
