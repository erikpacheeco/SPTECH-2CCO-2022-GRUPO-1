import './DashboardAdmin.css';
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import React from "react";
import { Chart } from "react-google-charts";
import VLibras from "@djpfs/react-vlibras"
import { useEffect, useState } from "react";
import api from "../../Api"
import dash from "../../Images/data-graph.svg"
import pet from "../../Images/paw.svg"
import padrinho from "../../Images/padrinhos.svg"
import perfil from "../../Images/people.svg"
import perfilInstituicao from "../../Images/user-business.svg"
import colaborador from "../../Images/colaboradores.svg"
import duvida from "../../Images/duvida.svg"
import SideBarItem from '../../Components/SideBarItem';
import headerFunctions from "../../functions/headerFunctions";

export const data = [
    ["Mês", "Padrinhos", "Prêmios Postados"],
    ["Fev", 21, 10],
    ["Mar", 23, 7],
    ["Abr", 16, 2],
    ["Mai", 23, 9],
    ["Jun", 39, 20]
];

function DashboardAdmin() {
    const [infoUsuario, setInfoUsuario] = useState([])

    const [infoDashboard, setInfoDashboard] = useState([])
    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));


    useEffect(() => {
        const infoUsuario = JSON.parse(localStorage.getItem('petfinder_user'));
        if (infoUsuario) {
            setInfoUsuario(infoUsuario);
        }

        api.get(`/demandas/dashboard/${infoUsuario.id}`).then((res) => {
            setInfoDashboard(res.data)
        })
    })

    return (
        <>
            <HeaderApp
                sideItens={headerFunctions.sideBarNivelAcesso(objUser.nivelAcesso)}
                itens={headerFunctions.headerNivelAcesso(objUser.nivelnivelAcesso)}

            />

            <div className="dashboard-admin">
                <div className="dashboard-admin-container">
                    <div className="dashboard-admin-metricas-container">
                        <h2>Métricas de cadastro</h2>
                        <div className="dashboard-admin-metricas-card-container">
                            <div className="dashboard-admin-metricas-card">
                                <p>{infoDashboard.qtdPadrinhoInstituicao}</p>
                                <p>Padrinhos</p>
                            </div>

                            <div className="dashboard-admin-metricas-card">
                                <p>{infoDashboard.qtdResgatePendenteInstituicao}</p>
                                <p>Resgates Pendentes</p>
                            </div>

                            <div className="dashboard-admin-metricas-card">
                                <p>{infoDashboard.qtdPetAdotado}</p>
                                <p>Pet's Adotados</p>
                            </div>

                            <div className="dashboard-admin-metricas-card">
                                <p>{infoDashboard.qtdPremioPorPetInstituicao}</p>
                                <p>Prêmios por Pet</p>
                            </div>

                            <div className="dashboard-admin-metricas-card">
                                <p>{infoDashboard.qtdPetSemPremioInstiuicao}</p>
                                <p>Pet's sem Prêmio</p>
                            </div>
                        </div>
                    </div>

                    <div className="dashboard-admin-metricas-graficos-container">

                        <div className="dashboard-admin-metricas-graficos">
                            <div className="dashboard-admin-metricas-grafico">
                                <h2>Pets resgatados esse mês</h2>
                                <div className="dashboard-admin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={[["", "Resgate"], ["Fev", 38], ["Mar", 43], ["Abr", 35], ["Mai", 20], ["Jun", 44]]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>

                            <div className="dashboard-admin-metricas-grafico">
                                <h2>Padrinhos por mês</h2>
                                <div className="dashboard-admin-metricas-grafico-container">
                                    <Chart
                                        chartType="Line"
                                        data={data}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>
                        </div>

                        <div className="dashboard-admin-metricas-graficos">

                            <div className="dashboard-admin-metricas-grafico-1">
                                <h2>Prêmios adicionados por</h2>
                                <div className="dashboard-admin-metricas-grafico-botoes">

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
                                <div className="dashboard-admin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={[["", "Prêmios"], ["Fev", 38], ["Mar", 43], ["Abr", 35], ["Mai", 20], ["Jun", 44]]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>

                            <div className="dashboard-admin-metricas-grafico">
                                <h2>Categorias de demandas mais frequentes por</h2>

                                <div className="dashboard-admin-metricas-grafico-botoes">

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

                                <div className="dashboard-admin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={[["", "Demanda"], ["Pagamento", 38], ["Adoção", 43], ["Resgate", 35]]}
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

export default DashboardAdmin;