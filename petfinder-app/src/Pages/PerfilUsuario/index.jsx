import './perfil-usuario.css';

import HeaderApp from "../../Components/HeaderApp";
import headerFunctions from "../../functions/headerFunctions";
import SideBarItem from "../../Components/SideBarItem";
import NavItem from "../../Components/NavItem";

function PerfilUsuario(){
    
    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));

    return(
        <>
            <HeaderApp
                sideItens={headerFunctions.sideBarNivelAcesso(objUser.nivelAcesso)}
                itens={headerFunctions.headerNivelAcesso(objUser.nivelnivelAcesso)}
            />
        </>
    )

}

export default PerfilUsuario;