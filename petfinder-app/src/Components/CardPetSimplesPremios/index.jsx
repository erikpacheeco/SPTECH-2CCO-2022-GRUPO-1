import "./card-pet-simples-premios.css"
import React from "react";

function CardPetSimples(props) {

    return (
       <img className="cardPetSimples"src={props.srcImg} alt="" />
    )
}

export default CardPetSimples;