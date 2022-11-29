import "./card-pet-simples-premios.css"
import React from "react";
import { useEffect, useState } from "react";

function CardPetSimples(props) {
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);
        setTimeout(() => {
        setLoading(false);
        }, 2000);
    }, []);

    return (
        <>
            {loading ? (
                <div className="card-simples-loader-container">
                    <div className="card-simples-spinner"></div>
                </div>
            ) : (
                <img
                    className="cardPetSimples"
                    src={props.srcImg} 
                    alt="" 
                />
            )}
        </>
    )
}

export default CardPetSimples;