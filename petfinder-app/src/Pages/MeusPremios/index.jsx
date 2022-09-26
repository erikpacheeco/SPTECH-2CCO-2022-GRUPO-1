import React, { useState } from "react";
import "./meusPremios.css";
import HeaderApp from "../../Components/HeaderApp";
import img from "../../Images/erase.svg";
import VLibras from "@djpfs/react-vlibras"
import headerFunctions from "../../functions/headerFunctions";

export default function meusPremios() {
  const objUser = JSON.parse(localStorage.getItem("petfinder_user"));
  return (
    <>
      <HeaderApp
        sideItens={headerFunctions.sideBarNivelAcesso(objUser.nivelAcesso)}
        itens={headerFunctions.headerNivelAcesso(objUser.nivelnivelAcesso)}
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

      <VLibras forceOnload={true}></VLibras>
    </>
  );
}
