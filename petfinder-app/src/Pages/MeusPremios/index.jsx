import React, { useState } from "react";
import "./meusPremios.css";
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import img from "./img/erase.svg";
import img2 from "./img/img roberto.png"

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
              <img src={img} alt="icone de filtro"></img>
            </div>
            <h2 className="premios-h2-filtros-titulos">Instituições</h2>
            <div className="premios-container-filtro-backend">
              <p className="premios-p-filtro">Delivery de gatinhos</p>
              <input type="checkbox" className="premios-check-box-filtro" />
            </div>
            <h2 className="premios-h2-filtros-titulos">Pets</h2>
            <h2 className="premios-h2-filtros-titulos">Características</h2>
          </div>
          <div className="premios-fotos-container">
            <div className="premios-fotos-container-sub">
            <img src={img2} alt="" className="premios-img-animais"/>
            <img src={img2} alt="" className="premios-img-animais"/>
            <img src={img2} alt="" className="premios-img-animais"/>
            <img src={img2} alt="" className="premios-img-animais"/>
            <img src={img2} alt="" className="premios-img-animais"/>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
