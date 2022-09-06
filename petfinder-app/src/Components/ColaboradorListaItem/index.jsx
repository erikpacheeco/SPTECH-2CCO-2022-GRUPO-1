import './styles.css';
import userIcon from "../../Images/png_img/user_icon.png";
import React from "react";

function ColaboradorListaItem(props){

    function handleEditItem() {
        console.log("edit!");
    }

    return(
        <>
            <div className="colaborador-item-lista">
                <div className="colaborador-item-lista-container">
                    <div className="colaborador-item-userIcon">
                        <img src={userIcon} alt="" />
                    </div>
                    <div className="colaborador-item-box">
                        <div className="colaborador-item-valor">
                            <div className="colaborador-item-valor-box">
                                <strong>NOME:</strong>
                                <strong>{props.nome}</strong>
                            </div>
                            <div  className="colaborador-item-valor-box">
                                <strong>CARGO:</strong>
                                <strong>{props.cargo}</strong>
                            </div>
                        </div>
                        <button onClick={handleEditItem} className="colaborador-lista-item-editar">EDITAR</button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ColaboradorListaItem;