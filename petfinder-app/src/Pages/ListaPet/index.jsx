import './lista-pet.css';
import HeaderApp from "../../Components/HeaderApp";
import PetListItem from "../../Components/PetListItem";
import add from "../../Images/plus.svg"
import file from "../../Images/file-txt.svg"
import api from "../../Api"
import React, { useEffect, useState } from 'react';
import VLibras from "@djpfs/react-vlibras"
import { useNavigate } from "react-router-dom";

function ListaPet() {

    const [allPets, setAllPets] = useState([]);

    const navigate = useNavigate();
    
    function handleAddItemList() {
        navigate(`/cadastro-pet/${objUser.id}`)
    }

    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));

    // function handleFileImport(event) {
    //     event.preventDefault()
    //     console.log(allPets)io
    // }

    useEffect(() => {
        api.get(`/pets/instituicao/${objUser.fkInstituicao.id}`).then(res => {
            setAllPets(res.data)
        })
    })

    return (
        <>

            <div className="lista-pet-root">
                <HeaderApp/>

                <div className="lista-pet">
                    <div className="lista-pet-container">
                        <div className="lista-pet-title">
                            <h2>Pets Cadastrados</h2>
                            <div className="lista-pet-buttons">
                                <label htmlFor="fileImport">
                                    {/* <div className="lista-pet-add-icon" onClick={handleAddItemList}>
                                        <img src={file} alt=""/>
                                        <input type="file" accept=".txt" id="fileImport" style={{display: "none"}}/>
                                    </div> */}
                                </label>
                                <div className="lista-pet-add-icon" onClick={handleAddItemList}>
                                    <img src={add} alt=""/>
                                </div>
                            </div>
                        </div>
                        <div id="idCadastroPetList" className="lista-pet-list">
                            {
                                allPets.map((pet) => (
                                    <PetListItem
                                        id={pet.id}
                                        nome={pet.nome}
                                        especie={pet.especie}
                                        raca={pet.raca}
                                        idade={pet.idade}
                                        porte={pet.porte}
                                    />
                                ))
                            }
                        </div>
                    </div>
                </div>
                
            </div>

            <VLibras forceOnload={true}></VLibras>
        </>
    )
}

export default ListaPet;