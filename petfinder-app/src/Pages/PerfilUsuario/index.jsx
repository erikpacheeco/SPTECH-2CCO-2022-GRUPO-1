import './perfil-usuario.css';

import HeaderApp from "../../Components/HeaderApp";
import React from "react";

function PerfilUsuario(){
    
    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));

    return(
        <>
            <HeaderApp/>
        </>
    )

}

export default PerfilUsuario;