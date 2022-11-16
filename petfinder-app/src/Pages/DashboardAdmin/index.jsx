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
    const [chartPadrinhosPor, setChartPadrinhosPor] = useState([
        ["Dia", "Padrinhos"],
        [0, 0]
    ]);

    // selected active
    let [isPadrinhosSemana, setIsPadrinhosSemana] = useState(true);

    function toNumberAndInvert(values) {
        return [values[0], ...values.slice(1).reverse().map(value => {
            return [value[0], Number(value[1])]
        })];
    }

    useEffect(() => {

        api.get(`/dashboard/admin/${infoUsuario.id}`)
        .then((res) => {
            res.data.chartPadrinhosPorSemana = toNumberAndInvert(res.data.chartPadrinhosPorSemana);
            res.data.chartPadrinhosPorMes = toNumberAndInvert(res.data.chartPadrinhosPorMes);
            setPageValues(res.data);
        })
        .catch((err) => {
            console.log(err);
        });

    }, []);

    useEffect(() => {
        console.log(pageValues);
        setQtdPadrinhos(pageValues.padrinhos);
        setPetsAdotados(pageValues.petsAdotados);
        setChartPadrinhosPor(pageValues.chartPadrinhosPorSemana);
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
                                            }
                                        }}
                                    />

                                    <BtnDashboard 
                                        value="Mês" 
                                        active={!isPadrinhosSemana}
                                        click={() => {
                                            if (isPadrinhosSemana) {
                                                setIsPadrinhosSemana(false);
                                            }
                                        }}
                                    />

                                </div>
                                <div className="dashboard-admin-metricas-grafico-container">     
                                {console.log(chartPadrinhosPor)}                       
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

                                    <button
                                        type="button"
                                        className="btn-semana-demanda"
                                        id='btn-semana-demanda'
                                        onClick={() => {
                                            if(false){
                                                let btnSemanaDemanda = document.getElementById("btn-semana-demanda");
                                                btnSemanaDemanda.style.backgroundColor = "#7F2AB5";
                                                btnSemanaDemanda.style.color = "white";

                                                let btnMesDemanda = document.getElementById("btn-mes-demanda");
                                                btnMesDemanda.style.backgroundColor = "white";
                                                btnMesDemanda.style.color = "#7F2AB5";
                                            }
                                            // setValorDataDemanda(dataDemandaSemana);
                                        }}
                                    >
                                        Semana
                                    </button>

                                    <button
                                        type="button"
                                        className="btn-mes-demanda"
                                        id='btn-mes-demanda'
                                        onClick={() => {
                                            if(false){
                                                let btnSemanaDemanda = document.getElementById("btn-semana-demanda");
                                                btnSemanaDemanda.style.backgroundColor = "white";
                                                btnSemanaDemanda.style.color = "#7F2AB5";

                                                let btnMesDemanda = document.getElementById("btn-mes-demanda");
                                                btnMesDemanda.style.backgroundColor = "#7F2AB5";
                                                btnMesDemanda.style.color = "white";
                                            }
                                            // setValorDataDemanda(dataDemandaMes);
                                        }}
                                    >
                                        Mês
                                    </button>
                                </div>

                                <div className="dashboard-admin-metricas-grafico-container">
                                    {/* {console.log(valorDataDemanda)} */}
                                    <Chart
                                        chartType="Bar"
                                        data={[
                                            ["Mês", "Adoção", "Pagamento"],
                                            ["06", 7, 10],
                                            ["06", 8, 9],
                                            ["06", 7, 11],
                                            ["06", 6, 12],
                                            ["06", 7, 10],
                                            ["06", 8, 12],
                                            ["06", 7, 11],
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