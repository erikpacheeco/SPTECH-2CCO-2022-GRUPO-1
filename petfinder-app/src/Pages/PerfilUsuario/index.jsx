import './perfil-usuario.css';
import axios from "axios";
import HeaderApp from "../../Components/HeaderApp";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import api from "../../Api";
import CardPet from "../../Components/CardPet";
import Swal from 'sweetalert2';
import withReactContent from "sweetalert2-react-content";

// imagens
import noPet from "../../Images/png_img/gatinhu.png";
import EditarIcon from "../../Images/edit-two.svg";

function PerfilUsuario() {

    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));
    const [pets, setPets] = useState([]);
    const idUsuario = useParams().id;
    const [infoPadrinho, setInfoPadrinho] = useState(
        {
            id: 0,
            nome: "",
            email: "",
            nivelAcesso: "",
            endereco: {
                id: 0,
                rua: "",
                num: "",
                complemento: "",
                bairro: "",
                cidade: "",
                uf: "",
                cep: "",
                latitude: "",
                longitude: ""
            },
            fkInstituicao: {
                id: 0,
                nome: "",
                telefone: "",
                termoAdocao: "",
                endereco: {
                    id: 0,
                    rua: "",
                    num: "",
                    complemento: "",
                    bairro: "",
                    cidade: "",
                    uf: "",
                    cep: "",
                    latitude: "",
                    longitude: ""
                }
            },
            logado: true
        }
    );
    const [editarInput, setEditarInput] = useState(true);
    const swal = withReactContent(Swal);

    const [preferencias, setPreferencias] = useState([]);

    const [nomeUser, setNomeUser] = useState("");
    const [emailUser, setEmailUser] = useState("");
    const [ruaUser, setRuaUser] = useState("");
    const [numUser, setNumUser] = useState("");
    const [complementoUser, setComplementoUser] = useState("");
    const [bairroUser, setBairroUser] = useState("");
    const [cidadeUser, setCidadeUser] = useState("");
    const [ufUser, setUfUser] = useState("");
    const [cepUser, setCepUser] = useState("");

    useEffect(() => {
       
            api.get(`/usuarios/${idUsuario}`).then((res) => {
                setInfoPadrinho(res.data);
                setNomeUser(res.data.nome);
                setEmailUser(res.data.email);
                setRuaUser(res.data.endereco.rua);
                setNumUser(res.data.endereco.num);
                setComplementoUser(res.data.endereco.complemento);
                setBairroUser(res.data.endereco.bairro);
                setCidadeUser(res.data.endereco.cidade);
                setUfUser(res.data.endereco.uf);
                setCepUser(res.data.endereco.cep);
            }).catch(error => { console.log(error) })

            api.get(`/pets/apadrinhamentos/usuario/${idUsuario}`).then(res => {
                if(res.status == 200) {
                    setPets(res.data);
                }
            }).catch(error => {console.log(error)})

            api.get(`/usuarios/interesse/${idUsuario}`).then((res) => {
                setPreferencias(res.data.map(caracteristica => caracteristica.caracteristica))
            }).catch(error => {console.log(error)})

            

    }, []);


    function handleChangeCep(event) {
        setCepUser(event.target.value)

        if(event.target.value.length === 8){
            axios.get(`https://viacep.com.br/ws/${event.target.value}/json/`).then((res) => {
                console.log(res.data);
                setRuaUser(res.data.logradouro)
            })
        }
    }
    
    function handleSubmitColaborador(event) {
        event.preventDefault();
        let json = {
            id: infoPadrinho.id,
            nome: nomeUser,
            email: emailUser,
            nivelAcesso: infoPadrinho.nivelAcesso,
            endereco: {
                id: infoPadrinho.endereco.id,
                rua: ruaUser,
                num: numUser,
                complemento: complementoUser,
                bairro: bairroUser,
                cidade: cidadeUser,
                uf: ufUser,
                cep: cepUser,
                latitude: infoPadrinho.endereco.latitude,
                longitude: infoPadrinho.endereco.longitude
            },
            fkInstituicao: null,
            logado: infoPadrinho.logado
        }

        console.log(json)

        api.put(`/usuarios/${idUsuario}`, json, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then((res) => {
                swal.fire({
                    icon: "success",
                    title: <h2>Usuário atualizado com sucesso!</h2>,
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

    function clicarEdicao(event) {
        event.preventDefault();

        if(!editarInput){ 
            {handleSubmitColaborador(event)} 
        }
        
        setEditarInput(!editarInput);
    }

    function verificarApadrinhado(pets, objUser) {
        const navigate = useNavigate()
        if (pets.length > 0) {
            return (
                <div className="perfil-usuario-box-lista">
                    <span>Pets que ajudei</span>
                    <div className="perfil-usuario-box-lista-pet">
                        {
                            pets.map((p, index) => (
                                <CardPet
                                    key={index}
                                    id={p.id}
                                    nome={p.nome}
                                    isDoente={p.isDoente}
                                    backgroundImage={p.caminhoImagem}
                                    onClick={() =>
                                        navigate(`/perfil-pet-usuario/${p.id}`)
                                    }>
                                </CardPet>
                            ))
                        }
                    </div>
                </div>
            )
        } else {
            return (
                <div className="perfil-usuario-box-Nolista">
                    <span>Ainda não ajudou algum pet</span>
                    <img src={noPet} alt="Gato triste" />
                    {objUser.nivelAcesso == "user" ? <button onClick={() => navigate("/home-user")}>Ajude Aqui!</button> : ""}
                </div>
            )
        }
    }

    function verificarUsuarioEditar() {
            return (
                editarInput ? 
                <button onClick={(event) => clicarEdicao(event)}>
                    <span>Editar</span>
                    <img src={EditarIcon} alt="" />
                </button>
                : 
                <button type="submit" id="salvar" onClick={(event) => clicarEdicao(event)}>
                    <span>Salvar</span>
                    <img src={EditarIcon} alt="" />
                </button>
            )
    }

    return (
        <>
            <HeaderApp />
            <div className="perfil-usuario-root">
                <div className="perfil-usuario-root-container">
                    <div className="perfil-usuario-box-titulo">
                        <span>{objUser.nivelAcesso == "user" ? "Meu Perfil" : "Perfil do Padrinho"}</span>
                    </div>
                    <div className="perfil-usuario-box">
                        <form>
                            <div className="perfil-usuario-card">
                                <div className="perfil-usuario-card-container">
                                    <div className="perfil-usuario-div-editar">
                                        <span className="perfil-usuario-card-titulo">Informações Pessoais</span>
                                        {objUser.nivelAcesso === "user" ? verificarUsuarioEditar() : ""}
                                    </div>
                                        <input
                                            id="nome"
                                            type="text"
                                            name="nome"
                                            value={nomeUser}
                                            onChange={(e) => setNomeUser(e.target.value)}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input90"
                                            disabled={editarInput}
                                        />
                                        <input
                                            id="email"
                                            type="text"
                                            name="email"
                                            value={emailUser}
                                            onChange={(e) => setEmailUser(e.target.value)}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input90"
                                            disabled={editarInput}
                                        />
                                </div>
                            </div>

                            <div className="perfil-usuario-card">
                                <div className="perfil-usuario-card-container">
                                    <span className="perfil-usuario-card-titulo">Endereço</span>
                                    <div className="perfil-usuario-div-input">
                                        <input
                                            id="rua"
                                            type="text"
                                            name="rua"
                                            value={ruaUser}
                                            onChange={(e) => setRuaUser(e.target.value)}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input70"
                                            disabled={editarInput}
                                        />
                                         <input
                                            id="num"
                                            type="text"
                                            name="num"
                                            value={numUser}
                                            onChange={(e) => setNumUser(e.target.value)}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input10"
                                            disabled={editarInput}
                                        />
                                        <input
                                            id="complemento"
                                            type="text"
                                            name="complemento"
                                            value={complementoUser !== null ? complementoUser : ""}
                                            onChange={(e) => setComplementoUser(e.target.value)}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input20"
                                            disabled={editarInput}
                                            placeholder="Complemento"
                                        />
                                    </div>
                                    <div className="perfil-usuario-div-input">
                                        <input
                                            id="cep"
                                            type="text"
                                            name="cep"
                                            value={cepUser}
                                            onChange={handleChangeCep}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input15"
                                            disabled={editarInput}
                                            maxLength={8}
                                        />
                                        <input
                                            id="bairro"
                                            type="text"
                                            name="bairro"
                                            value={bairroUser}
                                            onChange={(e) => setBairroUser(e.target.value)}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input35"
                                            disabled={editarInput}
                                        />
                                    </div>
                                    <div className="perfil-usuario-div-input">
                                        
                                        <input
                                            id="cidade"
                                            type="text"
                                            name="cidade"
                                            value={cidadeUser}
                                            onChange={(e) => setCidadeUser(e.target.value)}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input35"
                                            disabled={editarInput}
                                        />
                                        <input
                                            id="uf"
                                            type="text"
                                            name="uf"
                                            value={ufUser}
                                            onChange={(e) => setUfUser(e.target.value)}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input15"
                                            disabled={editarInput}
                                        />
                                    </div>
                                </div>
                            </div>

                            <div className="perfil-usuario-card">
                                <div className="perfil-usuario-card-container-caracteristica">
                                    <span className="perfil-usuario-card-titulo">Características</span>
                                    <div className="perfil-usuario-box-caracteristica">
                                        {
                                            preferencias.map((pref) => (
                                                <div key={`div-pref-${pref.id}`}>
                                                    <input
                                                        key={`input-pref-${pref.id}`}
                                                        className="cadastro-usuario-hide"
                                                        value={pref.caracteristica}
                                                        type="checkbox"
                                                        id={pref.id}
                                                    />
                                                    <button
                                                        key={`btn-pref-${pref.id}`}
                                                        type="button"
                                                        className="cadastro-usuario-btn-preferencia"
                                                        id={pref.id + "-btn"}
                                                    >
                                                        {pref.caracteristica}
                                                    </button>
                                                </div>
                                                
                                            ))
                                        }
                                    </div>
                                </div>
                            </div>
                        </form>

                        <div className="perfil-usuario-pets-apadrinhado">
                            <div className="perfil-usuario-pets-apadrinhado-container">
                                {verificarApadrinhado(pets, objUser)}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )

}

export default PerfilUsuario;