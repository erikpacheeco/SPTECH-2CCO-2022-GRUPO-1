import './editar-adm.css';
import HeaderApp from "../../Components/HeaderApp";
import React, { useEffect, useState } from "react";
import VLibras from "@djpfs/react-vlibras"
import { useParams } from 'react-router-dom'
import { useNavigate } from "react-router-dom";
import Swal from 'sweetalert2';
import withReactContent from "sweetalert2-react-content";
import api from '../../Api';
import headerFunctions from "../../functions/headerFunctions";

function resetValues() {
    return { nome: "" }
}

function EditarAdm() {

    const [infoAdm, setInfoAdm] = useState([])
    const [values, setValues] = useState(resetValues)

    const idAdm = useParams()
    const navigate = useNavigate()
    const swal = withReactContent(Swal);

    useEffect(() => {
        if(values.nome == ""){
            api.get(`/usuarios/${idAdm.id}`).then((res) => {
                setInfoAdm(res.data)
                setValues({nome:`${res.data.nome}`})
            })   
        }
    },[])
    console.log(infoAdm);

    function handleChange(event) {
        const { value, name } = event.target;
        setValues({ ...values, [name]: value, })
    }

    function handleSubmitAdm(event) {
        event.preventDefault()
        let json = {
            id: infoAdm.id,
            nome: values.nome,
            email: infoAdm.email,
            nivelAcesso: infoAdm.nivelAcesso,
            endereco: infoAdm.endereco,
            fkInstituicao: infoAdm.fkInstituicao,
            logado: infoAdm.logado
        }
        api.put(`/usuarios/${infoAdm.id}`, json, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                swal.fire({
                    icon: "success",
                    title: <h2>Usuário atualizado com sucesso!</h2>,
                }).then(() => {
                    navigate(`/lista-adm/${infoAdm.id}`)
                })
            }).catch((error) => {
                swal.fire({
                    icon: "error",
                    title: <h2>Ops! Algo deu errado da nossa parte :(</h2>,
                    text: "Por favor, tente novamente!"
                });
                console.log(error)
            })
    }

    return(
        <>
            <HeaderApp/>
            
            <div className="editar-adm-container">
                <div className="editar-adm-form-container">
                    <form>
                        <div className="editar-adm-form-titulo">
                            <h1>Editar Administrador</h1>
                        </div>

                        <div className="editar-adm-input-container">
                            <div className="editar-adm-input">
                                <label htmlFor="text">Nome Completo</label>
                                <input
                                    id="nome"
                                    type="text"
                                    name="nome"
                                    value={values.nome}
                                    onChange={handleChange}
                                />

                                <div className="editar-adm-button-container">
                                    <button type="submit" id="salvar" className="editar-adm-btn-form" onClick={handleSubmitAdm}>Salvar</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <VLibras forceOnload={true}></VLibras>
        </>
    )
}

export default EditarAdm;