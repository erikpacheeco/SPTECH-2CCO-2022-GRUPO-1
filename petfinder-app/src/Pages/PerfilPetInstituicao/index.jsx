import './PerfilPetInstituicao.css';
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import { useEffect, useState } from "react";
import api from "../../Api"

function PerfilPetInstituicao() {

    const [infoPet, setInfoPet] = useState([])
    const [preferencias, setPreferencias] = useState([])

    useEffect(() => {
        api.get("/pets/1/perfil").then((res) => {
            setInfoPet(res.data)
            setPreferencias(res.data.caracteristicas)
        })
    })

    return(
        <>
            <HeaderApp itens={[
                <NavItem label="Pets" />,
                <NavItem label="Demandas" />
            ]}/>

            <div className="perfil-pet-instituicao">
                <div className="perfil-pet-instituicao-container">

                    <div className="perfil-pet-instituicao-foto">
                        <h1>{infoPet.nome}</h1>
                        <img src="localhost:8080${infoPet.caminhoImagem}" alt="" />
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
                                            <input type="text" value={infoPet.porte}/>
                                        </div>
                                        <div className="perfil-pet-instituicao-raca">
                                            <p>Raça: </p>
                                            <input type="text" value={infoPet.raca}/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="perfil-pet-instituicao-descricao-pet-container">
                                <div className="perfil-pet-instituicao-descricao-pet">
                                    <p>Descrição: </p>
                                    <textarea id="" cols="67" rows="8" value={infoPet.descricao}></textarea>
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
                    <button>Adicionar Mimos</button>
                    <button>Editar</button>
                </div>
            </div>
            
        </>
    )
}

export default PerfilPetInstituicao;