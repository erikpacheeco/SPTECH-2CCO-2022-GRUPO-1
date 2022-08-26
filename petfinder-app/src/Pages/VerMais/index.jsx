import React, { useState } from "react";
import "./VerMais.css";
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";

export default function verMais() {
    return (
        <>
            <HeaderApp
                itens={[
                    <NavItem label="Página Inicial" />,
                    <NavItem label="Meus Prêmios" />,
                    <NavItem label="Mensagens" />,
                ]}
            />
            <div class="ver-mais-container-geral">
                <h1 className="ver-mais-h1-titulo">Meus Prêmios</h1>
                <div className="ver-mais-container-conteudo">
                    <div className="ver-mais-container-filtros">
                        <div className="ver-mais-container-filtros-titulo">
                            <h2 className="ver-mais-h2-filtros">Filtros</h2>
                        </div>
                        <h2 className="ver-mais-h2-filtros-titulos">Instituições</h2>

                        <div className="ver-mais-container-filtro-backend">
                            <p className="ver-mais-p-filtro">Delivery de gatinhos</p>
                            <input type="checkbox" className="ver-mais-check-box-filtro" />
                        </div>

                        <h2 className="ver-mais-h2-filtros-titulos">Pets</h2>
                        <h2 className="ver-mais-h2-filtros-titulos">Características</h2>
                    </div>

                    <div className="ver-mais-fotos-container">
                        <div className="ver-mais-fotos-container-sub">
                            {/* <img src={} alt="" className="ver-mais-img-animais"/> */}
                        </div>
                    </div>

                </div>
            </div>        </>
    )

}