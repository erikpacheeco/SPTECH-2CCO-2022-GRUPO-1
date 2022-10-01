import './adm-list-item.css';
import userIcon from "../../Images/png_img/user_icon.png";
import React from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from 'react-router-dom';

function AdmListItem(props){

    const navigate  = useNavigate()
    const idAdm = useParams()

    return(
        <>
            <div className="adm-item-list">
                <div className="adm-item-list-container">
                    <div className="adm-item-userIcon">
                        <img src={userIcon} alt="" />
                    </div>
                    <div className="adm-item-box">
                        <div className="adm-item-valor">
                            <div className="adm-item-valor-box">
                                <strong>NOME:</strong>
                                <strong>{props.nome}</strong>
                            </div>
                        </div>
                        <button 
                        onClick={() => 
                            {
                            try {
                                navigate(`/editar-adm/${props.id}`)
                                
                            } catch (error) {
                                console.log(error)
                            }
                            }
                        } 
                        className="adm-list-item-editar">EDITAR</button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default AdmListItem;

