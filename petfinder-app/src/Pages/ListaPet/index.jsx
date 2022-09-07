import './lista-pet.css';
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import PetListItem from "../../Components/PetListItem";
import add from "../../Images/plus.svg"
import file from "../../Images/file-txt.svg"
import api from "../../Api"
import React, { useEffect, useState } from 'react';
import { calculateNewValue } from '@testing-library/user-event/dist/utils';

function ListaPet() {

    const [allPets, setAllPets] = useState([]);

    function handleAddItemList() {
        console.log(allPets)
    }

    // function handleFileImport(event) {
    //     event.preventDefault()
    //     console.log(allPets)
    // }

    useEffect(() => {
        api.get(`/pets/instituicao/${2}`).then(res => {
            setAllPets(res.data)
        })
    })

    return (
        <>
            {/* <HeaderApp itens={[
                <NavItem label="Dashboard" />,
                <NavItem label="Padrinhos" />,
                <NavItem label="Demandas" />,
                <NavItem isSelected={true} label="Pets" />
            ]}
            /> */}

            <div className="lista-pet-container">
                <div className="lista-pet-title">
                    <h2>Pets Cadastrados</h2>
                    <div className="lista-pet-buttons">
                        <label htmlFor="fileImport">
                            <div className="lista-pet-add-icon" onClick={handleAddItemList}>
                                <img src={file} />
                                <input type="file" accept=".txt" id="fileImport" style={{display: "none"}}/>
                            </div>
                        </label>
                        <div className="lista-pet-add-icon" onClick={handleAddItemList}>
                            <img src={add} />
                        </div>
                    </div>
                </div>
                <div id="idCadastroPetList" className="lista-pet-list">
                    {
                        allPets.map((pet) => (
                            <PetListItem
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

        </>
    )
}

export default ListaPet;