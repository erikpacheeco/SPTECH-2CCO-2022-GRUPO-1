import './DashboardAdmin.css';
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import React from "react";
import { Chart } from "react-google-charts";

export const data = [
    ["Mês", "Padrinhos", "Prêmios Postados"],
    ["Fev", 21, 10],
    ["Mar", 23, 7],
    ["Abr", 16, 2],
    ["Mai", 23, 9],
    ["Jun", 39, 20]
];

function DashboardAdmin() {

    return(
        <>
            <HeaderApp itens={[
                <NavItem label="Dashboard" />,
                <NavItem label="Admin Cadastrados" />,
                <NavItem label="Instituições Cadastrados" />,
                <NavItem label="Dúvidas" />
            ]}/>

            <div className="dashboard-admin">
                <div className="dashboard-admin-container">
                    <div className="dashboard-admin-metricas-container">
                        <h2>Métricas de cadastro</h2>
                        <div className="dashboard-admin-metricas-card-container">
                            <div className="dashboard-admin-metricas-card">
                                <p>X</p>
                                <p>Instituições</p>
                            </div>

                            <div className="dashboard-admin-metricas-card">
                                <p>X</p>
                                <p>Usuários</p>
                            </div>

                            <div className="dashboard-admin-metricas-card">
                                <p>X</p>
                                <p>Animais</p>
                            </div>

                            <div className="dashboard-admin-metricas-card">
                                <p>X</p>
                                <p>Padrinhos</p>
                            </div>

                            <div className="dashboard-admin-metricas-card">
                                <p>X</p>
                                <p>Administradores</p>
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
            
            
        </>
    )
}

export default DashboardAdmin;