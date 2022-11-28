import doente from "../../Images/png_img/saude_2.png"
import "./card-pet.css"
import React from "react";
import { useEffect, useState } from "react";

export default function CardPet(props) {

    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);
        setTimeout(() => {
        setLoading(false);
        }, 2000);
    }, []);

    CardPet.defaultProps= {
        isDoente: false,
        nome: "Label"
    }

    return (
        <>
            {loading ? (
                <div className="card-loader-container">
                    <div className="card-spinner"></div>
                </div>
                
            ) : (
                <figure className="card-pet-img-pet" onClick={props.onClick} 
                style={{backgroundImage: `url("${props.backgroundImage}")`}}
                >

                    <p style={{'textTransform': "Capitalize"}}>{props.nome}</p>
                        <img
                            className={
                                props.isDoente ? ("card-pet-info") : ("card-pet-hide")
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