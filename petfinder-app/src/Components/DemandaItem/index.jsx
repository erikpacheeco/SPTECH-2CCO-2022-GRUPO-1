import "./demanda-item.css"
import React from "react";

export default function DemandaItem(props) {
    
    function chooseColor(){
        return `demanda-item-categoria demanda-item-${props.categoria.toLowerCase()}`;
    }

    return(
        <div className={`demanda-item-container ${props.isSelected ? 'demanda-item-selected' : ''}`} id={props.id} onClick={props.onClick}>
            <p className={chooseColor()}>{props.categoria.toLowerCase()}</p>
            <p>#{props.id}</p>
            <p className="demanda-item-colab">{props.nome}</p>
        </div> 
    );
}