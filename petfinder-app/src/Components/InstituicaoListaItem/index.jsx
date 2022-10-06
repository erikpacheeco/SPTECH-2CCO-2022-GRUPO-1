import './instituicao-list-item.css';
import userIcon from "../../Images/png_img/user_icon.png";
import React from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from 'react-router-dom';
import { useState } from 'react';
import { useEffect } from 'react';
import api from '../../Api';

function ColaboradorListaItem(props){

    const [pets, setPets] = useState(0);
    const [instituicao, setInstituicao] = useState(0);
 

    useEffect(() => {
        api.get(`/pets/instituicao/pets/count/${(props.qtd)}`).then((res) => {
            setPets(res.data);
            console.log(res.data);
        });
        api.get(`instituicoes/instituicao/colaborador/count/${(props.qtdCol)}`).then((res) => {
            setInstituicao(res.data);
        });
    }, []);

    return(
        <>
            <div className="instituicao-item-lista">
                <div className="instituicao-item-lista-container">
                    <div className="instituicao-item-userIcon">
                        <img src={userIcon} alt="" />
                    </div>
                    <div className="instituicao-item-box">
                        <div className="instituicao-item-valor">
                            <div className="instituicao-item-valor-box">
                                <strong>NOME:</strong>
                                <strong>{props.nome}</strong>
                            </div>
                            <div  className="instituicao-item-valor-box">
                                <strong>PETS:</strong>
                                <strong>{pets}</strong>
                            </div>
                            <div  className="instituicao-item-valor-box">
                                <strong>Colaboradores:</strong>
                                <strong>{instituicao}</strong>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ColaboradorListaItem;