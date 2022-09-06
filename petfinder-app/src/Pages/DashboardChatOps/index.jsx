import './DashboardChatOps.css';
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import React from "react";
import { Chart } from "react-google-charts";
import VLibras from "@djpfs/react-vlibras"
import { useEffect, useState } from "react";
import api from "../../Api"
import SideBarItem from '../../Components/SideBarItem';
import perfil from "../../Images/people.svg"
import demanda from "../../Images/attention-icon.svg"

export const dataDemadaMes = [
    ["", "Demanda"],
    ["Pagamento", 102], 
    ["Adoção", 127], 
    ["Resgate", 92]
];

export const dataDemadaSemana = [
    ["", "Demanda"],
    ["Pagamento", 38], 
    ["Adoção", 43], 
    ["Resgate", 35]
];

function DashboardChatOps() {

    const [infoUsuario, setInfoUsuario] = useState([])

    const [infoTotalEspera, setTotalEspera] = useState([])
    const [infoTotalConcluida, setTotalConcluida] = useState([])

    useEffect(() => {
        const infoUsuario = JSON.parse(localStorage.getItem('petfinder_user'));
        if (infoUsuario) {
            setInfoUsuario(infoUsuario);
        }

        api.get(`/demandas/instituicao/${infoUsuario.fkInstituicao.id}/em_andamento`).then((res) => {
            setTotalEspera(res.data)
        })
        api.get(`/demandas/instituicao/${infoUsuario.fkInstituicao.id}/aberto`).then((res) => {
            setTotalConcluida(res.data)
        })
    })

    return(
        <>
            <HeaderApp 
                sideItens={[
                    <SideBarItem label="Página Inicial" icon={perfil} navigateTo={"/dashboard-chatops"}/>,
                    <SideBarItem label="Demandas" icon={demanda} navigateTo={"/dashboard-chatops"}/>,
                    <SideBarItem label="Nova Dúvida" icon={demanda} navigateTo={"/dashboard-chatops"}/>,
                ]}
                
                itens={[
                    <NavItem label="Dashboard" />,
                    <NavItem label="Admin Cadastrados" />,
                    <NavItem label="Instituições Cadastrados" />,
                    <NavItem label="Dúvidas" />
                ]}
            />

            <div className="dashboard-chatops">
                <div className="dashboard-chatops-container">
                    <div className="dashboard-chatops-metricas-container">
                        <h2>Métricas de cadastro</h2>
                        <div className="dashboard-chatops-metricas-card-container">
                            <div className="dashboard-chatops-metricas-card">
                                <p>X</p>
                                <p>Em espera</p>
                            </div>

                            <div className="dashboard-chatops-metricas-card">
                                <p>X</p>
                                <p>Concluídas hoje</p>
                            </div>

                        </div>
                    </div>

                    <div className="dashboard-chatops-metricas-graficos-container">
                        
                        <div className="dashboard-chatops-metricas-graficos">
                            <div className="dashboard-chatops-metricas-grafico">
                                <h2>Demandas por semana</h2>
                                <div className="dashboard-chatops-metricas-grafico-container-1">
                                    <Chart
                                        chartType="PieChart"
                                        data={[["", "Demanda"], ["Sua equipe (com sucesso)", 38], ["Você (com sucesso)", 43], ["Sem sucesso", 35]]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>

                            <div className="dashboard-chatops-metricas-grafico">
                                <h2>Categorias de demandas mais frequentes por</h2>

                                <div className="dashboard-chatops-metricas-grafico-botoes">

                                    <input
                                        type="checkbox"
                                    />
                                    <button
                                        type="button"
                                        onClick={() => {
                                        }}
                                    >
                                        Semana
                                    </button>

                                    <input
                                        type="checkbox"
                                    />
                                    <button
                                        type="button"
                                        onClick={() => {
                                        }}
                                    >
                                        Mês
                                    </button>
                                </div>

                                <div className="dashboard-chatops-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={dataDemadaSemana}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
            
            <VLibras forceOnload={true}></VLibras>
        </>
    )
}

export default DashboardChatOps;