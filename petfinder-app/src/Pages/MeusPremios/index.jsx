import React, { useState } from "react";
import "./meusPremios.css";
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import SideBarItem from '../../Components/SideBarItem';
import perfil from "../../Images/people.svg"
import home from "../../Images/home.svg"
import message from "../../Images/message.svg"
import premio from "../../Images/picture.svg"
import img from "../../Images/erase.svg";
import VLibras from "@djpfs/react-vlibras"

export default function meusPremios() {
  return (
    <>
      <HeaderApp

        sideItens={[
          <SideBarItem label="Página Inicial" icon={home} navigateTo="/home-user"/>,
          <SideBarItem label="Mensagens" icon={message} navigateTo="/chat" />,
          <SideBarItem label="Meu Perfil" icon={perfil} navigateTo="" />,
          <SideBarItem label="Meus Prêmios" icon={premio} navigateTo="/meus-premios" />
        ]}

        itens={[
          <NavItem label="Página Inicial" navigateTo="/home-user" />,
          <NavItem isSelected={true} label="Meus Prêmios" navigateTo="/meus-premios" />,
          <NavItem label="Mensagens" navigateTo="/chat" />
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

      <VLibras forceOnload={true}></VLibras>
    </>
  );
}
