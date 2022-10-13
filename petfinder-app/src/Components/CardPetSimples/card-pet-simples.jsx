import "./card-pet-simples.css"
import React from "react";
import api from "../../Api";

function CardPetSimples(props) {

    return (
       <img className="card-pet-simples"src={props.srcImg} alt="" />
    )
}

export default CardPetSimples;