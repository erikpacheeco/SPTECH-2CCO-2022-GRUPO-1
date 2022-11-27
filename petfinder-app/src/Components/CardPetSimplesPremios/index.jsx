import "./card-pet-simples-premios.css"
import React from "react";
import { LazyLoadImage } from "react-lazy-load-image-component";
import "react-lazy-load-image-component/src/effects/blur.css";

function CardPetSimples(props) {

    return (
        <LazyLoadImage 
            effect="blur"
            className="cardPetSimples"
            src={props.srcImg} 
            alt="" 
        />
    )
}

export default CardPetSimples;