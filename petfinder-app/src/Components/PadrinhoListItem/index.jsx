import "./padrinho-list-item.css";
import userIcon from "../../Images/png_img/user_icon.png"
import React from "react";
import { useNavigate } from "react-router-dom";

function PadrinhoListItem({nome, id}) {

    //const infoUsuario = JSON.parse(localStorage.getItem('petfinder_user'));

    const navigate = useNavigate();

    function PadrinhoKeyValueItem({chave, value}) {
        return (
            <div className="padrinho-key-value-item-container">
                <div className="padrinho-key-value-item-key">{chave.toUpperCase()}</div>
                <div className="padrinho-key-value-item-value">{value}</div>
            </div>
        );
    }

    return(
        <div className="padrinho-item-list">
            <div className="padrinho-item-list-container">
                <div className="padrinho-item-userIcon">
                    <img src={userIcon} alt="" className="item-list-user-icon"/>
                </div>
                <div className="padrinho-item-value">
                    <PadrinhoKeyValueItem chave="nome" value={nome}/>
                </div>
                <button 
                    onClick={() => 
                        navigate(`/perfil/${id}`)
                    } 
                    className="padrinho-list-item-btn-editar-cadastro">VER PERFIL</button>
            </div>
            
        </div>
    )
}

export default PadrinhoListItem;