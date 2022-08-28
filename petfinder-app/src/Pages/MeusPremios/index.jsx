import React, { useState } from "react";
import "./meusPremios.css";
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import img from "../../Images/erase.svg";

export default function meusPremios() {
  return (
    <>
      <HeaderApp
        itens={[
          <NavItem label="Página Inicial" />,
          <NavItem isSelected={true} label="Meus Prêmios" />,
          <NavItem label="Mensagens" />,
        ]}
      />
      <div class="premios-container-geral">
        <h1 className="premios-h1-titulo">Meus Prêmios</h1>
        <div className="premios-container-conteudo">
          <div className="premios-container-filtros">
            <div className="premios-container-filtros-titulo">
              <h2 className="premios-h2-filtros">Filtros</h2>
              <img src={img} alt="meus-premios-icone-de-filtro"></img>
            </div>
            <h2 className="premios-h2-filtros-titulos">Instituições</h2>

            <div className="premios-container-filtro-backend">
              <p className="premios-p-filtro">Delivery de gatinhos</p>
              <input type="checkbox" className="premios-check-box-filtro" />
            </div>

            <h2 className="premios-h2-filtros-titulos">Pets</h2>
          </div>

          <div className="premios-fotos-container">
            {/* <img src={} alt="" className="premios-img-animais"/> */}
          </div>
          
        </div>
      </div>
    </>
  );
}
