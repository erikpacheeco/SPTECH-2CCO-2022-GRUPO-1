import './PerfilPetInstituicao.css';
import HeaderApp from "../../Components/HeaderApp";
import { useEffect, useState } from "react";
import api from "../../Api"
import React from "react";
import { useParams } from 'react-router-dom'
import { useNavigate } from "react-router-dom";
import Swal from 'sweetalert2';
import withReactContent from "sweetalert2-react-content";
import VLibras from "@djpfs/react-vlibras"

function resetValues() {
    return { 
        descricao: "",
        especie: "",
        idade: "",
        instituicao: "",
        isDoente: "",
        mimosPorMes: "",
        nome: "",
        porte: "",
        raca: ""
    }
}

function PerfilPetInstituicao() {

    const [infoPet, setInfoPet] = useState([])
    const [preferencias, setPreferencias] = useState([])
    const [values, setValues] = useState(resetValues)
    
    const idPet = useParams()
    const navigate = useNavigate()
    const swal = withReactContent(Swal);

    console.log(values)

    useEffect(() => {
        if(values.descricao == "" && values.especie == "" && values.idade == "" && values.instituicao == "" &&
        values.isDoente == "" && values.mimosPorMes == "" && values.nome == "" && values.porte == "" && values.raca == ""){
            api.get(`/pets/${idPet.id}/perfil`).then((res) => {
                setInfoPet(res.data)
                setPreferencias(res.data.caracteristicas)
                setValues({
                    descricao: `${res.data.descricao}`,
                    especie: `${res.data.especie}`,
                    id: `${res.data.id}`,
                    idade: `${res.data.idade}`,
                    instituicao: `${res.data.instituicao}`,
                    isDoente: `${res.data.isDoente}`,
                    mimosPorMes: `${res.data.mimosPorMes}`,
                    nome: `${res.data.nome}`,
                    porte: `${res.data.porte}`,
                    raca: `${res.data.raca}`
                })
                //console.log(res.data)
            })
        }
    }, [])

    function handleChange(event) {
        const { value, name } = event.target;
        setValues({ ...values, [name]: value, })
    }

    function handleSubmitPet(event) {
        event.preventDefault()
        let json = {
            id: values.id,
            nome: values.nome,
            especie: values.especie,
            raca: values.raca,
            porte: values.porte,
            sexo: values.sexo,
            descricao: values.descricao,
            caminhoImagem: values.caminhoImagem,
            doente: values.isDoente,
            adotado: values.adotado,
            instituicao: values.instituicao,
            caracteristicas: values.caracteristicas
        }
        api.put(`/pets/${infoPet.id}`, json, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                swal.fire({
                    icon: "success",
                    title: <h2>Pet atualizado com sucesso!</h2>,
                }).then(() => {
                    navigate(`/lista-pet`)
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

            <div className="perfil-pet-instituicao">
                <div className="perfil-pet-instituicao-container">

                    <div className="perfil-pet-instituicao-foto">
                        <h1>{infoPet.nome}</h1>
                        <img src={infoPet.caminhoImagem} alt="" />
                    </div>

                    <div className="perfil-pet-instituicao-informacao-container">

                        <div className="perfil-pet-instituicao-informacao">
                            <div className="perfil-pet-instituicao-info-pet-container">
                                <div className="perfil-pet-instituicao-info-pet">
                                    <div className="perfil-pet-instituicao-info">
                                        <div className="perfil-pet-instituicao-idade">
                                            <p>Idade: </p>
                                            <input type="text" value={infoPet.idade}/>
                                        </div>
                                        <div className="perfil-pet-instituicao-especie">
                                            <p>Espécie: </p>
                                            <input type="text" value={infoPet.especie}/>
                                        </div>
                                    </div>
                                    <div className="perfil-pet-instituicao-info">
                                        <div className="perfil-pet-instituicao-sexo">
                                            <p>Porte: </p>
                                            <input type="text" name="porte" value={values.porte} onChange={handleChange}/>
                                        </div>
                                        <div className="perfil-pet-instituicao-raca">
                                            <p>Raça: </p>
                                            <input type="text" name="raca" value={values.raca} onChange={handleChange}/>
                                        </div>
                                    </div>
                                    <div className="perfil-pet-instituicao-info">
                                        <div className="perfil-pet-instituicao-doente">
                                            <p>Doente: </p>
                                            <select name="isDoente" id="isDoente" value={values.isDoente} onChange={handleChange}>
                                                <option value="true">Sim</option>
                                                <option value="false">Não</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="perfil-pet-instituicao-descricao-pet-container">
                                <div className="perfil-pet-instituicao-descricao-pet">
                                    <p>Descrição: </p>
                                    <textarea id="" cols="67" rows="6" name="descricao" value={values.descricao} onChange={handleChange}></textarea>
                                </div>
                            </div>
                        
                            <div className="perfil-pet-instituicao-caracteristica-pet-container"> 
                                <div className="perfil-pet-instituicao-caracteristica-pet">
                                    <p>Como eu sou/estou: </p>
                                    <div className="perfil-pet-instituicao-caracteristica">
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
                            </div>
                        </div>
                    </div>    
                </div>
                <div className="perfil-pet-instituicao-btn">
                    <button onClick={() => navigate(`/adicionar-mimos/${idPet.id}`)}>Adicionar Mimos</button>
                    <button type="submit" onClick={handleSubmitPet}>Salvar</button>
                </div>
            </div>
            
            <VLibras forceOnload={true}></VLibras>
        </>
    )
}

export default PerfilPetInstituicao;