import './perfil-usuario.css';

import HeaderApp from "../../Components/HeaderApp";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import EditarIcon from "../../Images/edit-two.svg";

import noPet from "../../Images/png_img/gatinhu.png";
import api from "../../Api";
import CardPet from "../../Components/CardPet";
import Swal from 'sweetalert2';
import withReactContent from "sweetalert2-react-content";

// imagens
// import MedalhaNoBronzeIcon from "./../../Images/pet-friendly-No-bronze.svg";
// import MedalhaNoPrataIcon from "./../../Images/pet-friendly-No-prata.svg";
// import MedalhaNoOuroIcon from "./../../Images/pet-friendly-No-ouro.svg";
// import MedalhaBronzeIcon from "./../../Images/pet-friendly-bronze.svg";
// import MedalhaPrataIcon from "./../../Images/pet-friendly-prata.svg";
// import MedalhaOuroIcon from "./../../Images/pet-friendly-ouro.svg";

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
    // const navigate = useNavigate();
    const [editarInput, setEditarInput] = useState(true);
    const swal = withReactContent(Swal);

    useEffect(() => {
        if (objUser.nivelAcesso === "user") {
            setInfoPadrinho(objUser);
        } else {
            api.get(`/usuarios/${idUsuario}`).then((res) => {
                setInfoPadrinho(res.data);
            }).catch(error => { console.log(error) })
        }

        api.get(`/pets/apadrinhamentos/usuario/${infoPadrinho.id}`).then(res => {
            console.log(res.data);
            if(res.status === 200) {
                setPets(res.data);
            }
        }).catch(error => {console.log(error)})

    }, []);

    // const [values, setValues] = useState();

    // function resetValues(infoPadrinho) {
    //     setValues( {
    //             nome: infoPadrinho.nome, email: infoPadrinho.email, rua: infoPadrinho.endereco.rua, num: infoPadrinho.endereco.num, 
    //             complemento: infoPadrinho.endereco.complemento, bairro: infoPadrinho.endereco.bairro, cidade: infoPadrinho.endereco.cidade, 
    //             uf: infoPadrinho.endereco.uf, cep: infoPadrinho.endereco.cep}
    //         )
    // }

    console.log("testando agora");
    console.log(infoPadrinho.nome);

    function handleChange(event) {
        // const { value, name } = event.target;
        // setValues({ ...values, [name]: value, })
    }
    
    function handleSubmitColaborador(event) {
        event.preventDefault()
        let json = {
            id: infoPadrinho.id,
            nome: infoPadrinho.nome,
            email: infoPadrinho.email,
            nivelAcesso: infoPadrinho.cargo,
            endereco: {
                id: infoPadrinho.endereco.id,
                rua: infoPadrinho.endereco.rua,
                num: infoPadrinho.endereco.num,
                complemento: infoPadrinho.endereco.complemento,
                bairro: infoPadrinho.endereco.bairro,
                cidade: infoPadrinho.endereco.cidade,
                uf: infoPadrinho.endereco.uf,
                cep: infoPadrinho.endereco.cep,
                latitude: infoPadrinho.endereco.latitude,
                longitude: infoPadrinho.endereco.longitude
            },
            fkInstituicao: {
                id: infoPadrinho.fkInstituicao.id,
                nome: infoPadrinho.fkInstituicao.nome,
                telefone: infoPadrinho.fkInstituicao.telefone,
                termoAdocao: infoPadrinho.fkInstituicao.termoAdocao,
                endereco: {
                  id: infoPadrinho.fkInstituicao.endereco.id,
                  rua: infoPadrinho.fkInstituicao.endereco.rua,
                  num: infoPadrinho.fkInstituicao.endereco.num,
                  complemento: infoPadrinho.fkInstituicao.endereco.complemento,
                  bairro: infoPadrinho.fkInstituicao.endereco.bairro,
                  cidade: infoPadrinho.fkInstituicao.endereco.cidade,
                  uf: infoPadrinho.fkInstituicao.endereco.uf,
                  cep: infoPadrinho.fkInstituicao.endereco.cep,
                  latitude: infoPadrinho.fkInstituicao.endereco.latitude,
                  longitude: infoPadrinho.fkInstituicao.endereco.longitude
                }
              },
            logado: infoPadrinho.logado
        }
        api.put(`/usuarios/${infoPadrinho.id}`, json, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
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
            // {handleSubmitColaborador}
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
                    {objUser.nivelAcesso === "user" ? <button onClick={() => navigate("/home-user")}>Ajude Aqui!</button> : ""}
                </div>
            )
        }
    }

    function verificarUsuarioEditar(objUser) {
        if (objUser.nivelAcesso === "user") {
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
    }

    
    

    return (
        <>
            <HeaderApp />
            <div className="perfil-usuario-root">
                <div className="perfil-usuario-root-container">
                    <div className="perfil-usuario-box-titulo">
                        <span>{objUser.nivelAcesso === "user" ? "Meu Perfil" : "Perfil do Padrinho"}</span>
                    </div>
                    <div className="perfil-usuario-box">
                        <form>
                            <div className="perfil-usuario-card">
                                <div className="perfil-usuario-card-container">
                                    <div className="perfil-usuario-div-editar">
                                        <span className="perfil-usuario-card-titulo">Informação Pessoal</span>
                                        {verificarUsuarioEditar(objUser)}
                                    </div>
                                    <input
                                        id="nome"
                                        type="text"
                                        name="nome"
                                        value={infoPadrinho.nome}
                                        onChange={handleChange}
                                        className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input70"
                                        disabled={editarInput}
                                    />
                                    <input
                                        id="email"
                                        type="text"
                                        name="email"
                                        value={infoPadrinho.email}
                                        onChange={handleChange}
                                        className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input70"
                                        disabled={editarInput}
                                    />
                                </div>
                            </div>

                            <div className="perfil-usuario-card">
                                <div className="perfil-usuario-card-container">
                                    <span className="perfil-usuario-card-titulo">Endereço</span>
                                    <div>
                                        <input
                                            id="rua"
                                            type="text"
                                            name="rua"
                                            value={infoPadrinho.endereco.rua}
                                            onChange={handleChange}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input70"
                                            disabled={editarInput}
                                        />
                                    </div>
                                    <div>
                                        <input
                                            id="num"
                                            type="text"
                                            name="num"
                                            value={infoPadrinho.endereco.num}
                                            onChange={handleChange}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input10"
                                            disabled={editarInput}
                                        />
                                        {infoPadrinho.endereco.complemento !== null ? 
                                            <input
                                                id="num"
                                                type="text"
                                                name="num"
                                                value={infoPadrinho.endereco.complemento}
                                                onChange={handleChange}
                                                className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input20"
                                                disabled={editarInput}
                                            />
                                            :
                                            <input
                                                id="num"
                                                type="text"
                                                name="num"
                                                value=""
                                                onChange={handleChange}
                                                className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input20"
                                                disabled={editarInput}
                                                placeholder="Complemento"
                                            />

                                        }
                                        
                                        <input
                                            id="cep"
                                            type="text"
                                            name="cep"
                                            value={infoPadrinho.endereco.cep}
                                            onChange={handleChange}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input15"
                                            disabled={editarInput}
                                        />
                                    </div>
                                    <div>
                                        <input
                                            id="bairro"
                                            type="text"
                                            name="bairro"
                                            value={infoPadrinho.endereco.bairro}
                                            onChange={handleChange}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input35"
                                            disabled={editarInput}
                                        />
                                        <input
                                            id="cidade"
                                            type="text"
                                            name="cidade"
                                            value={infoPadrinho.endereco.cidade}
                                            onChange={handleChange}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input35"
                                            disabled={editarInput}
                                        />
                                        <input
                                            id="uf"
                                            type="text"
                                            name="uf"
                                            value={infoPadrinho.endereco.uf}
                                            onChange={handleChange}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input15"
                                            disabled={editarInput}
                                        />
                                    </div>
                                </div>
                            </div>

                            <div className="perfil-usuario-card">
                                <div className="perfil-usuario-card-container-pontuacao">
                                    <div className="perfil-usuario-box-dash">
                                        <span className="perfil-usuario-card-titulo">Pontuação</span>
                                    </div>
                                    <div className="perfil-usuario-box-pontuacao">
                                        <span>Total:</span>
                                        <span>Próxima Medalha: </span>
                                        <div>
                                            <img src="" alt="" />
                                        </div>
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