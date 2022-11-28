import React, { useEffect, useState } from "react";
import "./chat-user.css"
import HeaderApp from "../../Components/HeaderApp";
import Mensagem from "../../Components/Mensagem";
import standby from "../../Images/chat-standby.svg"
import chat_down from "../../Images/chat-down.svg"
import check from "../../Images/check.svg"
import ask from "../../Images/ask.svg"
import paperclip from "../../Images/paperclip.svg"
import send from "../../Images/send-one.svg"
import api from '../../Api.js'
import api_msg from '../../ApiMsg.js'
import DemandaItem from "../../Components/DemandaItem";
import ActionButton from "../../Components/ActionButton";
import FileUploadModal from "../../Components/FileUploadModal";

export default function ChatUsuario() {

    const usuarioLogado = JSON.parse(localStorage.getItem("petfinder_user"));
    const [modalIsOpen, setModalIsOpen] = useState(false);
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
        status: null,
        descricao: "undefined"
    });

    const [idUltimoCliente, setIdUltimoCliente] = useState()

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

    function handleChangeDemandaAtual(demanda, evt) {
        setDemandaAtual({
            id: demanda.id,
            categoria: demanda.categoria,
            nome: usuarioLogado.nivelAcesso == "user" ? (demanda.colaborador == null ? "" : demanda.colaborador.nome) : demanda.usuario.nome,
            proximaAcao: JSON.parse(localStorage.getItem("petfinder_user")).nivelAcesso.toLowerCase() == "user" ? demanda.proximaAcaoUsuario : demanda.proximaAcaoColaborador,
            status: demanda.status,
            idUsuario: demanda.idUsuario
        });
    }

    function newDemandaItem(demanda) {
        return (<DemandaItem
            categoria={demanda.categoria}
            nome={usuarioLogado.nivelAcesso == "user" ? (demanda.colaborador == null ? "" : demanda.colaborador.nome) : demanda.usuario.nome}
            id={demanda.id}
            onClick={(evt) => handleChangeDemandaAtual(demanda, evt)}
            key={demanda.id}
            isSelected={demanda.id == demandaAtual.id}
        />);
    }

    function chooseColor() {
        if (demandaAtual.id !== '') {
            if (demandaAtual.categoria.toLowerCase() == "adocao") {
                return "chat-user-message-type chat-user-adocao"
            } else if (demandaAtual.categoria.toLowerCase() == "pagamento") {
                return "chat-user-message-type chat-user-pagamento"
            } else if (demandaAtual.categoria.toLowerCase() == "resgate") {
                return "chat-user-message-type chat-user-resgate"
            }
        } else {
            return "chat-user-hidden"
        }
    }

    useEffect(() => {
        api.get(`/demandas/chats/${JSON.parse(localStorage.getItem('petfinder_user')).id}`).then((res) => {
            setListaDemandaAberta(res.data.abertas)
            setListaDemandaAndamento(res.data.emAndamento)
            setListaDemandaConcluida(res.data.fechadas)
        });
    });

    useEffect(() => {
        if (demandaAtual.id !== "") {
            const interval = setInterval(() => {
                api_msg.get(`/message/${demandaAtual.id}`).then((res) => {
                    console.log("demanda atual: ", demandaAtual.id);
                    console.log("mensagens: ", res.data.length);
                    if (res.status == 200) {
                        setMessages(res.data)
                    } else if (res.status == 204) {
                        setMessages([]);
                    }
                });
            }, 100);
            return(() => clearInterval(interval));
        }

    }, [demandaAtual]);

    useEffect(() => {
        api.get("/usuarios/ultimo-cliente").then((res) => {
            setIdUltimoCliente("ultimo cadastro cliente "+res.data)
            console.log(res.data)
        })
    }, [])

    function handleSubmitMessageText(event) {
        event.preventDefault();
        let input = document.getElementById('input_text')
        let messageJson = {
            conteudo: input.value,
            tipo: 'texto',
            demandaId: demandaAtual.id,
            remetenteId: JSON.parse(localStorage.getItem('petfinder_user')).id,
            dataEnvio: new Date()
        }
        input.value = '';

        api_msg.post('/message', messageJson, { headers: { 'Content-Type': 'application/json' } })
    }

    function addingNewCliente() {
        if (("concluir demada".localeCompare(demandaAtual.proximaAcao.acao) && usuarioLogado.nivelAcesso == "adm") || usuarioLogado.nivelAcesso == "chatops") {
            let cliente = {
                id: idUltimoCliente+1,
                usuario_id: demandaAtual.idUsuario,
                tipo: "adm",
                dataCliente: new Date().toISOString()
            }
    
            api.post('/usuarios/cliente', cliente, { headers: { 'Content-Type': 'application/json' } })
        }
    }

    return (
        <>
            <HeaderApp/>
            <div className="chat-user-centralizer">
                <div className="chat-user-container">

                    <div className="chat-user-container-demandas">
                        <div className="chat-user-demandas-header" onClick={handleChangeAndamento}>
                            <div className={`qtd-status qtd-status-${listaDemandaAndamento.length == 0 ? "purple" : "yellow"}`}>{listaDemandaAndamento.length}</div>
                            <p className="chat-user-demandas-header-title">Em Andamento</p>
                            <div className="chat-user-demandas-header-actions">
                                <img className="chat-user-demandas-btn-icon" id='icon_andamento' src={demandasStatus[0] ? standby : chat_down} alt="seta para minimizar demandas em aberto" />
                            </div>
                        </div>

                        <div className={demandasStatus[0] ? "chat-user-demandas-list" : " chat-user-hidden"}>
                            {
                                listaDemandaAndamento.map((demanda) => newDemandaItem(demanda))
                            }
                        </div>

                        <div className="chat-user-demandas-header"  onClick={handleChangeAbertas}>
                            <div className={`qtd-status qtd-status-${listaDemandaAberta.length == 0 ? "purple" : "green"}`}>{listaDemandaAberta.length}</div>
                            <p className="chat-user-demandas-header-title">Abertas</p>
                            <div className="chat-user-demandas-header-actions">
                                <img className="chat-user-demandas-btn-icon" id='icon_abertas' src={demandasStatus[1] ? standby : chat_down} alt="seta para minimizar demandas em aberto" />
                            </div>
                        </div>
                        <div className={demandasStatus[1] ? "chat-user-demandas-list" : " chat-user-hidden"}>
                            {
                                listaDemandaAberta.map((demanda) => newDemandaItem(demanda))
                            }
                        </div>
                        <div className="chat-user-demandas-header" onClick={handleChangeConcluidas}>
                            <div className={`qtd-status qtd-status-purple`}>{listaDemandaConcluida.length}</div>
                            <p className="chat-user-demandas-header-title">Concluidas</p>
                            <div className="chat-user-demandas-header-actions">
                                <img className="chat-user-demandas-btn-icon" id='icon_concluidas' src={demandasStatus[2] ? standby : chat_down} alt="seta para minimizar demandas em aberto" />
                            </div>
                        </div>
                        <div className={demandasStatus[2] ? "chat-user-demandas-list" : " chat-user-hidden"}>
                            {
                                listaDemandaConcluida.map((demanda) => newDemandaItem(demanda))
                            }
                        </div>
                    </div>

                    <div className="chat-user-container-chat">
                        <FileUploadModal 
                            isOpen={modalIsOpen} 
                            setModalIsOpen={setModalIsOpen} 
                            remetenteId={JSON.parse(localStorage.getItem('petfinder_user')).id}
                            demandaId={demandaAtual.id}
                        />
                        <div className="chat-user-message-header">
                            <p className={chooseColor()}>{`#${demandaAtual.id}`}</p>
                            <p className={demandaAtual.id == '' ? "chat-user-hidden" : "chat-user-message-receiver"}>{demandaAtual.nome}</p>
                            <div className={demandaAtual.id == '' ? "chat-user-hidden" : "chat-user-demanda-action"}>
                                <p className="chat-user-action-description">{demandaAtual.proximaAcao.texto}</p>
                                <img className="chat-user-hidden" src={check} alt="" />
                                {demandaAtual.proximaAcao.tipoBotao == "accept" ? <ActionButton type="accept" demandaId={demandaAtual.id} userId={usuarioLogado.id} handleChangeDemandaAtual={handleChangeDemandaAtual} /> : ""}
                                {demandaAtual.proximaAcao.tipoBotao == "accept/decline" ? <>
                                    <ActionButton type="accept" demandaId={demandaAtual.id} userId={usuarioLogado.id} handleChangeDemandaAtual={handleChangeDemandaAtual} onClick={addingNewCliente}/>
                                    <ActionButton type="decline" demandaId={demandaAtual.id} userId={usuarioLogado.id} handleChangeDemandaAtual={handleChangeDemandaAtual} />
                                </> : ""}
                                {demandaAtual.proximaAcao.tipoBotao == "decline" ? <ActionButton type="decline" demandaId={demandaAtual.id} userId={usuarioLogado.id} handleChangeDemandaAtual={handleChangeDemandaAtual} /> : ""}
                                <img src={ask} alt="" title={demandaAtual.proximaAcao.descricao} />
                            </div>
                        </div>

                        <div className="chat-user-message-container">
                            <div className="chat-user-message-section" id='chatSection'>
                                {
                                messages.map((msg, index) => {
                                    return (<Mensagem key={index} content={msg.conteudo} idUsuario={msg.remetente.id} date={msg.dataEnvio} id={msg.id} tipo={msg.tipo}/>)}).reverse()
                                }
                            </div>
                            <div className="chat-user-message-input-container">
                                <input className="chat-user-message-input" type="text" id="input_text" />
                                <div className="chat-user-message-input-buttons">
                                    <img className="chat-user-message-send-file-button" src={paperclip} alt="Anexar arquivo" onClick={() => {
                                        if(demandaAtual.id !== "") {
                                            setModalIsOpen(!modalIsOpen)}
                                        }
                                    }/>
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