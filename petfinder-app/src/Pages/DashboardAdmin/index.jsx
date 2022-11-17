import './DashboardAdmin.css';
import HeaderApp from "../../Components/HeaderApp";
import React from "react";
import { Chart } from "react-google-charts";
import VLibras from "@djpfs/react-vlibras"
import { useEffect, useState } from "react";
import api from "../../Api";
import BtnDashboard from '../../Components/BtnDashboard';

function DashboardAdmin() {
    
    const infoUsuario = JSON.parse(localStorage.getItem('petfinder_user'));
    const [pageValues, setPageValues] = useState({});
    const [qtdPadrinhos, setQtdPadrinhos] = useState(0);
    const [qtdPetsAdotados, setPetsAdotados] = useState(0);
    const [chartPadrinhosPor, setChartPadrinhosPor] = useState();
    const [chartCategoriasPor, setChartCategoriasPor] = useState();

    // selected active
    const [isPadrinhosSemana, setIsPadrinhosSemana] = useState(true);
    const [isCategoriasSemana, setIsCategoriasSemana] = useState(true);

    function toNumberAndInvert(values) {
        return [values[0], ...values.slice(1).reverse().map(value => {
            return [value[0], ...value.slice(1).map(numericValue => {
                return Number(numericValue);
            })]
        })];
    }

    useEffect(() => {

        api.get(`/dashboard/admin/${infoUsuario.id}`)
        .then((res) => {
            res.data.chartPadrinhosPorSemana = toNumberAndInvert(res.data.chartPadrinhosPorSemana);
            res.data.chartPadrinhosPorMes = toNumberAndInvert(res.data.chartPadrinhosPorMes);
            res.data.chartCategoriasPorSemana = toNumberAndInvert(res.data.chartCategoriasPorSemana);
            res.data.chartCategoriasPorMes = toNumberAndInvert(res.data.chartCategoriasPorMes);
            setPageValues(res.data);
        })
        .catch((err) => {
            console.log(err);
        });

    }, []);

    useEffect(() => {
        setQtdPadrinhos(pageValues.padrinhos);
        setPetsAdotados(pageValues.petsAdotados);
        setChartPadrinhosPor(pageValues.chartPadrinhosPorSemana);
        setChartCategoriasPor(pageValues.chartCategoriasPorSemana);
    }, [pageValues]);

    return (
        <>
            <HeaderApp/>

            <div className="dashboard-admin">
                <div className="dashboard-admin-container">
                    <div className="dashboard-admin-metricas-container">
                        <h2>Métricas de cadastro</h2>
                        <div className="dashboard-admin-metricas-card-container">
                            <div className="dashboard-admin-metricas-card">
                                <p>{qtdPadrinhos}</p>
                                <p>Padrinhos</p>
                            </div>

                            <div className="dashboard-admin-metricas-card">
                                <p>{qtdPetsAdotados}</p>
                                <p>Pet's Adotados</p>
                            </div>

                            <div className="dashboard-admin-metricas-card">
                                <p>{0}</p>
                                <p>Prêmios por Pet</p>
                            </div>

                            <div className="dashboard-admin-metricas-card">
                                <p>{0}</p>
                                <p>Pet's sem Prêmio</p>
                            </div>
                        </div>
                    </div>

                    <div className="dashboard-admin-metricas-graficos-container">
                        
                        <div className="dashboard-admin-metricas-graficos">

                            <div className="dashboard-admin-metricas-grafico-1">
                            <h2>Padrinhos por</h2>
                                <div className="dashboard-admin-metricas-grafico-botoes">

                                    <BtnDashboard 
                                        value="Semana" 
                                        active={isPadrinhosSemana}
                                        click={() => {
                                            if (!isPadrinhosSemana) {
                                                setIsPadrinhosSemana(true);
                                                setChartPadrinhosPor(pageValues.chartPadrinhosPorSemana);
                                            }
                                        }}
                                    />

                                    <BtnDashboard 
                                        value="Mês" 
                                        active={!isPadrinhosSemana}
                                        click={() => {
                                            if (isPadrinhosSemana) {
                                                setIsPadrinhosSemana(false);
                                                setChartPadrinhosPor(pageValues.chartPadrinhosPorMes);
                                            }
                                        }}
                                    />

                                </div>
                                <div className="dashboard-admin-metricas-grafico-container">     
                                {/* {console.log(chartPadrinhosPor)}                        */}
                                    <Chart
                                        id="chart-padrinho"
                                        chartType="Bar"
                                        data={chartPadrinhosPor || [
                                            ["Dia", "Padrinhos"],
                                            ["0", 0]
                                        ]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>

                            <div className="dashboard-admin-metricas-grafico">
                                <h2>Categorias de demandas mais frequentes por</h2>

                                <div className="dashboard-admin-metricas-grafico-botoes">

                                    <BtnDashboard 
                                        value="Semana" 
                                        active={isCategoriasSemana}
                                        click={() => {
                                            if(!isCategoriasSemana) {
                                                setIsCategoriasSemana(true);
                                                setChartCategoriasPor(pageValues.chartCategoriasPorSemana);
                                            }
                                        }}
                                    />

                                    <BtnDashboard 
                                        value="Mês" 
                                        active={!isCategoriasSemana}
                                        click={() => {
                                            if(isCategoriasSemana) {
                                                setIsCategoriasSemana(false);
                                                setChartCategoriasPor(pageValues.chartCategoriasPorMes);
                                            }
                                        }}
                                    />

                                </div>

                                <div className="dashboard-admin-metricas-grafico-container">
                                    {/* {console.log(valorDataDemanda)} */}
                                    <Chart
                                        chartType="Bar"
                                        data={chartCategoriasPor || [
                                            ["Dia", "Adoção", "Pagamentos"],
                                            ["01/11", 0, 0]
                                        ]}
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
                                            if(false){
                                                let btnSemanaPremio = document.getElementById("btn-semana-premio");
                                                btnSemanaPremio.style.backgroundColor = "#7F2AB5";
                                                btnSemanaPremio.style.color = "white";

                                                let btnMesPremio = document.getElementById("btn-mes-premio");
                                                btnMesPremio.style.backgroundColor = "white";
                                                btnMesPremio.style.color = "#7F2AB5";
                                            }
                                            // setValorDataPremio(dataPremioSemana)
                                        }}
                                    >
                                        Semana
                                    </button>

                                    <button
                                        type="button"
                                        className="btn-mes-premio"
                                        id='btn-mes-premio'
                                        onClick={() => {
                                            if(false){
                                                let btnSemanaPremio = document.getElementById("btn-semana-premio");
                                                btnSemanaPremio.style.backgroundColor = "white";
                                                btnSemanaPremio.style.color = "#7F2AB5";

                                                let btnMesPremio = document.getElementById("btn-mes-premio");
                                                btnMesPremio.style.backgroundColor = "#7F2AB5";
                                                btnMesPremio.style.color = "white";
                                            }
                                            // setValorDataPremio(dataPremioMes);
                                        }}
                                    >
                                        Mês
                                    </button>
                                </div>
                                <div className="dashboard-admin-metricas-grafico-container">     
                                    <Chart
                                        id="chart-premio"
                                        chartType="Bar"
                                        data={[
                                            ["Dia", "Premios"],
                                            [10, 20],
                                            [11, 13],
                                            [12, 16],
                                            [13, 12],
                                            [14, 15],
                                            [15, 14],
                                            [16, 19],
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

export default DashboardAdmin;