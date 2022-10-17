import React from "react";
import HeaderApp from "../../Components/HeaderApp";
import "./perfil-instituicao.css"

export default function Perfilinstituicao() {
    return(
        <>
        <HeaderApp/>
        <div className="perfil-inst-container-geral">
            <div className="perfil-inst-container-interno">
                <div className="perfil-inst-pontuacao"></div>
                <div className="perfil-inst-info-account"></div>
                <div className="perfil-inst-info-endereco"></div>
            </div>
        </div>
        </>
    )
}
