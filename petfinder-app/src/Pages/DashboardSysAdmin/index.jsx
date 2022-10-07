import './DashboardSysAdmin.css';
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import React from "react";
import { Chart } from "react-google-charts";
import VLibras from "@djpfs/react-vlibras"
import { useEffect, useState } from "react";
import api from "../../Api";

export const dataApadrinhamentoMes = [
    ["Mês", "Padrinhos", "Mimos Postados"],
    ["Fev", 21, 10],
    ["Mar", 23, 7],
    ["Abr", 16, 2],
    ["Mai", 23, 9],
    ["Jun", 39, 20]
];

function DashboardSysAdmin() {

    const infoUsuario = JSON.parse(localStorage.getItem('petfinder_user'));

    const [infoDashboard, setInfoDashboard] = useState([]);

    useEffect(() => {

        api.get(`/demandas/dashboard/${infoUsuario.id}`).then((res) => {
            setInfoDashboard(res.data)
        })
        
    }, [])

    console.log(infoDashboard)

    return (
        <>
            <HeaderApp/>

            <div className="dashboard-sysadmin">
                <div className="dashboard-sysadmin-container">
                    <div className="dashboard-sysadmin-metricas-container">
                        <h2>Métricas de cadastro</h2>
                        <div className="dashboard-sysadmin-metricas-card-container">
                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{infoDashboard.qtdInstituicao}</p>
                                <p>Instituições</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{infoDashboard.qtdUsuario}</p>
                                <p>Usuários</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{infoDashboard.qtdAnimal}</p>
                                <p>Animais</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{infoDashboard.qtdPadrinho}</p>
                                <p>Padrinhos</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{infoDashboard.qtdAdmin}</p>
                                <p>Administradores</p>
                            </div>
                        </div>
                    </div>

                    <div className="dashboard-sysadmin-metricas-graficos-container">

                        <div className="dashboard-sysadmin-metricas-graficos">
                            <div className="dashboard-sysadmin-metricas-grafico">
                                <h2>Conversão de visitantes por mês</h2>
                                <div className="dashboard-sysadmin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={[["", "Visitantes", "Usuários"], ["Jan", 20, 9], ["Fev", 31, 12], ["Mar", 27, 5], ["Abr", 33, 15], ["Mai", 28, 20], ["Jun", 25, 22]]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>

                            <div className="dashboard-sysadmin-metricas-grafico">
                                <h2>Conversão de leads usuário por mês</h2>
                                <div className="dashboard-sysadmin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={[["", "Cadastrados", "Clientes"], ["Jan", 20, 9], ["Fev", 31, 12], ["Mar", 27, 5], ["Abr", 33, 15], ["Mai", 28, 20], ["Jun", 25, 22]]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>
                        </div>

                        <div className="dashboard-sysadmin-metricas-graficos">

                            <div className="dashboard-sysadmin-metricas-grafico">
                                <h2>Conversão de leads instituição por mês</h2>
                                <div className="dashboard-sysadmin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        // data={[["", "Adoções"], ["Fev", 38], ["Mar", 43], ["Abr", 35], ["Mai", 20], ["Jun", 44]]}
                                        data={[["", "Cadastradas", "Clientes"], ["Jan", 20, 9], ["Fev", 31, 12], ["Mar", 27, 5], ["Abr", 33, 15], ["Mai", 28, 20], ["Jun", 25, 22]]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>

                            {/* <div className="dashboard-sysadmin-metricas-grafico">
                                <h2>Reporte de resgate por mês</h2>
                                <div className="dashboard-sysadmin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={[["", "Reporte"], ["Fev", 38], ["Mar", 43], ["Abr", 35], ["Mai", 20], ["Jun", 44]]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div> */}

                        </div>
                    </div>

                </div>
            </div>

            <VLibras forceOnload={true}></VLibras>
        </>
    )
}

export default DashboardSysAdmin;