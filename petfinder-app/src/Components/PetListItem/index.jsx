import "./styles.css";
import userIcon from "../../Images/png_img/user_icon.png"
import React from "react";

function PetListItem({nome, especie, raca, idade, peso}) {

    function PetKeyValueItem({chave, value}) {
        return (
            <div className="pet-key-value-item-container">
                <div className="pet-key-value-item-key">{chave.toUpperCase()}</div>
                <div className="pet-key-value-item-value">{value}</div>
            </div>
        );
    }

    function handleEditItem() {
        console.log("edit!");
    }

    return(
        <div className="pet-item-list">
            <img src={userIcon} alt="" className="item-list-user-icon"/>
            <PetKeyValueItem chave="nome" value={nome}/>
            <PetKeyValueItem chave="especie" value={especie}/>
            <PetKeyValueItem chave="raça" value={raca}/>
            <PetKeyValueItem chave="idade" value={idade}/>
            <PetKeyValueItem chave="peso" value={peso}/>
            <button onClick={handleEditItem} className="pet-list-item-btn-editar-cadastro">EDITAR CADASTRO</button>
        </div>
    )
}

export default PetListItem;