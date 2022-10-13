import doente from "../../Images/png_img/saude_2.png"
import "./card-pet.css"
import React from "react";
import api from "../../Api";

export default function CardPet(props) {

    CardPet.defaultProps= {
        isDoente: false,
        nome: "Label"
    }

    return (
        <figure className="card-pet-img-pet" onClick={props.onClick} style={{backgroundImage: `url("${props.backgroundImage}")`}}>
            <p>{props.nome}</p>
            <img
                className={
                    props.isDoente ? ("card-pet-info") : ("card-pet-hide")
                } src={doente}
                title="Este animal possui alguma doença atualmente "
            />
        </figure>
    );
}