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

    const [infoPadrinhoSemana1, setInfoPadrinhoSemana1] = useState([]);
    const [infoPadrinhoSemana2, setInfoPadrinhoSemana2] = useState([]);
    const [infoPadrinhoSemana3, setInfoPadrinhoSemana3] = useState([]);
    const [infoPadrinhoSemana4, setInfoPadrinhoSemana4] = useState([]);
    const [infoPadrinhoSemana5, setInfoPadrinhoSemana5] = useState([]);
    const [infoPadrinhoSemana6, setInfoPadrinhoSemana6] = useState([]);
    const [infoPadrinhoSemana7, setInfoPadrinhoSemana7] = useState([]);

    const [infoPadrinhoMes1, setInfoPadrinhoMes1] = useState([]);
    const [infoPadrinhoMes2, setInfoPadrinhoMes2] = useState([]);
    const [infoPadrinhoMes3, setInfoPadrinhoMes3] = useState([]);
    const [infoPadrinhoMes4, setInfoPadrinhoMes4] = useState([]);
    const [infoPadrinhoMes5, setInfoPadrinhoMes5] = useState([]);
    const [infoPadrinhoMes6, setInfoPadrinhoMes6] = useState([]);

    const [infoDemandaSemana1, setInfoDemandaSemana1] = useState([]);
    const [infoDemandaSemana2, setInfoDemandaSemana2] = useState([]);
    const [infoDemandaSemana3, setInfoDemandaSemana3] = useState([]);
    const [infoDemandaSemana4, setInfoDemandaSemana4] = useState([]);
    const [infoDemandaSemana5, setInfoDemandaSemana5] = useState([]);
    const [infoDemandaSemana6, setInfoDemandaSemana6] = useState([]);
    const [infoDemandaSemana7, setInfoDemandaSemana7] = useState([]);

    const [infoDemandaMes1, setInfoDemandaMes1] = useState([]);
    const [infoDemandaMes2, setInfoDemandaMes2] = useState([]);
    const [infoDemandaMes3, setInfoDemandaMes3] = useState([]);
    const [infoDemandaMes4, setInfoDemandaMes4] = useState([]);
    const [infoDemandaMes5, setInfoDemandaMes5] = useState([]);
    const [infoDemandaMes6, setInfoDemandaMes6] = useState([]);

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
            return clearInterval(interval);
        }, 400);

    }, []);

    // cads 
    useEffect(() => {
        
        api.get(`/demandas/dashboard/${infoUsuario.id}`).then((res) => {
            setInfoDashboard(res.data)
        })

    }, [])

    // grafico padrinhos
    useEffect(() => {
        
        const dataAtual = new Date();
        const mesAtual = dataAtual.getMonth() + 1;
        const diaAtual = dataAtual.getDate();

        api.get(`/usuarios/padrinhos-ultima-semana/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual}`).then((res) => {
            setInfoPadrinhoSemana7(res.data)
            setInfoDia7(diaAtual)
        })

        api.get(`/usuarios/padrinhos-ultima-semana/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-1}`).then((res) => {
            setInfoPadrinhoSemana6(res.data)
            setInfoDia6(diaAtual-1)
        })

        api.get(`/usuarios/padrinhos-ultima-semana/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-2}`).then((res) => {
            setInfoPadrinhoSemana5(res.data)
            setInfoDia5(diaAtual-2)
        })

        api.get(`/usuarios/padrinhos-ultima-semana/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-3}`).then((res) => {
            setInfoPadrinhoSemana4(res.data)
            setInfoDia4(diaAtual-3)
        })

        api.get(`/usuarios/padrinhos-ultima-semana/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-4}`).then((res) => {
            setInfoPadrinhoSemana3(res.data)
            setInfoDia3(diaAtual-4)
        })

        api.get(`/usuarios/padrinhos-ultima-semana/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-5}`).then((res) => {
            setInfoPadrinhoSemana2(res.data)
            setInfoDia2(diaAtual-5)
        })

        api.get(`/usuarios/padrinhos-ultima-semana/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-6}`).then((res) => {
            setInfoPadrinhoSemana1(res.data)
            setInfoDia1(diaAtual-6)
        })

        api.get(`/usuarios/padrinhos/${infoUsuario.fkInstituicao.id}/${dataAtual.getFullYear()}/0${mesAtual-1}`).then((res) => {
            setInfoPadrinhoMes6(res.data)
            setInfoMes6(mesAtual-1)
        })

        api.get(`/usuarios/padrinhos/${infoUsuario.fkInstituicao.id}/${dataAtual.getFullYear()}/0${mesAtual-2}`).then((res) => {
            setInfoPadrinhoMes5(res.data)
            setInfoMes5(mesAtual-2)
        })

        api.get(`/usuarios/padrinhos/${infoUsuario.fkInstituicao.id}/${dataAtual.getFullYear()}/0${mesAtual-3}`).then((res) => {
            setInfoPadrinhoMes4(res.data)
            setInfoMes4(mesAtual-3)
        })

        api.get(`/usuarios/padrinhos/${infoUsuario.fkInstituicao.id}/${dataAtual.getFullYear()}/0${mesAtual-4}`).then((res) => {
            setInfoPadrinhoMes3(res.data)
            setInfoMes3(mesAtual-4)
        })

        api.get(`/usuarios/padrinhos/${infoUsuario.fkInstituicao.id}/${dataAtual.getFullYear()}/0${mesAtual-5}`).then((res) => {
            setInfoPadrinhoMes2(res.data)
            setInfoMes2(mesAtual-5)
        })

        api.get(`/usuarios/padrinhos/${infoUsuario.fkInstituicao.id}/${dataAtual.getFullYear()}/0${mesAtual-6}`).then((res) => {
            setInfoPadrinhoMes1(res.data)
            setInfoMes1(mesAtual-6)
        })

    }, [])

    const dataPadrinhoMes = [
        ["Mês", "Padrinhos"], 
        ["0"+infoMes1, infoPadrinhoMes1], 
        ["0"+infoMes2, infoPadrinhoMes2], 
        ["0"+infoMes3, infoPadrinhoMes3], 
        ["0"+infoMes4, infoPadrinhoMes4], 
        ["0"+infoMes5, infoPadrinhoMes5], 
        ["0"+infoMes6, infoPadrinhoMes6]
    ]

    const dataPadrinhoSemana = [
        ["Dia", "Padrinhos"], 
        [infoDia1, infoPadrinhoSemana1], 
        [infoDia2, infoPadrinhoSemana2], 
        [infoDia3, infoPadrinhoSemana3], 
        [infoDia4, infoPadrinhoSemana4], 
        [infoDia5, infoPadrinhoSemana5], 
        [infoDia6, infoPadrinhoSemana6], 
        [infoDia7, infoPadrinhoSemana7]
    ]

    // grafico demandas 
    useEffect(() => {

        const dataAtual = new Date();
        const mesAtual = dataAtual.getMonth() + 1;
        const diaAtual = dataAtual.getDate();

        api.get(`/usuarios/demandas-ultima-semana/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual}`).then((res) => {
            setInfoDemandaSemana7(res.data)
        })

        api.get(`/usuarios/demandas-ultima-semana/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-1}`).then((res) => {
            setInfoDemandaSemana6(res.data)
        })

        api.get(`/usuarios/demandas-ultima-semana/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-2}`).then((res) => {
            setInfoDemandaSemana5(res.data)
        })

        api.get(`/usuarios/demandas-ultima-semana/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-3}`).then((res) => {
            setInfoDemandaSemana4(res.data)
        })

        api.get(`/usuarios/demandas-ultima-semana/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-4}`).then((res) => {
            setInfoDemandaSemana3(res.data)
        })

        api.get(`/usuarios/demandas-ultima-semana/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-5}`).then((res) => {
            setInfoDemandaSemana2(res.data)
        })

        api.get(`/usuarios/demandas-ultima-semana/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-6}`).then((res) => {
            setInfoDemandaSemana1(res.data)
        })

        api.get(`/usuarios/demandas/${infoUsuario.fkInstituicao.id}/${dataAtual.getFullYear()}/0${mesAtual-1}`).then((res) => {
            setInfoDemandaMes6(res.data)
        })

        api.get(`/usuarios/demandas/${infoUsuario.fkInstituicao.id}/${dataAtual.getFullYear()}/0${mesAtual-2}`).then((res) => {
            setInfoDemandaMes5(res.data)
        })

        api.get(`/usuarios/demandas/${infoUsuario.fkInstituicao.id}/${dataAtual.getFullYear()}/0${mesAtual-3}`).then((res) => {
            setInfoDemandaMes4(res.data)
        })

        api.get(`/usuarios/demandas/${infoUsuario.fkInstituicao.id}/${dataAtual.getFullYear()}/0${mesAtual-4}`).then((res) => {
            setInfoDemandaMes3(res.data)
        })

        api.get(`/usuarios/demandas/${infoUsuario.fkInstituicao.id}/${dataAtual.getFullYear()}/0${mesAtual-5}`).then((res) => {
            setInfoDemandaMes2(res.data)
        })

        api.get(`/usuarios/demandas/${infoUsuario.fkInstituicao.id}/${dataAtual.getFullYear()}/0${mesAtual-6}`).then((res) => {
            setInfoDemandaMes1(res.data)
        })

    }, [])

    const dataDemandaMes = [
        ["Mês", "Adoção", "Pagamento"], 
        ["0"+infoMes1, infoDemandaMes1[0], infoDemandaMes1[1]], 
        ["0"+infoMes2, infoDemandaMes2[0], infoDemandaMes2[1]], 
        ["0"+infoMes3, infoDemandaMes3[0], infoDemandaMes3[1]], 
        ["0"+infoMes4, infoDemandaMes4[0], infoDemandaMes4[1]], 
        ["0"+infoMes5, infoDemandaMes5[0], infoDemandaMes5[1]], 
        ["0"+infoMes6, infoDemandaMes6[0], infoDemandaMes6[1]]
    ]

    const dataDemandaSemana = [
        ["Dia", "Adoção", "Pagamento"], 
        [infoDia1, infoDemandaSemana1[0], infoDemandaSemana1[1]], 
        [infoDia2, infoDemandaSemana2[0], infoDemandaSemana2[1]], 
        [infoDia3, infoDemandaSemana3[0], infoDemandaSemana3[1]], 
        [infoDia4, infoDemandaSemana4[0], infoDemandaSemana4[1]], 
        [infoDia5, infoDemandaSemana5[0], infoDemandaSemana5[1]], 
        [infoDia6, infoDemandaSemana6[0], infoDemandaSemana6[1]],
        [infoDia7, infoDemandaSemana7[0], infoDemandaSemana7[1]]
    ]

    // grafico prêmios
    useEffect(() => {

    },[])

    const dataPremioSemana = [
        ["", "Prêmios"], 
        ["Seg", 3], 
        ["Ter", 0], 
        ["Qua", 2], 
        ["Qui", 8], 
        ["Sex", 10],
        ["Sab", 5],
        ["Dom", 0]
    ]
    
    const dataPremioMes = [
        ["", "Prêmios"], 
        ["Fev", 38], 
        ["Mar", 43], 
        ["Abr", 35], 
        ["Mai", 20], 
        ["Jun", 44]
    ]
    

    const [valorDataPremio, setValorDataPremio] = useState(dataPremioSemana);
    const [valorDataDemanda, setValorDataDemanda] = useState(dataDemandaSemana);
    const [valorDataPadrinho, setValorDataPadrinho] = useState(dataPadrinhoSemana);

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