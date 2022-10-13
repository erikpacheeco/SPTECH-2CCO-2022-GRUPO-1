import './DashboardAdmin.css';
import HeaderApp from "../../Components/HeaderApp";
import React from "react";
import { Chart } from "react-google-charts";
import VLibras from "@djpfs/react-vlibras"
import { useEffect, useState } from "react";
import api from "../../Api";
import PetListItem from '../../Components/PetListItem';

export const data = [
    ["", "Padrinhos", "Prêmios Postados"],
    ["Fev", 21, 10],
    ["Mar", 23, 7],
    ["Abr", 16, 2],
    ["Mai", 23, 9],
    ["Jun", 39, 20]
];

export const dataPremioSemana = [
    ["", "Prêmios"], 
    ["Seg", 3], 
    ["Ter", 0], 
    ["Qua", 2], 
    ["Qui", 8], 
    ["Sex", 10],
    ["Sab", 5],
    ["Dom", 0]
]

export const dataPremioMes = [
    ["", "Prêmios"], 
    ["Fev", 38], 
    ["Mar", 43], 
    ["Abr", 35], 
    ["Mai", 20], 
    ["Jun", 44]
]

export const dataDemandaSemana = [
    ["", "Pagamento", "Adoção"], 
    ["Seg", 2, 1], 
    ["Ter", 0, 2], 
    ["Qua", 0, 0], 
    ["Qui", 2, 2], 
    ["Sex", 7, 4], 
    ["Sab", 9, 6], 
    ["Dom", 4, 3]
]

export const dataDemandaMes = [
    ["", "Pagamento", "Adoção"], 
    ["Jan", 20, 16], 
    ["Fev", 27, 18], 
    ["Mar", 40, 34], 
    ["Abr", 38, 36], 
    ["Mai", 29, 30], 
    ["Jun", 42, 44]
]
export const dataPadrinhoMes = [
    ["", "Padrinhos"], 
    ["Jan", 20], 
    ["Fev", 18], 
    ["Mar", 40], 
    ["Abr", 38], 
    ["Mai", 30], 
    ["Jun", 42]
]

export const dataPadrinhoSemana = [
    ["", "Padrinhos"], 
    ["Seg", 2], 
    ["Ter", 0], 
    ["Qua", 1], 
    ["Qui", 2], 
    ["Sex", 4], 
    ["Sab", 9], 
    ["Dom", 4]
]

