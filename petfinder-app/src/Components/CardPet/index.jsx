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
        <figure class="card-pet-img-pet" onClick={props.onClick} style={{backgroundImage: `url("${api.defaults.baseURL}${props.backgroundImage}")`}}>
            <p>{props.nome}</p>
            <img
                className={
                    props.isDoente ? ("") : ("card-user-hide")
                } src={doente} alt={props.tipo}
            />
        </figure>
    );
}