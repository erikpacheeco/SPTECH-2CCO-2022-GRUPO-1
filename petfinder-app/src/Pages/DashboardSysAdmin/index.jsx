import './DashboardSysAdmin.css';
import HeaderApp from "../../Components/HeaderApp";
import React from "react";
import { Chart } from "react-google-charts";
import VLibras from "@djpfs/react-vlibras"
import { useEffect, useState } from "react";
import api from "../../Api";
import {toNumberAndInvert} from "../../functions/util";

export const options = {
    is3D: true
};

function DashboardSysAdmin() {

    const authedUser = JSON.parse(localStorage.getItem('petfinder_user')); 

    // cards
    const [instituicoes, setInstituicoes] = useState(0);
    const [usuarios, setUsuarios] = useState(0);
    const [pets, setPets] = useState(0);
    const [padrinhos, setPadrinhos] = useState(0);
    const [administradores, setAdministradores] = useState(0);

    // charts
    const [chartVisitantesPorMes, setChartVisitantesPorMes] = useState(null);
    const [chartLeadsClientes, setChartLeadsClientes] = useState(null);
    const [chartLeadsClientesInst, setChartLeadsClientesInst] = useState(null);

    // request
    useEffect(() => {
        api.get(`/dashboard/sysadmin/${authedUser.id}`)
        .then(({status, data}) => {
            if(status == 200) {
                // cards
                setInstituicoes(data.instituicoes);
                setUsuarios(data.usuarios);
                setPets(data.pets);
                setPadrinhos(data.padrinhos);
                setAdministradores(data.administradores);

                // charts
                setChartVisitantesPorMes(toNumberAndInvert(data.chartVisitantesUsuarios));
                setChartLeadsClientes(toNumberAndInvert(data.chartLeadsClientes));
                setChartLeadsClientesInst(toNumberAndInvert(data.chartLeadsClientesInstituicao));
            } 
        })
        .catch(err => {
            console.log(err);
        })
    }, []);

    return (
        <>
            <HeaderApp />

            <div className="dashboard-sysadmin" >
                <div className="dashboard-sysadmin-container">
                    <div className="dashboard-sysadmin-metricas-container">
                        <h2>Métricas de cadastro</h2>
                        <div className="dashboard-sysadmin-metricas-card-container">
                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{instituicoes}</p>
                                <p>Instituições</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{usuarios}</p>
                                <p>Usuários</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{pets}</p>
                                <p>Animais</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{padrinhos}</p>
                                <p>Padrinhos</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{administradores}</p>
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
                                        data={chartVisitantesPorMes || [
                                            ["Mês", "Qtd Visitantes", "Qtd Usuários"],
                                            ["2022/06", 0, 0],
                                            ["2022/07", 0, 0],
                                            ["2022/08", 0, 0],
                                            ["2022/09", 0, 0],
                                            ["2022/10", 0, 0],
                                            ["2022/11", 0, 0]
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
                                        data={chartLeadsClientes || [
                                            ["Mês", "Qtd Leads", "Qtd Clientes"],
                                            ["2022/06", 0, 0],
                                            ["2022/07", 0, 0],
                                            ["2022/08", 0, 0],
                                            ["2022/09", 0, 0],
                                            ["2022/10", 0, 0],
                                            ["2022/11", 0, 0]
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
                                        data={chartLeadsClientesInst || [
                                            ["Ativas", "Inativas"],
                                            ["Ativas", 0],
                                            ["Inativas", 0],
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