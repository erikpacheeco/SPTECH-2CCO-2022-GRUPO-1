import app_menu from "../../Images/application-menu.svg"
import perfil from "../../Images/people.svg"
import NavItem from "../NavItem"
import "./header-app.css"
import React, { useState } from "react";
import SideBar from "../SideBar";
import SideBarItem from "../SideBarItem";

function HeaderApp(props) {
    HeaderApp.defaultProps = {
        itens : [<NavItem isSelected = {true}/>,<NavItem/>,<NavItem/>],
        sideItens :  [<SideBarItem/>]
    }

    const [hiddenSideBar, setHidenSideBar] = useState(true);
    
    return (
    <header>
        <nav className="header-app-nav">
            <SideBar sideBar={hiddenSideBar} setSideBar={setHidenSideBar}  elementsSide={props.sideItens} />
            <div className="header-app-container-nav">
                <img className="header-app-application-menu" src={app_menu} alt="icone do menu" onClick={()=>{setHidenSideBar(false)}}/>
                <div className="header-app-container-btn-nav header-app-nav-container-itens">
                    {
                        props.itens.map((element)=> {
                            return (<NavItem 
                            isSelected={element.props.isSelected} 
                            navigateTo={element.props.navigateTo}
                            id={element.props.id}
                            label={element.props.label}
                            key={props.itens.indexOf(element)}
                            />)
                        })
                    }
                </div>
                <img className="header-app-perfil-nav" src={perfil} alt="botão para o perfil"/>
                <p className="header-app-p">Perfil</p>
            </div>
        </nav>
    </header>
    );
}

export default HeaderApp;