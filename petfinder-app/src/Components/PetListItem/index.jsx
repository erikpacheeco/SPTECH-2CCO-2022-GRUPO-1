import "./pet-list-item.css";
import userIcon from "../../Images/png_img/user_icon.png"
import React from "react";
import { useNavigate } from "react-router-dom";

function PetListItem({nome, especie, raca, idade, porte, id}) {

    const navigate = useNavigate()

    function PetKeyValueItem({chave, value}) {
        return (
            <div className="pet-key-value-item-container">
                <div className="pet-key-value-item-key">{chave.toUpperCase()}</div>
                <div className="pet-key-value-item-value">{value}</div>
            </div>
        );
    }

    return(
        <div className="pet-item-list">
            <div className="pet-item-list-container">
                <div className="pet-item-userIcon">
                    <img src={userIcon} alt="" className="item-list-user-icon"/>
                </div>
                <div className="pet-item-value">
                    <PetKeyValueItem chave="nome" value={nome}/>
                    <PetKeyValueItem chave="especie" value={especie}/>
                    {/* <PetKeyValueItem chave="raça" value={raca}/>
                    <PetKeyValueItem chave="idade" value={idade}/>
                    <PetKeyValueItem chave="porte" value={porte}/>
                    <PetKeyValueItem chave="id" value={id}/> */}
                </div>
                <button 
                    onClick={() => 
                        navigate(`/perfil-pet-instituicao/${id}`)
                    } 
                    className="pet-list-item-btn-editar-cadastro">EDITAR</button>
            </div>
            
        </div>
    )
}

export default PetListItem;