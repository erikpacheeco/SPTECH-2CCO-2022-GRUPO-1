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
import chat_down from "../../Images/chat-down.svg"
import check from "../../Images/check.svg"
import close_chat from "../../Images/close-chat.svg"
import ask from "../../Images/ask.svg"
import paperclip from "../../Images/paperclip.svg"
import send from "../../Images/send-one.svg"
import api from '../../Api.js'
import api_msg from '../../ApiMsg.js'
import DemandaItem from "../../Components/DemandaItem";
import ActionButton from "../../Components/ActionButton";

export default function ChatUsuario() {

    const usuarioLogado = JSON.parse(localStorage.getItem("petfinder_user"));
    const [messages, setMessages] = useState([]);
    const [listaDemandaAberta, setListaDemandaAberta] = useState([]);
    const [listaDemandaAndamento, setListaDemandaAndamento] = useState([]);
    const [listaDemandaConcluida, setListaDemandaConcluida] = useState([]);
    const [demandasStatus, setDemandasStatus] = useState([true, false, false]);
    const [demandaAtual, setDemandaAtual] = useState({
        id: "",
        categoria: "",
        nome: "",
        proximaAcao: {
            texto: null,
            tipoBotao: null,
            acao: null,
        },
        status: null
    });

    function handleChangeAndamento() {
        if (demandasStatus[0]) setDemandasStatus([false, demandasStatus[1], demandasStatus[2]]);
        else setDemandasStatus([true, demandasStatus[1], demandasStatus[2]]);
    }

    function handleChangeAbertas() {
        if (demandasStatus[1]) setDemandasStatus([demandasStatus[0], false, demandasStatus[2]]);
        else setDemandasStatus([demandasStatus[0], true, demandasStatus[2]]);
    }

    function handleChangeConcluidas() {
        if (demandasStatus[2]) setDemandasStatus([demandasStatus[0], demandasStatus[1], false]);
        else setDemandasStatus([demandasStatus[0], demandasStatus[1], true]);
    }

    function handleChangeDemandaAtual(demanda) {
        setDemandaAtual({
            id: demanda.id,
            categoria: demanda.categoria,
            nome: usuarioLogado.nivelAcesso === "user" ? (demanda.colaborador === null ? "" : demanda.colaborador.nome) : demanda.usuario.nome,
            proximaAcao: JSON.parse(localStorage.getItem("petfinder_user")).nivelAcesso === "user" ? demanda.proximaAcaoUsuario : demanda.proximaAcaoColaborador,
            status: demanda.status,
        })
    }

    function newDemandaItem(demanda) {
        return (<DemandaItem
            categoria={demanda.categoria}
            nome={usuarioLogado.nivelAcesso === "user" ? (demanda.colaborador === null ? "" : demanda.colaborador.nome) : demanda.usuario.nome}
            id={demanda.id}
            onClick={() => handleChangeDemandaAtual(demanda)}
            key={demanda.id}
        />);
    }

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

    function chooseColor() {
        if (demandaAtual.id !== '') {
            if (demandaAtual.categoria.toLowerCase() === "adocao") {
                return "chat-user-message-type chat-user-adocao"
            } else if (demandaAtual.categoria.toLowerCase() === "pagamento") {
                return "chat-user-message-type chat-user-pagamento"
            } else if (demandaAtual.categoria.toLowerCase() === "resgate") {
                return "chat-user-message-type chat-user-resgate"
            }
        } else {
            return "chat-user-hidden"
        }
    }

    useEffect(() => {
        if (demandaAtual.id !== "") {
            api_msg.get(`/message/${demandaAtual.id}`).then((res) => {
                if (res.status === 200) {
                    setMessages(res.data)
                } else if (res.status === 204) {
                    setMessages([]);
                }
            });
        }
        api.get(`/demandas/chats/${localStorage.getItem('petfinder_user_id')}`).then((res) => {
            setListaDemandaAberta(res.data.abertas)
            setListaDemandaAndamento(res.data.emAndamento)
            setListaDemandaConcluida(res.data.fechadas)
        });
    });

    function handleSubmitMessageText(event) {
        event.preventDefault();
        let input = document.getElementById('input_text')
        let messageJson = {
            "conteudo": input.value,
            "tipo": 'text',
            "demandaId": demandaAtual.id,
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
                    <SideBarItem label="Mensagens" icon={message} navigateTo="/chat" />,
                    <SideBarItem label="Meu Perfil" icon={perfil} navigateTo="" />,
                    <SideBarItem label="Meus Prêmios" icon={premio} navigateTo="/meus-premios" />
                ]}

                itens={[
                    <NavItem label="Página Inicial" navigateTo="/home-user" />,
                    <NavItem label="Meus Prêmios" navigateTo="/meus-premios" />,
                    <NavItem isSelected={true} label="Mensagens" navigateTo="/chat" />
                ]}
            />
            <div className="chat-user-centralizer">
                <div className="chat-user-container">

                    <div className="chat-user-container-demandas">
                        <div className="chat-user-demandas-header">
                            <p className="chat-user-demandas-header-title">Em Andamento</p>
                            <div className="chat-user-demandas-header-actions">
                                <img className="chat-user-demandas-btn-icon" id='icon_andamento' src={demandasStatus[0] ? standby : chat_down} alt="seta para minimizar demandas em aberto" onClick={handleChangeAndamento} />
                            </div>
                        </div>

                        <div className={demandasStatus[0] ? "chat-user-demandas-list" : " chat-user-hidden"}>
                            {
                                listaDemandaAndamento.map((demanda) => newDemandaItem(demanda))
                            }
                        </div>

                        <div className="chat-user-demandas-header">
                            <p className="chat-user-demandas-header-title">Abertas</p>
                            <div className="chat-user-demandas-header-actions">
                                <img className="chat-user-demandas-btn-icon" id='icon_abertas' src={demandasStatus[1] ? standby : chat_down} alt="seta para minimizar demandas em aberto" onClick={handleChangeAbertas} />
                            </div>
                        </div>
                        <div className={demandasStatus[1] ? "chat-user-demandas-list" : " chat-user-hidden"}>
                            {
                                listaDemandaAberta.map((demanda) => newDemandaItem(demanda))
                            }
                        </div>
                        <div className="chat-user-demandas-header">
                            <p className="chat-user-demandas-header-title">Concluidas</p>
                            <div className="chat-user-demandas-header-actions">
                                <img className="chat-user-demandas-btn-icon" id='icon_concluidas' src={demandasStatus[2] ? standby : chat_down} alt="seta para minimizar demandas em aberto" onClick={handleChangeConcluidas} />
                            </div>
                        </div>
                        <div className={demandasStatus[2] ? "chat-user-demandas-list" : " chat-user-hidden"}>
                            {
                                listaDemandaConcluida.map((demanda) => newDemandaItem(demanda))
                            }
                        </div>
                    </div>

                    <div className="chat-user-container-chat">
                        <div className="chat-user-message-header">
                            <p className={chooseColor()}>{demandaAtual.categoria.toLowerCase()}</p>
                            <p>#{demandaAtual.id}</p>
                            <p className={demandaAtual.id === '' ? "chat-user-hidden" : "chat-user-message-receiver"}>{demandaAtual.nome}</p>
                            <div className={demandaAtual.id === '' ? "chat-user-hidden" : "chat-user-demanda-action"}>
                                <p className="chat-user-action-description">{demandaAtual.proximaAcao.texto}</p>
                                <img className="chat-user-hidden" src={check} alt="" />
                                {demandaAtual.proximaAcao.tipoBotao === "accept" ? <ActionButton type="accept" demandaId={demandaAtual.id} userId={usuarioLogado.id} handleChangeDemandaAtual={handleChangeDemandaAtual}/> : ""}
                                {demandaAtual.proximaAcao.tipoBotao === "accept/decline" ? <>
                                    <ActionButton type="accept" demandaId={demandaAtual.id} userId={usuarioLogado.id} handleChangeDemandaAtual={handleChangeDemandaAtual}/>
                                    <ActionButton type="decline" demandaId={demandaAtual.id} userId={usuarioLogado.id} handleChangeDemandaAtual={handleChangeDemandaAtual}/>
                                </> : ""}
                                {demandaAtual.proximaAcao.tipoBotao === "decline" ? <ActionButton type="decline" demandaId={demandaAtual.id} userId={usuarioLogado.id} handleChangeDemandaAtual={handleChangeDemandaAtual}/> : ""}
                                <img src={ask} alt="" />
                            </div>
                        </div>

                        <div className="chat-user-message-container">
                            <div className="chat-user-message-section" id='chatSection'>
                                {
                                    messages.map((msg, index) => {
                                        return (<Mensagem key={index} content={msg.conteudo} idUsuario={msg.remetente.id} date={formatData(msg.dataEnvio)} id='' />)
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