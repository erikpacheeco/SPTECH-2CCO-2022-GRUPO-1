import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import "./cadastro-instituicao.css";
import api from "../../Api"
import React from "react";
import VLibras from "@djpfs/react-vlibras";
import HeaderApp from "../../Components/HeaderApp";

function initialValuesInstituicao() {
    return {
        nome: "",
        telefone: ""
    }
}

function initialValuesEndereco() {
    return {
        rua: "",
        complemento: "",
        numero: "",
        bairro: "",
        cidade: "",
        uf: "",
        cep: ""
    }
}
    
function initialValuesUsuario() {
    return {
        nome: "",
        email: "",
        senha: "",
        nivelAcesso: "adm",
    }
}

function CadastroInstituicao() {

    const [valuesInstituicao, setValuesInstituicao] = useState(initialValuesInstituicao)
    const [valuesEndereco, setValuesEndereco] = useState(initialValuesEndereco)
    const [valuesUsuario, setValuesUsuario] = useState(initialValuesUsuario)

    const [formInstituicao, setFormInstituicao] = useState(true);
    const [formEndereco, setFormEndereco] = useState(false)
    const [formUsuario, setFormUsuario] = useState(false)

    const [idUltimoUsuario, setIdUltimoUsuario] = useState()
    const [idUltimoCliente, setIdUltimoCliente] = useState()

    const swal = withReactContent(Swal);
    const navigate = useNavigate();

    function handleChangeInstituicao(event) {
        const { value, name } = event.target
        setValuesInstituicao({ ...valuesInstituicao, [name]: value, })
    }

    function handleChangeEndereco(event) {
        const { value, name } = event.target
        setValuesEndereco({ ...valuesEndereco, [name]: value, })
    }

    function handleChangeUser(event) {
        const { value, name } = event.target
        setValuesUsuario({ ...valuesUsuario, [name]: value, })
    }

    function handleSubmit(event) {
        //console.log(valuesUsuario)
        //console.log(valuesInstituicao)
        //console.log(valuesEndereco)
        event.preventDefault()
        let json = {
            nome: valuesUsuario.nome,
            email: valuesUsuario.email,
            senha: valuesUsuario.senha,
            instituicao: {
                nome: valuesInstituicao.nome,
                telefone: valuesInstituicao.telefone,
                endereco: {
                    cep: valuesEndereco.cep,
                    rua: valuesEndereco.rua,
                    numero: valuesEndereco.numero,
                    complemento: valuesEndereco.complemento,
                    bairro: valuesEndereco.bairro,
                    cidade: valuesEndereco.cidade,
                    uf: valuesEndereco.uf
                }
            }
        }
        console.log(json)
        api.post("/instituicoes", json, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                swal.fire({
                    icon: "success",
                    title: <h1>Você será redirecionado para o login</h1>,
                }).then(() => {
                    navigate("/")
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

        api.get("/usuarios/ultimo-usuario-cadastrado").then((res) => {
            setIdUltimoUsuario(res.data)
            console.log("ultimo cadastro usuario "+res.data)
        })

        api.get("/usuarios/ultimo-cliente").then((res) => {
            setIdUltimoCliente(res.data)
            console.log("ultimo cadastro cliente "+res.data)
        })

    }, [])

    function handleChangeInstituicaoEndereco() {
        setFormEndereco(true);
        setFormInstituicao(false);
    }

    function handleChangeEnderecoInstituicao() {
        setFormEndereco(false);
        setFormInstituicao(true);
    }

    function handleChangeInstituicaoUsuario() {
        setFormUsuario(true);
        setFormInstituicao(false);
    }

    function handleChangeUsuarioInstituicao() {
        setFormUsuario(false);
        setFormInstituicao(true);
    }

    function handleChangeEnderecoUsuario() {
        setFormEndereco(false);
        setFormUsuario(true);
    }

    function handleChangeUsuarioEndereco() {
        setFormEndereco(true);
        setFormUsuario(false);
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
            
            <div className="cadastro-instituicao-container">
                <form className="cadastro-instituicao-form-container" onSubmit={handleSubmit}>

                    <div className={formInstituicao ? ("cadastro-instituicao-form") : ("cadastro-instituicao-form cadastro-instituicao-hide")}>
                        <div className="cadastro-instituicao-btn">
                            <button type="button" className="cadastro-instituicao-btn-paginas-selected"></button>
                            <button type="button" className="cadastro-instituicao-btn-paginas" onClick={handleChangeInstituicaoEndereco}></button>
                            <button type="button" className="cadastro-instituicao-btn-paginas" onClick={handleChangeInstituicaoUsuario}></button>
                        </div>

                        <h1 className="cadastro-instituicao-title">CADASTRO DA INSTITUIÇÃO</h1>
                        <div className="cadastro-instituicao-input-container">
                            <label htmlFor="nome">Nome instituicao: </label>
                            <input
                                id="nomeInstituicao"
                                name="nome"
                                type="text"
                                value={valuesInstituicao.nome}
                                onChange={handleChangeInstituicao}
                                required
                            />
                        </div>

                        <div className="cadastro-instituicao-input-container">
                            <label htmlFor="telefone">Telefone para contato: </label>
                            <input
                                id="telefone"
                                name="telefone"
                                type="text"
                                value={valuesUsuario.telefone}
                                onChange={handleChangeInstituicao}
                                required
                            />
                        </div>

                        <div className="cadastro-instituicao-button-container">
                            <button
                                type="button"
                                className="cadastro-instituicao-btn-form"
                                name="btnProximo"
                                onClick={handleChangeInstituicaoEndereco}
                            >
                                Próximo
                            </button>
                        </div>
                    </div>

                    <div className={formEndereco ? ("cadastro-instituicao-form") : ("cadastro-instituicao-form cadastro-instituicao-hide")}>
                        <div className="cadastro-instituicao-btn">
                            <button type="button" className="cadastro-instituicao-btn-paginas" onClick={handleChangeEnderecoInstituicao}></button>
                            <button type="button" className="cadastro-instituicao-btn-paginas-selected"></button>
                            <button type="button" className="cadastro-instituicao-btn-paginas" onClick={handleChangeEnderecoUsuario}></button>
                        </div>

                        <h1 className="cadastro-instituicao-title">ENDEREÇO</h1>
                        <div className="cadastro-instituicao-input-container">
                            <label html="cep">CEP: </label>
                            <input
                                id="cep"
                                name="cep"
                                type="text"
                                value={valuesEndereco.cep}
                                maxLength={8}
                                minLength={8}
                                pattern="[0-9]+"
                                required
                                onChange={handleChangeEndereco}
                            />
                        </div>

                        <div className="cadastro-instituicao-input-container">
                            <label html="rua">Rua: </label>
                            <input
                                id="rua"
                                name="rua"
                                type="text"
                                value={valuesEndereco.rua}
                                required
                                onChange={handleChangeEndereco}
                            />
                        </div>

                        <div className="cadastro-instituicao-separate-container">
                            <div className="cadastro-instituicao-input-container cadastro-instituicao-20">
                                <label html="numero">Número: </label>
                                <input
                                    id="numero"
                                    name="numero"
                                    type="text"
                                    value={valuesEndereco.numero}
                                    required
                                    onChange={handleChangeEndereco}
                                />
                            </div>

                            <div className="cadastro-instituicao-input-container cadastro-instituicao-70">
                                <label html="">Complemento: </label>
                                <input
                                    id="complemento"
                                    type="text"
                                    name="complemento"
                                    value={valuesEndereco.complemento}
                                    onChange={handleChangeEndereco} />
                            </div>
                        </div>

                        <div className="cadastro-instituicao-input-container">
                            <label html="bairro">Bairro: </label>
                            <input
                                id="bairro"
                                name="bairro"
                                type="text"
                                value={valuesEndereco.bairro}
                                onChange={handleChangeEndereco}
                                required
                            />
                        </div>

                        <div className="cadastro-instituicao-separate-container">
                            <div className="cadastro-instituicao-input-container cadastro-instituicao-70">
                                <label html="cidade">Cidade: </label>
                                <input
                                    id="cidade"
                                    name="cidade"
                                    type="text"
                                    value={valuesEndereco.cidade}
                                    required
                                    onChange={handleChangeEndereco}
                                />
                            </div>

                            <div className="cadastro-instituicao-input-container cadastro-instituicao-20">
                                <label html="estado">UF: </label>
                                <input
                                    id="estado"
                                    name="uf"
                                    minLength={2}
                                    maxLength={2}
                                    type="text"
                                    value={valuesEndereco.uf}
                                    required
                                    onChange={handleChangeEndereco}
                                />
                            </div>
                        </div>

                        <div className="cadastro-instituicao-button-container">
                            <button
                                type="button"
                                className="cadastro-instituicao-btn-form"
                                name="btnVoltar"
                                onClick={handleChangeEnderecoInstituicao}
                            >
                                Voltar
                            </button>


                            <button
                                type="button"
                                className="cadastro-instituicao-btn-form"
                                name="btnProximo"
                                onClick={handleChangeEnderecoUsuario}
                            >
                                Proximo
                            </button>

                        </div>
                    </div>

                    <div className={formUsuario ? ("cadastro-instituicao-form") : ("cadastro-instituicao-form cadastro-instituicao-hide")}>
                        <div className="cadastro-instituicao-btn">
                            <button type="button" className="cadastro-instituicao-btn-paginas" onClick={handleChangeUsuarioInstituicao}></button>
                            <button type="button" className="cadastro-instituicao-btn-paginas" onClick={handleChangeUsuarioEndereco}></button>
                            <button type="button" className="cadastro-instituicao-btn-paginas-selected"></button>
                        </div>

                        <h1 className="cadastro-instituicao-title">CADASTRO DO USUÁRIO ADMINISTRADOR DA INSTITUIÇÃO</h1>
                        <div className="cadastro-instituicao-input-container">
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

                        <div className="cadastro-instituicao-input-container">
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

                        <div className="cadastro-instituicao-input-container">
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

                        <div className="cadastro-instituicao-button-container">

                            <button
                                type="button"
                                className="cadastro-instituicao-btn-form"
                                name="btnVoltar"
                                onClick={handleChangeUsuarioEndereco}
                            >
                                Voltar
                            </button>

                            <button
                                type="submit"
                                className="cadastro-instituicao-btn-form"
                                name="btnCadastro"
                                onClick={addingNewCliente}
                            >
                                Finalizar
                            </button>
                        </div>
                    </div>

                </form>
            </div>

            <VLibras forceOnload={true}></VLibras>
        </>
    );
}

export default CadastroInstituicao;