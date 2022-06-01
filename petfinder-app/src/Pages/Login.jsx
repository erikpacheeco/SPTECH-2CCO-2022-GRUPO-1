import HeaderBasic from "../Components/HeaderBasic";
import "../css/form.css"
import "../css/login.css"
import api from "../Api";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function resetValues() {
    return { email: "", senha: "" }
}

function Login() {
    // FAZER AUTENTICAÇÃO E REDIRECIONAMENTO DO USER POR NIVEL ACESSO
    const [values, setValues] = useState(resetValues)
    const navigate = useNavigate()

    function handleChange(event) {
        const { value, name } = event.target;
        setValues({ ...values, [name]: value, })
    }

    function handleClickRedirect(event){
        event.preventDefault()
        navigate("/cadastro")
    }


    function authLogin(event) {
        event.preventDefault();
        api.post("/usuarios/autenticacao", values,
            {
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        ).then((res) => {
            try {
                localStorage.setItem("petfinder_id_user",JSON.stringify(res.data))
                navigate("/home-user")
            } catch (error) {
                console.log(error)
            }
        })
    }

    return (
        <>
            <HeaderBasic />
            <div className="container">
                <div className="form-container">
                    <form onSubmit={authLogin}>
                        <h1>Login</h1>

                        {/* Inputs */}
                        <div className="input-container">
                            <label htmlFor="email">E-mail</label>
                            <input
                                id="email"
                                type="email"
                                name="email"
                                required
                                value={values.email}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="input-container">
                            <label htmlFor="senha">Senha</label>
                            <input
                                id="senha"
                                type="password"
                                name="senha"
                                required
                                value={values.senha}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="label-container">
                            <a className="link-senha link">Esqueci a senha</a>
                        </div>

                        {/* Buttons */}
                        <div className="button-container">
                            <button type="submit" id="login" className="btn-form">Login</button>
                            <button 
                                type="button" 
                                id="cadastro" 
                                className="btn-form"
                                onClick={handleClickRedirect}
                            >
                                Cadastre-se
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </>
    );
}

export default Login;