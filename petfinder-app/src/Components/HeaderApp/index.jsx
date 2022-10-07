import app_menu from "../../Images/application-menu.svg"
import perfil from "../../Images/people.svg"
import NavItem from "../NavItem"
import "./header-app.css"
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import SideBar from "../SideBar";
import SideBarItem from "../SideBarItem";
import headerFunctions from "../../functions/headerFunctions";

function logoff(){
    if(localStorage.getItem("petfinder_user") == null){
        window.location.href = "/";
    }
}

function HeaderApp() {

    logoff();

    const [objUser, setObjUser] = useState(JSON.parse(localStorage.getItem("petfinder_user")));

    const [objUserNome, setObjUserNome] = useState(objUser.nome.split(" "));
    
    const [hiddenSideBar, setHidenSideBar] = useState(true);
    
    const navigate = useNavigate();

    return (
    <header>
        <nav className="header-app-nav">
            <SideBar sideBar={hiddenSideBar} setSideBar={setHidenSideBar}  elementsSide={headerFunctions.sideBarNivelAcesso(objUser.nivelAcesso)} />
            <div className="header-app-container-nav">
                <img className="header-app-application-menu" src={app_menu} alt="icone do menu" onClick={()=>{setHidenSideBar(false)}}/>
                <div className="header-app-container-btn-nav header-app-nav-container-itens">
                    {
                        headerFunctions.headerNivelAcesso(objUser.nivelAcesso).map((element)=> {
                            return (<NavItem 
                                isSelected={element.props.isSelected} 
                                navigateTo={element.props.navigateTo}
                                id={element.props.id}
                                label={element.props.label}
                            />)
                        })
                    }
                </div>
                <div 
                    className="header-app-container-perfil-nav"
                    onClick={() => {
                        if (objUser.nivelAcesso == "user") {
                            navigate(`/perfil-usuario`)
                        } else {
                            navigate(`/perfil/${objUser.id}`)
                        }
                    }
                    } >
                    <p className="header-app-p">{objUserNome[0]}</p>
                    <img className="header-app-perfil-nav" src={perfil} alt="botão para o perfil"/>  
                </div>
            </div>
        </nav>
    </header>
    );
}

export default HeaderApp;