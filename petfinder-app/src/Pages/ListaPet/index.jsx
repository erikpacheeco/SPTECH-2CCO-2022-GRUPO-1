import './styles.css';
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import PetListItem from "../../Components/PetListItem";
import React from "react";
import { useEffect, useState } from "react";
import api from "../../Api"

function ListaPet() {

    const [infoPet, setInfoPet] = useState([])
    
    useEffect(() => {
        api.get(`/pets/instituicao/1`).then((res) => {
            setInfoPet(res.data)
        })
    })

    function handleAddItemList() {
        console.log("click!");
    }

    return(
        <>
            <HeaderApp itens={[
                <NavItem label="Dashboard" />,
                <NavItem label="Padrinhos" />,
                <NavItem label="Demandas" />,
                <NavItem isSelected={true} label="Pets" />
            ]}
            />

            <div className="cadastro-pet-container">
                <div className="cadastro-pet-title">
                    <h2>Pets Cadastrados</h2>
                    <div className="cadastro-pet-add-icon" onClick={handleAddItemList}>+</div>
                </div>
                <div id="idCadstroPetList" className="cadastro-pet-list">
                    {
                        infoPet.map((info) => (
                            <PetListItem id={info.id} nome={info.nome} especie={info.especie} raca={info.raca} idade={info.idade} peso={info.peso}/>
                        ))
                    }
                </div>
            </div>
                
        </>
    )
}

export default ListaPet;