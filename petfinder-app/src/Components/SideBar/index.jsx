import "./side-bar.css";
import SideBarItem from "../SideBarItem";
import closeSmall from "../../Images/close-small.svg";
import logout from "../../Images/logout.svg";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function SideBar(props) {

    const navigate = useNavigate()

    function handleLogout() {
        localStorage.clear()
        navigate("/")
        window.location.reload(true);
    }

    return (
        
        <nav className={props.sideBar? "side-bar side-bar-hidden": "side-bar"}>
            <div className="side-bar-close">
                <img src={closeSmall} alt="fechar Barra Lateral" onClick={()=> {props.setSideBar(true)}}/>  
            </div>
            <div className="side-bar-option">
                {
                    props.elementsSide.map((element)=> {
                        return (
                            <SideBarItem 
                                id={element.props.id}
                                label={element.props.label}
                                icon={element.props.icon}
                                navigateTo={element.props.navigateTo}
                            />
                        )
                    })
                }
            </div>
            <div className="side-bar-logout">
                <button 
                    className="btn-side-bar-logout"
                    onClick={handleLogout}
                >
                    <p>Sair</p>
                    <img 
                        src={logout} 
                        alt="Saída" 
                    /> 
                </button>
            </div>
        </nav>
    
    )

}

export default SideBar;