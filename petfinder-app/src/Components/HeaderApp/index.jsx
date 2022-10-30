import app_menu from "../../Images/application-menu.svg"
import perfil from "../../Images/people.svg"
import NavItem from "../NavItem"
import "./header-app.css"
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import SideBar from "../SideBar";
import headerFunctions from "../../functions/headerFunctions";
import { useEffect } from "react";

function logoff(){
    if(localStorage.getItem("petfinder_user") == null){
        window.location.href = "/";
    }
}

function HeaderApp() {

    logoff();

    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));

    const objUserNome = objUser.nome.split(" ");
    
    const [hiddenSideBar, setHidenSideBar] = useState(true);
    
    const navigate = useNavigate();

    useEffect(() => {
        let body = document.getElementsByTagName("body")[0];
        if(objUser.nivelAcesso == "user") body.classList.add("background-user")
    },[])

    return (
    <header>
        <nav className="header-app-nav">
            <SideBar sideBar={hiddenSideBar} setSideBar={setHidenSideBar}  elementsSide={headerFunctions.sideBarNivelAcesso(objUser.nivelAcesso)} />
            <div className="header-app-container-nav">
                <img className="header-app-application-menu" src={app_menu} alt="icone do menu" onClick={()=>{setHidenSideBar(false)}}/>
                <div className="header-app-container-btn-nav header-app-nav-container-itens">
                    {
                        headerFunctions.headerNivelAcesso(objUser.nivelAcesso).map((element, index)=> {
                            return (<NavItem 
                                isSelected={element.props.isSelected} 
                                navigateTo={element.props.navigateTo}
                                id={element.props.id}
                                label={element.props.label}
                                key={index}
                            />)
                        })
                    }
                </div>
                <div 
                    className="header-app-container-perfil-nav"
                    onClick={() => {
                        if (objUser.nivelAcesso == "user") {
                            navigate(`/perfil-usuario/${objUser.id}`)
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