import './DashboardAdmin.css';
import HeaderApp from "../../Components/HeaderApp";
import React from "react";
import { Chart } from "react-google-charts";
import VLibras from "@djpfs/react-vlibras"
import { useEffect, useState } from "react";
import api from "../../Api";

function DashboardAdmin() {
    
    const infoUsuario = JSON.parse(localStorage.getItem('petfinder_user'));
    
    const [infoDashboard, setInfoDashboard] = useState([]);

    const [infoPadrinhosSemana, setInfoPadrinhosSemana] = useState([]);
    const [infoPadrinhosMes, setInfoPadrinhosMes] = useState([]);

    const [infoDemandaSemanaAdocao, setInfoDemandaSemanaAdocao] = useState([]);
    const [infoDemandaSemanaPagamento, setInfoDemandaSemanaPagamento] = useState([]);
    const [infoDemandaMesAdocao, setInfoDemandaMesAdocao] = useState([]);
    const [infoDemandaMesPagamento, setInfoDemandaMesPagamento] = useState([]);

    const [infoPremiosSemana, setInfoPremiosSemana] = useState([]);
    const [infoPremiosMes, setInfoPremiosMes] = useState([]);

    const [infoDia1, setInfoDia1] = useState([]);
    const [infoDia2, setInfoDia2] = useState([]);
    const [infoDia3, setInfoDia3] = useState([]);
    const [infoDia4, setInfoDia4] = useState([]);
    const [infoDia5, setInfoDia5] = useState([]);
    const [infoDia6, setInfoDia6] = useState([]);
    const [infoDia7, setInfoDia7] = useState([]);

    const [infoMes1, setInfoMes1] = useState([]);
    const [infoMes2, setInfoMes2] = useState([]);
    const [infoMes3, setInfoMes3] = useState([]);
    const [infoMes4, setInfoMes4] = useState([]);
    const [infoMes5, setInfoMes5] = useState([]);
    const [infoMes6, setInfoMes6] = useState([]);

    var trocaBtnPremio = true;
    var trocaBtnDemanda = true;
    var trocaBtnPadrinho = true;

    useEffect(() => {

        const interval = setInterval(() => {
            document.querySelector("#btn-semana-padrinho").click();
            document.querySelector("#btn-semana-demanda").click();
            document.querySelector("#btn-semana-premio").click();
            return clearInterval(interval);
        }, 400);

    }, []);

    // cads 
    useEffect(() => {
        
        api.get(`/demandas/dashboard/${infoUsuario.id}`).then((res) => {
            setInfoDashboard(res.data)
        })

        const dataAtual = new Date();
        const mesAtual = dataAtual.getMonth() + 1;
        const diaAtual = dataAtual.getDate();

        setInfoDia7(diaAtual)
        setInfoDia6(diaAtual-1)
        setInfoDia5(diaAtual-2)
        setInfoDia4(diaAtual-3)
        setInfoDia3(diaAtual-4)
        setInfoDia2(diaAtual-5)
        setInfoDia1(diaAtual-6)

        setInfoMes6(mesAtual)
        setInfoMes5(mesAtual-1)
        setInfoMes4(mesAtual-2)
        setInfoMes3(mesAtual-3)
        setInfoMes2(mesAtual-4)
        setInfoMes1(mesAtual-5)

    }, [])

    // grafico padrinhos
    useEffect(() => {
        
        api.get(`/demandas/padrinhos/${infoUsuario.fkInstituicao.id}`).then((res) => {
            setInfoPadrinhosSemana(res.data[0])
            setInfoPadrinhosMes(res.data[1])
        })

    }, [])

    const dataPadrinhoMes = [
        ["Mês", "Padrinhos"], 
        ["0"+infoMes1, infoPadrinhosMes[0]], 
        ["0"+infoMes2, infoPadrinhosMes[1]], 
        ["0"+infoMes3, infoPadrinhosMes[2]], 
        ["0"+infoMes4, infoPadrinhosMes[3]], 
        ["0"+infoMes5, infoPadrinhosMes[4]], 
        ["0"+infoMes6, infoPadrinhosMes[5]]
    ]

    const dataPadrinhoSemana = [
        ["Dia", "Padrinhos"], 
        [infoDia1, infoPadrinhosSemana[0]], 
        [infoDia2, infoPadrinhosSemana[1]], 
        [infoDia3, infoPadrinhosSemana[2]], 
        [infoDia4, infoPadrinhosSemana[3]], 
        [infoDia5, infoPadrinhosSemana[4]], 
        [infoDia6, infoPadrinhosSemana[5]], 
        [infoDia7, infoPadrinhosSemana[6]]
    ]

    // grafico demandas 
    useEffect(() => {
        
        api.get(`/demandas/dashboard/instituicao/${infoUsuario.fkInstituicao.id}`).then((res) => {
            setInfoDemandaMesAdocao(res.data.demandaAdocaoMes)
            setInfoDemandaSemanaAdocao(res.data.demandaAdocaoSemana)
            setInfoDemandaMesPagamento(res.data.demandaPagamentoMes)
            setInfoDemandaSemanaPagamento(res.data.demandaPagamentoSemana)
        })

    }, [])

    const dataDemandaMes = [
        ["Mês", "Adoção", "Pagamento"], 
        ["0"+infoMes1, infoDemandaMesAdocao[0], infoDemandaMesPagamento[0]], 
        ["0"+infoMes2, infoDemandaMesAdocao[1], infoDemandaMesPagamento[1]], 
        ["0"+infoMes3, infoDemandaMesAdocao[2], infoDemandaMesPagamento[2]], 
        ["0"+infoMes4, infoDemandaMesAdocao[3], infoDemandaMesPagamento[3]], 
        ["0"+infoMes5, infoDemandaMesAdocao[4], infoDemandaMesPagamento[4]], 
        ["0"+infoMes6, infoDemandaMesAdocao[5], infoDemandaMesPagamento[5]]
    ]

    const dataDemandaSemana = [
        ["Dia", "Adoção", "Pagamento"], 
        [infoDia1, infoDemandaSemanaAdocao[0], infoDemandaSemanaPagamento[0]], 
        [infoDia2, infoDemandaSemanaAdocao[1], infoDemandaSemanaPagamento[1]], 
        [infoDia3, infoDemandaSemanaAdocao[2], infoDemandaSemanaPagamento[2]], 
        [infoDia4, infoDemandaSemanaAdocao[3], infoDemandaSemanaPagamento[3]], 
        [infoDia5, infoDemandaSemanaAdocao[4], infoDemandaSemanaPagamento[4]], 
        [infoDia6, infoDemandaSemanaAdocao[5], infoDemandaSemanaPagamento[5]],
        [infoDia7, infoDemandaSemanaAdocao[6], infoDemandaSemanaPagamento[6]]
    ]

    // grafico prêmios
    useEffect(() => {

        api.get(`/demandas/premios/get/instituicao/${infoUsuario.fkInstituicao.id}`).then((res) => {
            setInfoPremiosSemana(res.data[0])
            setInfoPremiosMes(res.data[1])
        })

    },[])

    const dataPremioSemana = [
        ["Dia", "Prêmios"], 
        [infoDia1, infoPremiosSemana[0]], 
        [infoDia2, infoPremiosSemana[1]], 
        [infoDia3, infoPremiosSemana[2]], 
        [infoDia4, infoPremiosSemana[3]], 
        [infoDia5, infoPremiosSemana[4]], 
        [infoDia6, infoPremiosSemana[5]], 
        [infoDia7, infoPremiosSemana[6]], 
    ]
    
    const dataPremioMes = [
        ["Mês", "Prêmios"], 
        [infoMes1, infoPremiosMes[0]],
        [infoMes2, infoPremiosMes[1]],
        [infoMes3, infoPremiosMes[2]],
        [infoMes4, infoPremiosMes[3]],
        [infoMes5, infoPremiosMes[4]],
        [infoMes6, infoPremiosMes[5]]
    ]
    

    const [valorDataPadrinho, setValorDataPadrinho] = useState(dataPadrinhoSemana);
    const [valorDataDemanda, setValorDataDemanda] = useState(dataDemandaSemana);
    const [valorDataPremio, setValorDataPremio] = useState(dataPremioSemana);

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
                                        id="chart-padrinho"
                                        chartType="Bar"
                                        data={valorDataPadrinho}
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
                            
                        </div>

                    </div>

                </div>
            </div>

            <VLibras forceOnload={true}></VLibras>
        </>
    )
}

export default DashboardAdmin;