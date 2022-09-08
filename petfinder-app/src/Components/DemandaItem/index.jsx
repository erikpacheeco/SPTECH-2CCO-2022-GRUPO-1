import "./demanda-item.css"
import React from "react";

export default function DemandaItem(props) {
    
    function chooseColor(){
        if(props.categoria.toLowerCase() == "adocao" ){
            return "demanda-item-categoria demanda-item-adocao"
        }else if(props.categoria.toLowerCase() == "pagamento" ){
            return "demanda-item-categoria demanda-item-pagamento"
        }else if(props.categoria.toLowerCase() == "resgate" ){
            return "demanda-item-categoria demanda-item-resgate"
        }
    }

    return(
        <div className="demanda-item-container" id={props.id} onClick={props.onClick}>
            <p className={chooseColor()}>{props.categoria.toLowerCase()}</p>
            <p className="demanda-item-colab">{props.nome}</p>
        </div> 
    );
}