import './EditarColaborador.css';
import HeaderApp from "../../Components/HeaderApp";
import React, { useEffect, useState } from "react";
import VLibras from "@djpfs/react-vlibras"
import { useParams } from 'react-router-dom'
import { useNavigate } from "react-router-dom";
import Swal from 'sweetalert2';
import withReactContent from "sweetalert2-react-content";
import api from '../../Api';

function resetValues() {
    return { nome: "", cargo: "adm" }
}

function EditarColaborador() {
    const [infoColaborador, setInfoColaborador] = useState([])
    const [values, setValues] = useState(resetValues)

    const idColaborador = useParams()
    const navigate = useNavigate()
    const swal = withReactContent(Swal);

    useEffect(() => {
        if(values.nome == "" && values.cargo == ""){
            api.get(`/usuarios/${idColaborador.id}`).then((res) => {
                setInfoColaborador(res.data)
                setValues({nome:`${res.data.nome}`, cargo:`${res.data.nivelAcesso}`})
            })   
        }
    },[])

    function handleChange(event) {
        const { value, name } = event.target;
        setValues({ ...values, [name]: value, })
    }
    
    function handleSubmitColaborador(event) {
        event.preventDefault()
        let json = {
            id: infoColaborador.id,
            nome: values.nome,
            email: infoColaborador.email,
            nivelAcesso: values.cargo,
            endereco: {
                id: null,
                rua: null,
                num: null,
                complemento: null,
                bairro: null,
                cidade: null,
                uf: null,
                cep: null,
                latitude: null,
                longitude: null
            },
            fkInstituicao: {
                id: infoColaborador.fkInstituicao.id,
                nome: infoColaborador.fkInstituicao.nome,
                telefone: infoColaborador.fkInstituicao.telefone,
                termoAdocao: infoColaborador.fkInstituicao.termoAdocao,
                endereco: {
                  id: infoColaborador.fkInstituicao.endereco.id,
                  rua: infoColaborador.fkInstituicao.endereco.rua,
                  num: infoColaborador.fkInstituicao.endereco.num,
                  complemento: infoColaborador.fkInstituicao.endereco.complemento,
                  bairro: infoColaborador.fkInstituicao.endereco.bairro,
                  cidade: infoColaborador.fkInstituicao.endereco.cidade,
                  uf: infoColaborador.fkInstituicao.endereco.uf,
                  cep: infoColaborador.fkInstituicao.endereco.cep,
                  latitude: infoColaborador.fkInstituicao.endereco.latitude,
                  longitude: infoColaborador.fkInstituicao.endereco.longitude
                }
              },
            logado: infoColaborador.logado
        }
        api.put(`/usuarios/${infoColaborador.id}`, json, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                swal.fire({
                    icon: "success",
                    title: <h2>Usuário atualizado com sucesso!</h2>,
                }).then(() => {
                    navigate(`/lista-colaborador/${infoColaborador.id}`)
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
            <div className="editar-colaborador-container">
                <div className="editar-colaborador-form-container">
                    <form>
                        <div className="editar-colaborador-form-titulo">
                            <h1>Editar Colaborador</h1>
                        </div>

                        <div className="editar-colaborador-input-container">
                            <div className="editar-colaborador-input">
                                <label htmlFor="text">Nome Completo</label>
                                <input
                                    id="nome"
                                    type="text"
                                    name="nome"
                                    value={values.nome}
                                    onChange={handleChange}
                                />

                                <div className="editar-colaborador-input">
                                    <label htmlFor="text">Cargo</label>
                                    <select  id="cargo" name="cargo" value={values.cargo} onChange={handleChange}>
                                        <option value="adm">Adminstrador</option>
                                        <option value="chatops">ChatOps</option>    
                                    </select>
                                </div>

                                <div className="editar-colaborador-button-container">
                                    <button type="submit" id="salvar" className="editar-colaborador-btn-form" onClick={handleSubmitColaborador}>Salvar</button>
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

export default EditarColaborador;