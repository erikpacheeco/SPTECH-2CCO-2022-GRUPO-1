import "./perfil.css";
import userIcon from "../../Images/png_img/user_icon.png";
import eye from "../../Images/eye.svg";
import eyeOff from "../../Images/eye-off.svg";
import api from "../../Api"
import React from "react";
import VLibras from "@djpfs/react-vlibras"
import HeaderApp from "../../Components/HeaderApp";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

function initialValuesUsuario() {
    return {
        nome: "",
        email: "",
        senha: ""
    }
} 

function PerfilSysAdmin() {

    const infoUsuario = JSON.parse(localStorage.getItem('petfinder_user'));   

    const [valuesUsuario, setValuesUsuario] = useState(initialValuesUsuario)

    const swal = withReactContent(Swal);
    const navigate = useNavigate();

    function handleChangeUser(event) {
        const { value, name } = event.target
        setValuesUsuario({ ...valuesUsuario, [name]: value, })
    }

    useEffect(() => {
        if(valuesUsuario.nome == "" && valuesUsuario.email == "" && valuesUsuario.senha == ""){
            api.get(`/usuarios/completo/${infoUsuario.id}`).then((res) => {
                // setInfoAdm(res.data)
                console.log(res.data)
                setValuesUsuario({nome:`${res.data.nome}`, email:`${res.data.email}`, senha:`${res.data.senha}`})
            })   
        }
    },[])

    function handleSubmitAdm(event) {
        event.preventDefault()
        let json = {
            nome: valuesUsuario.nome,
            email: valuesUsuario.email,
            senha: valuesUsuario.senha
        }
        console.log(json)
        api.put(`/usuarios/colaborador/${infoUsuario.id}`, json, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                swal.fire({
                    icon: "success",
                    title: <h1>Perfil atualizado com sucesso</h1>,
                }).then(() => {
                    navigate(`/perfil/${infoUsuario.id}`)
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

    return(
        <>
            <HeaderApp/>
            
            <div className="perfil-adm-container">
                <form className="perfil-adm-form-container">

                    <div className="perfil-adm-form">
                        <h1 className="perfil-adm-title">PERFIL</h1>
                        <div className="perfil-adm-userIcon">
                            <img src={userIcon} alt="" />
                        </div>
                        <div className="perfil-adm-input-container">
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

                        <div className="perfil-adm-input-container">
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

                        <div className="perfil-adm-input-container">
                            <label htmlFor="senha">Senha: </label>
                            <div className="perfil-adm-senha-container">
                                <input
                                    id="senha"
                                    name="senha"
                                    type="password"
                                    value={valuesUsuario.senha}
                                    onChange={handleChangeUser}
                                    required
                                />
                                <img src={eye} alt="" id="icon-eye" onClick={changeEye}/>
                            </div>
                        </div>

                        <div className="perfil-adm-button-container">
                            <button
                                type="submit"
                                className="perfil-adm-btn-form"
                                name="btnperfil"
                                onClick={handleSubmitAdm}
                            >
                                Salvar
                            </button>
                        </div>
                    </div>

                </form>
            </div>

            <VLibras forceOnload={true}></VLibras>
        </>
    )
}

export default PerfilSysAdmin;