function DashboardAdmin() {
    
    const infoUsuario = JSON.parse(localStorage.getItem('petfinder_user'));
    
    const [infoDashboard, setInfoDashboard] = useState([]);
    
    const [valorDataPremio, setValorDataPremio] = useState(dataPremioSemana);
    const [valorDataDemanda, setValorDataDemanda] = useState(dataDemandaSemana);
    const [valorDataPadrinho, setValorDataPadrinho] = useState(dataPadrinhoSemana);

    var trocaBtnPremio = true;
    var trocaBtnDemanda = true;
    var trocaBtnPadrinho = true;

    useEffect(() => {
        
        api.get(`/demandas/dashboard/${infoUsuario.id}`).then((res) => {
            setInfoDashboard(res.data)
        })

    }, [])

    return (
        <>
            <HeaderApp/>

            <div className="dashboard-admin">
                <div className="dashboard-admin-container">
                    <div className="dashboard-admin-metricas-container">
                        <h2>Métricas de cadastro</h2>
                        <div className="dashboard-admin-metricas-card-container">
                            <div className="dashboard-admin-metricas-card">
                                <p>{infoDashboard.qtdPadrinhoInstituicao}</p>
                                <p>Padrinhos</p>
                            </div>

                            {/* <div className="dashboard-admin-metricas-card">
                                <p>{infoDashboard.qtdResgatePendenteInstituicao}</p>
                                <p>Resgates Pendentes</p>
                            </div> */}

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

                            <div className="dashboard-admin-metricas-grafico-1">
                                <h2>Prêmios adicionados por</h2>
                                <div className="dashboard-admin-metricas-grafico-botoes">

                                    <button
                                        type="button"
                                        className="btn-semana-premio"
                                        id='btn-semana-premio'
                                        onClick={() => {
                                            if(trocaBtnPremio){
                                                let btnSemanaPremio = document.getElementById("btn-semana-premio");
                                                btnSemanaPremio.style.backgroundColor = "#7F2AB5";
                                                btnSemanaPremio.style.color = "white";

                                                let btnMesPremio = document.getElementById("btn-mes-premio");
                                                btnMesPremio.style.backgroundColor = "white";
                                                btnMesPremio.style.color = "#7F2AB5";
                                            }
                                            setValorDataPremio(dataPremioSemana)
                                        }}
                                    >
                                        Semana
                                    </button>

                                    <button
                                        type="button"
                                        className="btn-mes-premio"
                                        id='btn-mes-premio'
                                        onClick={() => {
                                            if(trocaBtnPremio){
                                                let btnSemanaPremio = document.getElementById("btn-semana-premio");
                                                btnSemanaPremio.style.backgroundColor = "white";
                                                btnSemanaPremio.style.color = "#7F2AB5";

                                                let btnMesPremio = document.getElementById("btn-mes-premio");
                                                btnMesPremio.style.backgroundColor = "#7F2AB5";
                                                btnMesPremio.style.color = "white";
                                            }
                                            setValorDataPremio(dataPremioMes);
                                        }}
                                    >
                                        Mês
                                    </button>
                                </div>
                                <div className="dashboard-admin-metricas-grafico-container">                            
                                    <Chart
                                        id="chart-premio"
                                        chartType="Bar"
                                        data={valorDataPremio}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>

                            <div className="dashboard-admin-metricas-grafico">
                                <h2>Categorias de demandas mais frequentes por</h2>

                                <div className="dashboard-admin-metricas-grafico-botoes">

                                    <button
                                        type="button"
                                        className="btn-semana-demanda"
                                        id='btn-semana-demanda'
                                        onClick={() => {
                                            if(trocaBtnDemanda){
                                                let btnSemanaDemanda = document.getElementById("btn-semana-demanda");
                                                btnSemanaDemanda.style.backgroundColor = "#7F2AB5";
                                                btnSemanaDemanda.style.color = "white";

                                                let btnMesDemanda = document.getElementById("btn-mes-demanda");
                                                btnMesDemanda.style.backgroundColor = "white";
                                                btnMesDemanda.style.color = "#7F2AB5";
                                            }
                                            setValorDataDemanda(dataDemandaSemana);
                                        }}
                                    >
                                        Semana
                                    </button>

                                    <button
                                        type="button"
                                        className="btn-mes-demanda"
                                        id='btn-mes-demanda'
                                        onClick={() => {
                                            if(trocaBtnDemanda){
                                                let btnSemanaDemanda = document.getElementById("btn-semana-demanda");
                                                btnSemanaDemanda.style.backgroundColor = "white";
                                                btnSemanaDemanda.style.color = "#7F2AB5";

                                                let btnMesDemanda = document.getElementById("btn-mes-demanda");
                                                btnMesDemanda.style.backgroundColor = "#7F2AB5";
                                                btnMesDemanda.style.color = "white";
                                            }
                                            setValorDataDemanda(dataDemandaMes);
                                        }}
                                    >
                                        Mês
                                    </button>
                                </div>

                                <div className="dashboard-admin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={valorDataDemanda}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>

                        </div>

                        <div className="dashboard-admin-metricas-graficos">

                            <div className="dashboard-admin-metricas-grafico-1">
                                <h2>Padrinhos por</h2>
                                <div className="dashboard-admin-metricas-grafico-botoes">

                                    <button
                                        type="button"
                                        className="btn-semana-padrinho"
                                        id='btn-semana-padrinho'
                                        onClick={() => {
                                            if(trocaBtnPadrinho){
                                                let btnSemanaPadrinho = document.getElementById("btn-semana-padrinho");
                                                btnSemanaPadrinho.style.backgroundColor = "#7F2AB5";
                                                btnSemanaPadrinho.style.color = "white";

                                                let btnMesPadrinho = document.getElementById("btn-mes-padrinho");
                                                btnMesPadrinho.style.backgroundColor = "white";
                                                btnMesPadrinho.style.color = "#7F2AB5";
                                            }
                                            setValorDataPadrinho(dataPadrinhoSemana)
                                        }}
                                    >
                                        Semana
                                    </button>

                                    <button
                                        type="button"
                                        className="btn-mes-padrinho"
                                        id='btn-mes-padrinho'
                                        onClick={() => {
                                            if(trocaBtnPadrinho){
                                                let btnSemanaPadrinho = document.getElementById("btn-semana-padrinho");
                                                btnSemanaPadrinho.style.backgroundColor = "white";
                                                btnSemanaPadrinho.style.color = "#7F2AB5";

                                                let btnMesPadrinho = document.getElementById("btn-mes-padrinho");
                                                btnMesPadrinho.style.backgroundColor = "#7F2AB5";
                                                btnMesPadrinho.style.color = "white";
                                            }
                                            setValorDataPadrinho(dataPadrinhoMes);
                                        }}
                                    >
                                        Mês
                                    </button>
                                </div>
                                <div className="dashboard-admin-metricas-grafico-container">                            
                                    <Chart
                                        id="chart-premio"
                                        chartType="Bar"
                                        data={valorDataPadrinho}
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