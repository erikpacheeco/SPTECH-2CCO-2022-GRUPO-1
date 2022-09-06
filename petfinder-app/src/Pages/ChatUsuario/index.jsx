import React from "react";
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import SideBarItem from "../../Components/SideBarItem";
import perfil from "../../Images/people.svg"
import home from "../../Images/home.svg"
import message from "../../Images/message.svg"
import premio from "../../Images/picture.svg"
import standby from "../../Images/chat-standby.svg"
import down from "../../Images/chat-down.svg"
import "./chat-user.css"

export default function ChatUsuario() {
    return (
        <>
            <HeaderApp
                sideItens={[
                    <SideBarItem label="Página Inicial" icon={home} navigateTo={"/home-user"} />,
                    <SideBarItem label="Mensagens" icon={message} navigateTo="/chat-user" />,
                    <SideBarItem label="Meu Perfil" icon={perfil} navigateTo="" />,
                    <SideBarItem label="Meus Prêmios" icon={premio} navigateTo="/meus-premios" />
                ]}

                itens={[
                    <NavItem label="Página Inicial" navigateTo="/home-user" />,
                    <NavItem label="Meus Prêmios" navigateTo="/meus-premios" />,
                    <NavItem isSelected={true} label="Mensagens" navigateTo="/chat-user" />
                ]}
            />
            <div className="chat-user-centralizer">
                <div className="chat-user-container">
                    <div className="chat-user-container-demandas">
                        <div className="chat-user-demandas-pending-header">
                            <p>Em Andamento</p>
                            <p>1</p>
                            <img src={standby} alt="seta para minimizar demandas em aberto"/>
                        </div>
                    </div>
                    <div className="chat-user-container-chat">
                        <input type="text" />
                    </div>
                </div>
            </div>
        </>
    );
}