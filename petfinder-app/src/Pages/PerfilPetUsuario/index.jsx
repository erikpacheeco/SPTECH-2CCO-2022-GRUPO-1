import './PerfilPetUsuario.css';
import HeaderApp from "../../Components/HeaderApp";
import { useEffect, useState } from "react";
import api from "../../Api"
import React from "react";
import { useParams } from 'react-router-dom'
import VLibras from "@djpfs/react-vlibras"
import { useNavigate } from "react-router-dom";
import Swal from 'sweetalert2';
import withReactContent from "sweetalert2-react-content";

function initialValuesDemanda() {
    return {
        categoria: "",
        fkUsuario: "",
        fkIntituicao: "",
        fkPet: ""
    }
}

function PerfilPetUsuario() {

    const [infoPet, setInfoPet] = useState([])
    const [preferencias, setPreferencias] = useState([])

    const infoUsuario = JSON.parse(localStorage.getItem('petfinder_user'));

    const idPet = useParams()
    const navigate = useNavigate()
    const swal = withReactContent(Swal);

    function handleSubmitPagamento(event) {
        event.preventDefault()
        let json = {
            categoria: "PAGAMENTO",
            fkUsuario: infoUsuario.id,
            fkIntituicao: infoPet.id,
            fkPet: infoPet.id
        }
        console.log(json)
        api.post("/demandas", json, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                swal.fire({
                    icon: "success",
                    title: <h2>Agora mande uma mensagem para a instituição e tire as suas dúvidas</h2>,
                }).then(() => {
                    navigate("/chat")
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

    function handleSubmitAdocao(event) {
        event.preventDefault()
        let json = {
            categoria: "ADOCAO",
            fkUsuario: infoUsuario.id,
            fkIntituicao: infoPet.id,
            fkPet: infoPet.id
        }
        console.log(json)
        api.post("/demandas", json, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                swal.fire({
                    icon: "success",
                    title: <h2>Agora mande uma mensagem para a instituição e tire as suas dúvidas</h2>,
                }).then(() => {
                    navigate("/chat")
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

    useEffect(() => {

        api.get(`/pets/${idPet.id}/perfil`).then((res) => {
            setInfoPet(res.data)
            setPreferencias(res.data.caracteristicas)
        })

    }, [])

    return (
        <>
            <HeaderApp/>

            <div className="perfil-pet-usuario">
                <div className="perfil-pet-usuario-container">

                    <div className="perfil-pet-usuario-foto">
                        <img src={infoPet.caminhoImagem} alt="" />
                    </div>

                    <div className="perfil-pet-usuario-container-info">

                        <div className="perfil-pet-usuario-container-info-instituicao">

                            <div className="perfil-pet-usuario-info-instituicao-text">
                                {
                                    <>
                                        <h1>{infoPet.instituicao}</h1>
                                        <h1>{infoPet.distancia}</h1>
                                    </>
                                }
                            </div>

                        </div>

                        <div className="perfil-pet-usuario-info">

                            <div className="perfil-pet-usuario-info-text">

                                <div className="perfil-pet-usuario-info-pet">
                                    <div className="perfil-pet-usuario-info-pet-text">
                                        <div className="perfil-pet-usuario-info-nome">
                                            <h2>{infoPet.nome}</h2>
                                        </div>

                                        <div className="perfil-pet-usuario-info-idade">
                                            <p>Idade: </p>
                                            <span>{infoPet.idade}</span>
                                        </div>

                                        <div className="perfil-pet-usuario-info-especie">
                                            <p>Especie: </p>
                                            <span>{infoPet.especie}</span>
                                        </div>

                                        <div className="perfil-pet-usuario-info-porte">
                                            <p>Porte: </p>
                                            <span>{infoPet.porte}</span>
                                        </div>

                                        <div className="perfil-pet-usuario-info-raca">
                                            <p>Raça: </p>
                                            <span>{infoPet.raca}</span>
                                        </div>

                                        <div className="perfil-pet-usuario-info-mimos">
                                            <p>Mimos por mês: </p>
                                            <span>{infoPet.mimosPorMes}</span>
                                        </div>
                                    </div>
                                </div>

                                <div className="perfil-pet-usuario-info-descricao">
                                    <p>Descrição: </p>
                                    <textarea className="perfil-pet-usuario-descricao-textarea" id="" cols="30" rows="15" value={infoPet.descricao}></textarea>

                                </div>

                            </div>

                        </div>

                        <div className="perfil-pet-usuario-info-adocao-container">

                            <div className="perfil-pet-usuario-info-adocao">

                                <div className="perfil-pet-usuario-info-adocao-caracteristica">
                                    <p>Como eu sou/estou: </p>
                                    <div className="perfil-pet-usuario-caracteristica-btn">
                                        {
                                            preferencias.map((pref) => (
                                                <>
                                                    <button
                                                        type="button"
                                                        className="btn-preferencia"
                                                    >
                                                        {pref}
                                                    </button>
                                                </>
                                            ))
                                        }
                                    </div>
                                </div>

                                <div className="perfil-pet-usuario-info-adocao-acao-container">
                                    <div className="perfil-pet-usuario-info-adocao-acao">
                                        <p>Quem cuida de mim?</p>
                                        <h2>{infoPet.instituicao}</h2>
                                    </div>

                                    <div className="perfil-pet-usuario-info-adocao-acao-btn">
                                        <button
                                            onClick={handleSubmitPagamento}
                                        >Apadrinhar</button>
                                        <button
                                            onClick={handleSubmitPagamento}
                                        >Doar</button>
                                    </div>
                                    
                                    <div className="perfil-pet-usuario-info-adocao-adote-btn">
                                        <button
                                            onClick={handleSubmitAdocao}
                                        >Me Adote</button>
                                    </div>
                                </div>

                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <VLibras forceOnload={true}></VLibras>
        </>
    )
}

export default PerfilPetUsuario;