import doente from "../../Images/png_img/saude_2.png"
import "./card-pet-ver-mais.css"
import React from "react";
import { useEffect, useState } from "react";

export default function CardPetVerMais(props) {

    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);
        setTimeout(() => {
        setLoading(false);
        }, 2000);
    }, []);

    CardPetVerMais.defaultProps= {
        isDoente: false,
        nome: "Label"
    }

    return (
        <>
            {loading ? (
                <div className="card-ver-mais-loader-container">
                    <div className="card-ver-mais-spinner"></div>
                </div>
                
            ) : (
                <figure className="card-ver-mais-pet-img-pet" onClick={props.onClick} 
                style={{backgroundImage: `url("${props.backgroundImage}")`}}
                >

                    <p style={{'textTransform': "Capitalize"}}>{props.nome}</p>
                        <img
                            className={
                                props.isDoente ? ("card-ver-mais-pet-info") : ("card-ver-mais-pet-hide")
                            } 
                            src={doente}
                            title="Este animal possui alguma doença atualmente "
                            alt=""
                        />
                </figure>
            )}
        </>
    );
}