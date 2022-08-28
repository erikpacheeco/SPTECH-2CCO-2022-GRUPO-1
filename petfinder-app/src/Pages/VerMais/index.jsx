import React, { useState } from "react";
import "./VerMais.css";
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import img from "../../Images/erase.svg"
import img2 from "./img.png"

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
                <h1 className="ver-mais-h1-titulo">Todos os Pet´s</h1>
                <div className="ver-mais-container-conteudo">
                    <div className="ver-mais-container-filtros">
                        <div className="ver-mais-container-filtros-titulo">
                            <h2 className="ver-mais-h2-filtros">Filtros</h2>
                            <img src={img} alt="ver-mais-icone-de-filtro"></img>
                        </div>
                        <h2 className="ver-mais-h2-filtros-titulos">Instituições</h2>

                        <div className="ver-mais-container-filtro-backend">
                            <p className="ver-mais-p-filtro">Delivery de gatinhos</p>
                            <input type="checkbox" className="ver-mais-check-box-filtro" />
                        </div>

                        <h2 className="ver-mais-h2-filtros-titulos">Pets</h2>
                        <h2 className="ver-mais-h2-filtros-titulos">Características</h2>
                        <div className="ver-mais-container-filtro-backend">
                            <p className="ver-mais-p-filtro">Grande</p>
                            <input type="checkbox" className="ver-mais-check-box-filtro" />
                        </div>
                    </div>

                    <div className="ver-mais-fotos-container">
                        <div className="ver-mais-fotos-container-sub">
                            <img src={img2} alt="" className="ver-mais-img-animais" />
                        </div>
                    </div>

                </div>
            </div>        </>
    )
}