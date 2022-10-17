import "./mensagem.css"
import React from "react";
import docPreview from "../../Images/file-doc-white.svg";

function Mensagem(props) {
    Mensagem.defaultProps = {
        content: 'Esse texto é o padrão que está definido no props',
        date: "02/02/2002",
        idUsuario: "1"
    }

    function formatData(data) {
        let date_full = new Date(data);
        let day = String(date_full.getDate()).padStart(2, "0");
        let minute = String(date_full.getMinutes()).padStart(2, "0");
        let hour = String(date_full.getHours()).padStart(2, "0");
        let month = String(date_full.getMonth() + 1).padStart(2, "0");
        let year = date_full.getFullYear();
        let time = `${hour}:${minute}`;
        let date_string = time + ' - ' + day + '/' + month + '/' + year;
        return date_string;
    }

    return (
        <div className={Number(localStorage.getItem('petfinder_user_id')) === props.idUsuario ? 'msg-left' : 'msg-right'}>
            <div className={Number(localStorage.getItem('petfinder_user_id')) === props.idUsuario ? 'msg-container msg-color-right' : 'msg-container msg-color-left'}>

                {props.tipo === "texto" ? 
                <p>{props.content}</p> : 
                (props.tipo === "img" ? 
                <img src={props.content} alt="imagem" className="mensagem-imagem" /> :
                <a href={props.content}>
                    <img src={docPreview} alt="doc preview" className="mensagem-imagem"/>
                </a>
                )
                }
                <p className="msg-date">{formatData(props.date)}</p>
            </div>
        </div>
    );
}

export default Mensagem;