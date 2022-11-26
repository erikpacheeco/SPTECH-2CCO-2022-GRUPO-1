import './DashboardChatOps.css';
import HeaderApp from "../../Components/HeaderApp";
import React from "react";
import { Chart } from "react-google-charts";
import VLibras from "@djpfs/react-vlibras"
import { useEffect, useState } from "react";
import api from "../../Api";
import BtnDashboard from "../../Components/BtnDashboard";

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
                setChartCategoriaSem(data.chartDemandasMaisFrequentesSemana);
                setChartCategoriaMes(data.chartDemandasMaisFrequentesMes);
                setChartCategoria(chartCategoriaSem);
            }
        })
        .catch(err => {
            console.log(err);
        });
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
                                    <Chart
                                        chartType="PieChart"
                                        data={[
                                            ["", "Demanda"], 
                                            ["Sua equipe (com sucesso)", 2], 
                                            ["Você (com sucesso)", 10], 
                                            ["Sem sucesso", 3]
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
                                    <Chart
                                        chartType="Bar"
                                        data={chartCategoria || [
                                            ["Dia", "Pagamento", "Adoção"],
                                            ["11/18", 20, 18],
                                            ["11/19", 10, 12],
                                            ["11/20", 15, 14],
                                            ["11/21", 14, 12],
                                            ["11/22", 17, 20],
                                        ]}
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