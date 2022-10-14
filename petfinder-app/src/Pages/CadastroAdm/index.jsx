import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import "./cadastro-adm.css";
import api from "../../Api"
import React from "react";
import VLibras from "@djpfs/react-vlibras"
import headerFunctions from "../../functions/headerFunctions";
import HeaderApp from "../../Components/HeaderApp";

function initialValuesUsuario() {
    return {
        nome: "",
        email: "",
        senha: ""
    }
}

function CadastroAdm() {

    const [valuesUsuario, setValuesUsuario] = useState(initialValuesUsuario)

    const idAdm = useParams()
    const swal = withReactContent(Swal);
    const navigate = useNavigate();

    function handleChangeUser(event) {
        const { value, name } = event.target
        setValuesUsuario({ ...valuesUsuario, [name]: value, })
    }

    function handleSubmit(event) {
        event.preventDefault()
        let json = {
            nome: valuesUsuario.nome,
            email: valuesUsuario.email,
            senha: valuesUsuario.senha
        }
        console.log(json)
        api.post("/usuarios/sysadm", json, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                swal.fire({
                    icon: "success",
                    title: <h1>Novo administrador cadastrado com sucesso</h1>,
                }).then(() => {
                    navigate(`/lista-adm/${idAdm}`)
                })
            }).catch((error) => {
                swal.fire({
                    icon: "error",
                    title: <h1>Ops! Algo deu errado da nossa parte :(</h1>,
                    text: "Por favor, tente novamente!"
                });
                console.log(error)
            })
    }

    return(
        <>
            <HeaderApp/>
            
            <div className="cadastro-adm-container">
                <form className="cadastro-adm-form-container" onSubmit={handleSubmit}>

                    <div className="cadastro-adm-form">
                        <h1 className="cadastro-adm-title">CADASTRO ADMINISTRADOR</h1>
                        <div className="cadastro-adm-input-container">
                            <label htmlFor="nome">Nome completo: </label>
                            <input
                                id="nome"
                                name="nome"
                                type="text"
                                value={valuesUsuario.nome}
                                onChange={handleChangeUser}
                                required
                            />
                        </div>

                        <div className="cadastro-adm-input-container">
                            <label htmlFor="email">E-mail: </label>
                            <input
                                id="email"
                                name="email"
                                type="email"
                                value={valuesUsuario.email}
                                onChange={handleChangeUser}
                                required
                            />
                        </div>

                        <div className="cadastro-adm-input-container">
                            <label htmlFor="senha">Senha: </label>
                            <input
                                id="senha"
                                name="senha"
                                type="password"
                                value={valuesUsuario.senha}
                                onChange={handleChangeUser}
                                required
                            />
                        </div>

                        <div className="cadastro-adm-button-container">
                            <button
                                type="submit"
                                className="cadastro-adm-btn-form"
                                name="btnCadastro"
                            >
                                Cadastrar
                            </button>
                        </div>
                    </div>

                </form>
            </div>

            <VLibras forceOnload={true}></VLibras>
        </>
    )
}

export default CadastroAdm;