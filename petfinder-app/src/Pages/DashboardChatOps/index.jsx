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

    // const [infoDemandaSemana1, setInfoDemandaSemana1] = useState([]);
    // const [infoDemandaSemana2, setInfoDemandaSemana2] = useState([]);
    // const [infoDemandaSemana3, setInfoDemandaSemana3] = useState([]);
    // const [infoDemandaSemana4, setInfoDemandaSemana4] = useState([]);
    // const [infoDemandaSemana5, setInfoDemandaSemana5] = useState([]);
    // const [infoDemandaSemana6, setInfoDemandaSemana6] = useState([]);
    // const [infoDemandaSemana7, setInfoDemandaSemana7] = useState([]);

    // const [infoDemandaMes1, setInfoDemandaMes1] = useState([]);
    // const [infoDemandaMes2, setInfoDemandaMes2] = useState([]);
    // const [infoDemandaMes3, setInfoDemandaMes3] = useState([]);
    // const [infoDemandaMes4, setInfoDemandaMes4] = useState([]);
    // const [infoDemandaMes5, setInfoDemandaMes5] = useState([]);
    // const [infoDemandaMes6, setInfoDemandaMes6] = useState([]);

    const [infoDemandaSemanaAdocao, setInfoDemandaSemanaAdocao] = useState([]);
    const [infoDemandaSemanaPagamento, setInfoDemandaSemanaPagamento] = useState([]);
    const [infoDemandaMesAdocao, setInfoDemandaMesAdocao] = useState([]);
    const [infoDemandaMesPagamento, setInfoDemandaMesPagamento] = useState([]);

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

        api.get(`/demandas/dashboard/usuario/${infoUsuario.fkInstituicao.id}/${infoUsuario.id}`).then((res) => {
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