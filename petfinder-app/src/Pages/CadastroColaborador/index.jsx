import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import FloatResgate from "../../Components/FloatResgate";
import "./cadastro-colaborador.css";
import api from "../../Api"
import React from "react";
import VLibras from "@djpfs/react-vlibras"
import headerFunctions from "../../functions/headerFunctions";
import HeaderApp from "../../Components/HeaderApp";

function initialValuesUsuario() {
    return {
        nome: "",
        email: "",
        senha: "",
        nivelAcesso: ""
    }
}

function CadastroColaborador() {

    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));

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

    function handleSubmit(event) {
        event.preventDefault()
        let json = {
            usuario: {
                nome: valuesUsuario.nome,
                email: valuesUsuario.email,
                senha: valuesUsuario.senha,
                nivelAcesso: valuesUsuario.nivelAcesso,
                endereco: {
                    cep: valuesUsuarioInstituicao.endereco.cep,
                    rua: valuesUsuarioInstituicao.endereco.rua,
                    numero: valuesUsuarioInstituicao.endereco.num,
                    complemento: valuesUsuarioInstituicao.endereco.complemento,
                    bairro: valuesUsuarioInstituicao.endereco.bairro,
                    cidade: valuesUsuarioInstituicao.endereco.cidade,
                    uf: valuesUsuarioInstituicao.endereco.uf
                },
                instituicao: {
                    nome: valuesUsuarioInstituicao.fkInstituicao.nome,
                    telefone: valuesUsuarioInstituicao.fkInstituicao.telefone,
                    termoAdocao: null,
                    endereco: {
                        cep: valuesUsuarioInstituicao.fkInstituicao.endereco.cep,
                        rua: valuesUsuarioInstituicao.fkInstituicao.endereco.rua,
                        numero: valuesUsuarioInstituicao.fkInstituicao.endereco.num,
                        complemento: valuesUsuarioInstituicao.fkInstituicao.endereco.complemento,
                        bairro: valuesUsuarioInstituicao.fkInstituicao.endereco.bairro,
                        cidade: valuesUsuarioInstituicao.fkInstituicao.endereco.cidade,
                        uf: valuesUsuarioInstituicao.fkInstituicao.endereco.uf
                    }
                },
                logado: false
            },
            interesses: []
        }
        
        /*
        usuario: {
        },
    },
}
*/
        console.log(json)
        api.post("/usuarios", json, {
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

    useEffect(() => {
        if(valuesUsuario.nome == "" &&
            valuesUsuario.email == "" && valuesUsuario.senha == ""){
            api.get(`/usuarios/${idColaborador.id}`).then((res) => {
                setValuesUsuarioInstituicao(res.data)
                console.log(res.data)
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
            <HeaderApp
                    sideItens={headerFunctions.sideBarNivelAcesso(objUser.nivelAcesso)}
                    itens={headerFunctions.headerNivelAcesso(objUser.nivelnivelAcesso)}
            />
            
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
                                Finalizar
                            </button>
                        </div>
                    </div>

                </form>
            </div>


            <FloatResgate />

            <VLibras forceOnload={true}></VLibras>
        </>
    );
}

export default CadastroColaborador;