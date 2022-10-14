import icon_resgate from "../../Images/icon_resgate.svg"
import "./float-resgate.css"
import React from "react";

function FloatResgate() {
    return (
        <div
            id="idSolicitar"
            className="float-resgate-container"
            onMouseOver={()=>{
                if(document.getElementById("textSolicitar").style.display === "none"){
                    document.getElementById("textSolicitar").style.display = "block"
                }
            }}
            onMouseOut={()=>{
                if(document.getElementById("textSolicitar").style.display === "block"){
                    document.getElementById("textSolicitar").style.display = "none"
                }
            }}
        >
            <span
                id="textSolicitar"
                className="float-resgate-text"
                onMouseOver={() => {
                    document.getElementById("textSolicitar").style.display = "block";
                }}
                onMouseOut={() => {
                    document.getElementById("textSolicitar").style.display = "none";
                }}
            >
                Solicitar Resgate
            </span>
            <img
                className="float-resgate-icon"
                src={icon_resgate}
                alt="Telefone com pata de cachorro como símbolo"
                onMouseOver={() => {
                    document.getElementById("textSolicitar").style.display = "block";
                }}
            />
        </div>
    );
}

export default FloatResgate;