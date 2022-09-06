import "./side-bar.css";
import SideBarItem from "../SideBarItem";
import closeSmall from "../../Images/close-small.svg";
import logout from "../../Images/logout.svg";
import React, { useEffect, useState } from "react";

function SideBar(props) {

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
                            />
                        )
                    })
                }
            </div>
            <div className="side-bar-logout">
                <button className="btn-side-bar-logout">
                    <p>Sair</p>
                    <img src={logout} alt="Saída" /> 
                </button>
            </div>
        </nav>
    
    )

}

export default SideBar;