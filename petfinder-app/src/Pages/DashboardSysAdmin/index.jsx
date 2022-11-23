import './DashboardSysAdmin.css';
import HeaderApp from "../../Components/HeaderApp";
import React from "react";
import { Chart } from "react-google-charts";
import VLibras from "@djpfs/react-vlibras"
import { useEffect, useState } from "react";
import api from "../../Api";

export const options = {
    is3D: true
};

function DashboardSysAdmin() {

    return (
        <>
            <HeaderApp />

            <div className="dashboard-sysadmin" >
                <div className="dashboard-sysadmin-container">
                    <div className="dashboard-sysadmin-metricas-container">
                        <h2>Métricas de cadastro</h2>
                        <div className="dashboard-sysadmin-metricas-card-container">
                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{10}</p>
                                <p>Instituições</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{20}</p>
                                <p>Usuários</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{21}</p>
                                <p>Animais</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{24}</p>
                                <p>Padrinhos</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{44}</p>
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
                                        data={[
                                            ["Mês", "Qtd Visitantes", "Qtd Usuários"],
                                            ["2022/06", 10, 15],
                                            ["2022/07", 12, 12],
                                            ["2022/08", 15, 16],
                                            ["2022/09", 11, 11],
                                            ["2022/10", 14, 15],
                                            ["2022/11", 12, 14]
                                        ]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>

                            <div className="dashboard-sysadmin-metricas-grafico">
                                <h2>Conversão de leads para clientes</h2>
                                <div className="dashboard-sysadmin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={[
                                            ["Mês", "Qtd Leads", "Qtd Clientes"],
                                            ["2022/06", 10, 15],
                                            ["2022/07", 12, 14],
                                            ["2022/08", 15, 11],
                                            ["2022/09", 11, 16],
                                            ["2022/10", 12, 15],
                                            ["2022/11", 13, 12]
                                        ]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>
                        </div>

                        <div className="dashboard-sysadmin-metricas-graficos">

                            <div className="dashboard-sysadmin-metricas-grafico">
                                <h2>Conversão de leads para clientes instituição no mês anterior</h2>
                                <div className="dashboard-sysadmin-metricas-grafico-container">
                                    <Chart
                                        chartType="PieChart"
                                        data={[
                                            ["Ativas", "Inativas"],
                                            ["Ativas", 4],
                                            ["Inativas", 1],
                                        ]}
                                        options={{
                                            title: "",
                                        }}
                                        width={"100%"}
                                        height={"100%"}
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

export default DashboardSysAdmin;