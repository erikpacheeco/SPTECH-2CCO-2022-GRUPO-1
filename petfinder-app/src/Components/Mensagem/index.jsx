import "./mensagem.css"
import React from "react";

function Mensagem(props) {
    Mensagem.defaultProps = {
        content: 'Esse texto é o padrão que está definido no props',
        date: "02/02/2002",
        idUsuario: "1"
    }

    return (
        <div className={localStorage.getItem('petfinder_user_id') === props.idUsuario ? 'msg-left' : 'msg-right'}>
            <div className={localStorage.getItem('petfinder_user_id') === props.idUsuario ? 'msg-container msg-color-right' : 'msg-container msg-color-left'}>
                <p>{props.content}</p>
                <p className="msg-date">{props.date}</p>
            </div>
        </div>
    );
}

export default Mensagem;