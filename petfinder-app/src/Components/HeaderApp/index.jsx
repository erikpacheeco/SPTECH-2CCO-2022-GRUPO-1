import app_menu from "../../Images/application-menu.svg"
import perfil from "../../Images/people.svg"
import NavItem from "../NavItem"
import "./header-app.css"
import React, { useState } from "react";
import SideBar from "../SideBar";
import SideBarItem from "../SideBarItem";
import headerFunctions from "../../functions/headerFunctions";

function HeaderApp() {

    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));

    const objUserNome = objUser.nome.split(" ");

    const [hiddenSideBar, setHidenSideBar] = useState(true);
    
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
                <p className="header-app-p">{objUserNome[0]}</p>
                <img className="header-app-perfil-nav" src={perfil} alt="botão para o perfil"/>  
            </div>
        </nav>
    </header>
    );
}

export default HeaderApp;