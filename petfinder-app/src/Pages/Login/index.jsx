import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";
import React, { useState } from "react";
import HeaderBasic from "../../Components/HeaderBasic";
import "./login.css"
import api from "../../Api"
import VLibras from "@djpfs/react-vlibras"
import eye from "../../Images/eye.svg";
import eyeOff from "../../Images/eye-off.svg";

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
            localStorage.setItem("petfinder_user", JSON.stringify(res.data));

            if(res.data.nivelAcesso.toLowerCase() == "sysadm") {
                navigate("/dashboard-sysadmin")
            } else if (res.data.nivelAcesso.toLowerCase() == "adm") {
                navigate("/dashboard-admin")
            } else if (res.data.nivelAcesso.toLowerCase() == "chatops") {
                navigate("/dashboard-chatops")
            } else if (res.data.nivelAcesso.toLowerCase() == "user") {
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

    function changeEye() {
        var senha = document.getElementById("senha");
        var icon = document.getElementById("icon-eye")

        if (senha.type == "password") {
            senha.type = "text";
            icon.src = eyeOff;
        } else {
            senha.type = "password";
            icon.src = eye;
        }
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
                            <div className="login-senha-container">
                                <input
                                    id="senha"
                                    type="password"
                                    name="senha"
                                    required
                                    value={values.senha}
                                    onChange={handleChange}
                                />
                                <img src={eye} alt="" id="icon-eye" onClick={changeEye}/>
                            </div>
                        </div>

                        <div className="login-label-container">
                            {/* <a className="login-link-senha link" href="/">Esqueci a senha</a> */}
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

            <VLibras forceOnload={true}></VLibras>
        </>
    );
}

export default Login;