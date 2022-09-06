import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";
import React, { useState } from "react";
import FloatResgate from "../../Components/FloatResgate";
import HeaderBasic from "../../Components/HeaderBasic";
import "./login.css"
import api from "../../Api"
import VLibras from "@djpfs/react-vlibras"

function resetValues() {
    return { email: "", senha: "" }
}

function Login() {
    const [values, setValues] = useState(resetValues)
    const navigate = useNavigate()
    const swal = withReactContent(Swal);

    function handleChange(event) {
        const { value, name } = event.target;
        setValues({ ...values, [name]: value, })
    }

    function handleClickRedirect(event) {
        event.preventDefault()
        navigate("/cadastro-user")
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
            localStorage.setItem("petfinder_user", JSON.stringify(res.data))

            if(res.data.nivelAcesso == "sysadm") {
                navigate("/dashboard-sysadmin")
            } else if (res.data.nivelAcesso == "adm") {
                navigate("/dashboard-admin")
            } else if (res.data.nivelAcesso == "chatops") {
                navigate("/dashboard-chatops")
            } else if (res.data.nivelAcesso == "user") {
                navigate("/home-user")
            }
            
        }) 
            .catch(error => {
                if (error.request.status == 401) {
                    console.log("success")
                    swal.fire({
                        icon: "error",
                        title: <h1>Ops... Dados inválidos :(</h1>,
                        text: "Por favor, tente novamente!"
                    });
                } else {
                    console.error("err")
                    swal.fire({
                        icon: "error",
                        title: <h1>Ops! Algo deu errado da nossa parte :(</h1>,
                        text: "Por favor, tente novamente!"
                    });
                }
            });
    }

    return (
        <>
            <HeaderBasic />
            <div className="login-container">
                <div className="login-form-container">
                    <form onSubmit={authLogin}>
                        <h1>Entrar</h1>

                        {/* Inputs */}
                        <div className="login-input-container">
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

                        <div className="login-input-container">
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

                        <div className="login-label-container">
                            <a className="login-link-senha link">Esqueci a senha</a>
                        </div>

                        {/* Buttons */}
                        <div className="login-button-container">
                            <button 
                                type="submit" 
                                id="login" 
                                className="login-btn-form"
                            >
                                Login
                            </button>
                            <button
                                type="button"
                                id="cadastro"
                                className="login-btn-form"
                                onClick={handleClickRedirect}
                            >
                                Cadastre-se
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <FloatResgate />

            <VLibras forceOnload={true}></VLibras>
        </>
    );
}

export default Login;