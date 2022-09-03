import './styles.css';
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import PetListItem from "../../Components/PetListItem";
import React from "react";

function ListaPet() {

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
                    <PetListItem nome="Rubens" especie="Cachorro" raca="SRD" idade="5" peso="34"/>
                    <PetListItem nome="Rubens" especie="Cachorro" raca="SRD" idade="5" peso="34"/>
                    <PetListItem nome="Rubens" especie="Cachorro" raca="SRD" idade="5" peso="34"/>
                </div>
            </div>
                
        </>
    )
}

export default ListaPet;