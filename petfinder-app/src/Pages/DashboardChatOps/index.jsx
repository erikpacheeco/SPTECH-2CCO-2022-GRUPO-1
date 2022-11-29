import './DashboardChatOps.css';
import HeaderApp from "../../Components/HeaderApp";
import React from "react";
import { Chart } from "react-google-charts";
import VLibras from "@djpfs/react-vlibras"
import { useEffect, useState } from "react";
import api from "../../Api";
import BtnDashboard from "../../Components/BtnDashboard";
import { toNumberAndInvert } from "../../functions/util";

export const options = {
    is3D: true
};

function DashboardChatOps() {

    const infoUsuario = JSON.parse(localStorage.getItem('petfinder_user'));

    const [btnPadrinhosSem, setBtnPadrinhosSem] = useState(true);

    // cards
    const [emEspera, setEmEspera] = useState(0);
    const [concluido, setConcluido] = useState(0);

    // charts
    const [chartDemandasPorSemana, setChartDemandasPorSemana] = useState(null);
    const [chartCategoriaSem, setChartCategoriaSem] = useState(null);
    const [chartCategoriaMes, setChartCategoriaMes] = useState(null);
    const [chartCategoria, setChartCategoria] = useState(null);

    // requesting
    useEffect(() => {
        api.get(`/dashboard/chatops/${infoUsuario.id}`)
        .then(({status, data}) => {
            if(status == 200) {
                console.log(data);

                // card
                setEmEspera(data.emEspera);
                setConcluido(data.concluidos);

                // chart
                setChartCategoriaSem(toNumberAndInvert(data.chartDemandasMaisFrequentesSemana));
                setChartCategoriaMes(toNumberAndInvert(data.chartDemandasMaisFrequentesMes));
                setChartDemandasPorSemana(toNumberAndInvert(data.chartDemandasPorSemana));
            }
        })
        .catch(err => {
            console.log(err);
        });
    }, []);

    useEffect(() => {
        setChartCategoria(chartCategoriaSem);
    }, [chartCategoriaSem, chartCategoriaMes]);

    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);
        setTimeout(() => {
        setLoading(false);
        }, 2000);
    }, []);

    return(
        <>
            <HeaderApp/>

            <div className="dashboard-chatops">
                <div className="dashboard-chatops-container">
                    <div className="dashboard-chatops-metricas-container">
                        <h2>Métricas de cadastro</h2>
                        <div className="dashboard-chatops-metricas-card-container">
                            <div className="dashboard-chatops-metricas-card">
                                <p>{emEspera}</p>
                                <p>Em espera</p>
                            </div>

                            <div className="dashboard-chatops-metricas-card">
                                <p>{concluido}</p>
                                <p>Concluídas</p>
                            </div>

                        </div>
                    </div>

                    <div className="dashboard-chatops-metricas-graficos-container">
                        
                        <div className="dashboard-chatops-metricas-graficos">
                            <div className="dashboard-chatops-metricas-grafico">
                                <h2>Demandas por semana</h2>
                                <div className="dashboard-chatops-metricas-grafico-container-1">
                                    {loading ? (
                                        <div className="dashboard-chatops-loader-container">
                                            <div className="dashboard-chatops-spinner"></div>
                                        </div>
                                    ) : (
                                        <Chart
                                            chartType="PieChart"
                                            data={chartDemandasPorSemana || [
                                                ["", "Demanda"], 
                                                ["Sua equipe (com sucesso)", 1], 
                                                ["Você (com sucesso)", 1], 
                                                ["Sem sucesso", 10]
                                            ]}
                                            width="100%"
                                            height="100%"
                                            options={options}
                                            legendToggle
                                        />
                                    )}
                                </div>
                            </div>

                            <div className="dashboard-chatops-metricas-grafico">
                                <h2>Categorias de demandas mais frequentes por</h2>

                                <div className="dashboard-chatops-metricas-grafico-botoes">

                                    <BtnDashboard 
                                        value="Semana" 
                                        active={btnPadrinhosSem}
                                        click={() => {
                                            if (!btnPadrinhosSem) {
                                                setChartCategoria(chartCategoriaSem);
                                                setBtnPadrinhosSem(true);
                                            }
                                        }}
                                    />

                                    <BtnDashboard 
                                        value="Mês" 
                                        active={!btnPadrinhosSem}
                                        click={() => {
                                            if (btnPadrinhosSem) {
                                                setChartCategoria(chartCategoriaMes);
                                                setBtnPadrinhosSem(false);
                                            }
                                        }}
                                    />
                                </div>

                                <div className="dashboard-chatops-metricas-grafico-container">
                                    {loading ? (
                                        <div className="dashboard-chatops-loader-container">
                                            <div className="dashboard-chatops-spinner"></div>
                                        </div>
                                    ) : (
                                        <Chart
                                            chartType="Bar"
                                            data={chartCategoria || [
                                                ["Dia", "Pagamento", "Adoção"],
                                                ["11/18", 0, 0],
                                                ["11/19", 0, 0],
                                                ["11/20", 0, 0],
                                                ["11/21", 0, 0],
                                                ["11/22", 0, 0],
                                            ]}
                                            width="100%"
                                            height="100%"
                                            legendToggle
                                        />
                                    )}
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