import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import "./cadastro-colaborador.css";
import api from "../../Api"
import React from "react";
import VLibras from "@djpfs/react-vlibras";
import HeaderApp from "../../Components/HeaderApp";

function initialValuesUsuario() {
    return {
        nome: "",
        email: "",
        senha: "",
        nivelAcesso: "adm"
    }
}

function CadastroColaborador() {

    const [valuesUsuario, setValuesUsuario] = useState(initialValuesUsuario)
    const [idUltimoUsuario, setIdUltimoUsuario] = useState()
    const [idUltimoCliente, setIdUltimoCliente] = useState()
    const [valuesUsuarioInstituicao, setValuesUsuarioInstituicao] = useState()

    const idColaborador = useParams()
    const swal = withReactContent(Swal);
    const navigate = useNavigate();

    function handleChangeUser(event) {
        const { value, name } = event.target
        setValuesUsuario({ ...valuesUsuario, [name]: value, })
    }
    
    useEffect(() => {
        if(valuesUsuario.nome == "" &&
        valuesUsuario.email == "" && valuesUsuario.senha == ""){
            api.get(`/usuarios/${idColaborador.id}`).then((res) => {
                setValuesUsuarioInstituicao(res.data)
            })   
        }
        
        api.get("/usuarios/ultimo-usuario-cadastrado").then((res) => {
            setIdUltimoUsuario(res.data)
            console.log("ultimo cadastro "+res.data)
        })

        api.get("/usuarios/ultimo-cliente").then((res) => {
            setIdUltimoCliente(res.data)
            console.log("ultimo cliente "+res.data)
        })
        
    }, [])

    function handleSubmit(event) {
        event.preventDefault()
        let json = {
            nome: valuesUsuario.nome,
            email: valuesUsuario.email,
            senha: valuesUsuario.senha,
            cargo: valuesUsuario.nivelAcesso,
            instituicaoId: valuesUsuarioInstituicao.fkInstituicao.id
        }
        console.log(json)
        api.post("/usuarios/colaborador", json, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                swal.fire({
                    icon: "success",
                    title: <h1>Novo colaborador cadastrado com sucesso</h1>,
                }).then(() => {
                    navigate(`/lista-colaborador/${idColaborador}`)
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

    function addingNewCliente() {
        let cliente = {
            id: idUltimoCliente+1,
            usuarioId: idUltimoUsuario+1,
            tipo: "adm",
            dataCliente: new Date().toISOString()
        }

        api.post('/usuarios/cliente', cliente, { headers: { 'Content-Type': 'application/json' } })
    }

    return (
        <>
            <HeaderApp/>
            
            <div className="cadastro-colaborador-container">
                <form className="cadastro-colaborador-form-container" onSubmit={handleSubmit}>

                    <div className="cadastro-colaborador-form">
                        <h1 className="cadastro-colaborador-title">CADASTRO COLABORADOR</h1>
                        <div className="cadastro-colaborador-input-container">
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

                        <div className="cadastro-colaborador-input-container">
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

                        <div className="cadastro-colaborador-input-container">
                            <label htmlFor="nivelAcesso">Cargo: </label>
                            <select  id="nivelAcesso" name="nivelAcesso" value={valuesUsuario.nivelAcesso} onChange={handleChangeUser}>
                                <option value="adm">Adminstrador</option>
                                <option value="chatops">ChatOps</option>    
                            </select>
                        </div>

                        <div className="cadastro-colaborador-input-container">
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

                        <div className="cadastro-colaborador-button-container">
                            <button
                                type="submit"
                                className="cadastro-colaborador-btn-form"
                                name="btnCadastro"
                                onClick={addingNewCliente}
                            >
                                Cadastrar
                            </button>
                        </div>
                    </div>

                </form>
            </div>

            <VLibras forceOnload={true}></VLibras>
        </>
    );
}

export default CadastroColaborador;