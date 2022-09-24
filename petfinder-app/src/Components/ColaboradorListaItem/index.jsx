import './styles.css';
import userIcon from "../../Images/png_img/user_icon.png";
import React from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from 'react-router-dom';

function ColaboradorListaItem(props){

    function handleEditItem() {
        console.log("edit!");
    }

    const navigate  = useNavigate()
    const idColaborador = useParams()

    return(
        <>
            <div className="colaborador-item-lista">
                <div className="colaborador-item-lista-container">
                    <div className="colaborador-item-userIcon">
                        <img src={userIcon} alt="" />
                    </div>
                    <div className="colaborador-item-box">
                        <div className="colaborador-item-valor">
                            <div className="colaborador-item-valor-box">
                                <strong>NOME:</strong>
                                <strong>{props.nome}</strong>
                            </div>
                            <div  className="colaborador-item-valor-box">
                                <strong>CARGO:</strong>
                                <strong>{props.cargo}</strong>
                            </div>
                        </div>
                        <button 
                        onClick={() => 
                            {
                            try {
                                navigate(`/editar-colaborador/${props.id}`)
                                
                            } catch (error) {
                                console.log(error)
                            }
                            }
                        } 
                        className="colaborador-lista-item-editar">EDITAR</button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ColaboradorListaItem;