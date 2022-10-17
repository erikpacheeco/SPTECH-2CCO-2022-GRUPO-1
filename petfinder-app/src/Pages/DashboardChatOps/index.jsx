import './DashboardChatOps.css';
import HeaderApp from "../../Components/HeaderApp";
import React from "react";
import { Chart } from "react-google-charts";
import VLibras from "@djpfs/react-vlibras"
import { useEffect, useState } from "react";
import api from "../../Api"

export const options = {
    is3D: true
};

function DashboardChatOps() {

    const infoUsuario = JSON.parse(localStorage.getItem('petfinder_user'));

    const [infoDashboard, setInfoDashboard] = useState([]);

    const [infoDemandaComparaInst, setInfoDemandaComparaInst] = useState([]);
    const [infoDemandaComparaUsuario, setInfoDemandaComparaUsuario] = useState([]);
    const [infoDemandaComparaCancelada, setInfoDemandaComparaCancelada] = useState([]);

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

    var trocaBtnDemanda = true;

    useEffect(() => {

        const interval = setInterval(() => {
            document.querySelector("#btn-semana-demanda").click();
            return clearInterval(interval);
        }, 400);

    }, []);

    // Cards
    useEffect(() => {
    
        api.get(`/demandas/dashboard/${infoUsuario.id}`).then((res) => {
            setInfoDashboard(res.data)
        })
        
    }, [])

    // grafico demandas 
    useEffect(() => {

        const dataAtual = new Date();
        const mesAtual = dataAtual.getMonth() + 1;

        api.get(`/usuarios/demanda/${infoUsuario.fkInstituicao.id}/${infoUsuario.id}/${dataAtual.getFullYear()}/0${mesAtual-1}`).then((res) => {
            setInfoDemandaComparaInst(res.data[0])
            setInfoDemandaComparaUsuario(res.data[1])
            setInfoDemandaComparaCancelada(res.data[2])
        })

    }, [])

    // grafico demandas 
    useEffect(() => {

        const dataAtual = new Date();
        const mesAtual = dataAtual.getMonth() + 1;
        const diaAtual = dataAtual.getDate();

        api.get(`/usuarios/demandas-ultima-semana/${infoUsuario.id}/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual}`).then((res) => {
            setInfoDemandaSemana7(res.data)
            setInfoDia7(diaAtual)
        })

        api.get(`/usuarios/demandas-ultima-semana/${infoUsuario.id}/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-1}`).then((res) => {
            setInfoDemandaSemana6(res.data)
            setInfoDia6(diaAtual-1)
        })

        api.get(`/usuarios/demandas-ultima-semana/${infoUsuario.id}/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-2}`).then((res) => {
            setInfoDemandaSemana5(res.data)
            setInfoDia5(diaAtual-2)
        })

        api.get(`/usuarios/demandas-ultima-semana/${infoUsuario.id}/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-3}`).then((res) => {
            setInfoDemandaSemana4(res.data)
            setInfoDia4(diaAtual-3)
        })

        api.get(`/usuarios/demandas-ultima-semana/${infoUsuario.id}/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-4}`).then((res) => {
            setInfoDemandaSemana3(res.data)
            setInfoDia3(diaAtual-4)
        })

        api.get(`/usuarios/demandas-ultima-semana/${infoUsuario.id}/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-5}`).then((res) => {
            setInfoDemandaSemana2(res.data)
            setInfoDia2(diaAtual-5)
        })

        api.get(`/usuarios/demandas-ultima-semana/${infoUsuario.id}/${dataAtual.getFullYear()}/0${mesAtual-1}/${diaAtual-6}`).then((res) => {
            setInfoDemandaSemana1(res.data)
            setInfoDia1(diaAtual-6)
        })

        api.get(`/usuarios/demandas/${infoUsuario.fkInstituicao.id}/${infoUsuario.id}/${dataAtual.getFullYear()}/0${mesAtual-1}`).then((res) => {
            setInfoDemandaMes6(res.data)
            setInfoMes6(mesAtual-1)
        })

        api.get(`/usuarios/demandas/${infoUsuario.fkInstituicao.id}/${infoUsuario.id}/${dataAtual.getFullYear()}/0${mesAtual-2}`).then((res) => {
            setInfoDemandaMes5(res.data)
            setInfoMes5(mesAtual-2)
        })

        api.get(`/usuarios/demandas/${infoUsuario.fkInstituicao.id}/${infoUsuario.id}/${dataAtual.getFullYear()}/0${mesAtual-3}`).then((res) => {
            setInfoDemandaMes4(res.data)
            setInfoMes4(mesAtual-3)
        })

        api.get(`/usuarios/demandas/${infoUsuario.fkInstituicao.id}/${infoUsuario.id}/${dataAtual.getFullYear()}/0${mesAtual-4}`).then((res) => {
            setInfoDemandaMes3(res.data)
            setInfoMes3(mesAtual-4)
        })

        api.get(`/usuarios/demandas/${infoUsuario.fkInstituicao.id}/${infoUsuario.id}/${dataAtual.getFullYear()}/0${mesAtual-5}`).then((res) => {
            setInfoDemandaMes2(res.data)
            setInfoMes2(mesAtual-5)
        })

        api.get(`/usuarios/demandas/${infoUsuario.fkInstituicao.id}/${infoUsuario.id}/${dataAtual.getFullYear()}/0${mesAtual-6}`).then((res) => {
            setInfoDemandaMes1(res.data)
            setInfoMes1(mesAtual-6)
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

    const [valorDataDemanda, setValorDataDemanda] = useState(dataDemandaSemana);

    return(
        <>
            <HeaderApp/>

            <div className="dashboard-chatops">
                <div className="dashboard-chatops-container">
                    <div className="dashboard-chatops-metricas-container">
                        <h2>Métricas de cadastro</h2>
                        <div className="dashboard-chatops-metricas-card-container">
                            <div className="dashboard-chatops-metricas-card">
                                <p>{infoDashboard.qtdDemandaAbertaInstituicao}</p>
                                <p>Em espera</p>
                            </div>

                            <div className="dashboard-chatops-metricas-card">
                                <p>{infoDashboard.qtdDemandaConcluidaColaboradorInstiuicao}</p>
                                <p>Concluídas</p>
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
                                        data={[
                                            ["", "Demanda"], 
                                            ["Sua equipe (com sucesso)", infoDemandaComparaInst - infoDemandaComparaUsuario], 
                                            ["Você (com sucesso)", infoDemandaComparaUsuario], 
                                            ["Sem sucesso", infoDemandaComparaCancelada]
                                        ]}
                                        width="100%"
                                        height="100%"
                                        options={options}
                                        legendToggle
                                    />
                                </div>
                            </div>

                            <div className="dashboard-chatops-metricas-grafico">
                                <h2>Categorias de demandas mais frequentes por</h2>

                                <div className="dashboard-chatops-metricas-grafico-botoes">

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

                                <div className="dashboard-chatops-metricas-grafico-container">
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

                    </div>

                </div>
            </div>
            
            <VLibras forceOnload={true}></VLibras>
        </>
    )
}

export default DashboardChatOps;