import React, { useEffect, useState } from "react";
import "./chat-user.css"
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import SideBarItem from "../../Components/SideBarItem";
import Mensagem from "../../Components/Mensagem";
import perfil from "../../Images/people.svg"
import home from "../../Images/home.svg"
import message from "../../Images/message.svg"
import premio from "../../Images/picture.svg"
import standby from "../../Images/chat-standby.svg"
import check from "../../Images/check.svg"
import close_chat from "../../Images/close-chat.svg"
import ask from "../../Images/ask.svg"
import paperclip from "../../Images/paperclip.svg"
import send from "../../Images/send-one.svg"
import api from '../../Api.js'
import api_msg from '../../ApiMsg.js'



export default function ChatUsuario() {

    const [messages, setMessages] = useState([]);
    const [demandaId, setDemandaId] = useState(14);

    function formatData(data) {
        let date_full = new Date(data);
        let day = date_full.getDay();
        let minute = date_full.getMinutes();
        let hour = date_full.getHours();
        let month = date_full.getMonth();
        let year = date_full.getFullYear();
        let time = hour + ':' + minute;
        let date_string = time + ' - ' + day + '/' + month + '/' + year;
        return date_string;
    }

    useEffect(() => {
        api_msg.get(`/message/${demandaId}`).then((res) => {
            if(res.status === 200) {
                setMessages(res.data)
            }else if(res.status === 204){
                setMessages([]);
            }
        })
        
    })

    function handleSubmitMessageText(event) {
        event.preventDefault();
        let input = document.getElementById('input_text')
        let messageJson = {
            "conteudo": input.value,
            "tipo": 'text',
            "demandaId": demandaId,
            "remetenteId": localStorage.getItem('petfinder_user_id'),
            "dataEnvio": new Date()
        }
        input.value = '';

        api_msg.post('/message', messageJson, { headers: { 'Content-Type': 'application/json' } })
    }

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
                        <div className="chat-user-demandas-header">
                            <p>Em Andamento</p>
                            <p>1</p>
                            <img src={standby} alt="seta para minimizar demandas em aberto" />
                        </div>
                        <div className="chat-user-demandas-header">
                            <p>Em Andamento</p>
                            <p>1</p>
                            <img src={standby} alt="seta para minimizar demandas em aberto" />
                        </div>
                    </div>

                    <div className="chat-user-container-chat">
                        <div className="chat-user-message-header">
                            <p className="chat-user-message-type">Adoção</p>
                            <p className="chat-user-message-receiver">Mylena Oliveira</p>
                            <div className="chat-user-demanda-action">
                                <p className="chat-user-action-description">Finalizar Demanda</p>
                                <img src={check} />
                                <img src={close_chat} />
                                <img src={ask} />
                            </div>
                        </div>

                        <div className="chat-user-message-container">
                            <div className="chat-user-message-section" id='chatSection'>
                                {
                                    messages.map((msg) => {
                                        return (<Mensagem content={msg.conteudo} idUsuario={msg.remetente.id} date={formatData(msg.dataEnvio)} id=''/>)
                                    }).reverse()
                                }
                            </div>
                            <div className="chat-user-message-input-container">
                                <input className="chat-user-message-input" type="text" id="input_text" />
                                <div className="chat-user-message-input-buttons">
                                    <img className="chat-user-message-send-file-button" src={paperclip} alt="Anexar arquivo" />
                                    <img className="chat-user-message-send-button" src={send} alt="Enviar mensagem" onClick={(e) => { handleSubmitMessageText(e) }} />
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </>
    );
}