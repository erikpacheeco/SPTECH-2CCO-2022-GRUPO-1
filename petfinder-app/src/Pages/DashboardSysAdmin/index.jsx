import './DashboardSysAdmin.css';
import HeaderApp from "../../Components/HeaderApp";
import React from "react";
import { Chart } from "react-google-charts";
import VLibras from "@djpfs/react-vlibras"
import { useEffect, useState } from "react";
import api from "../../Api";

export const options = {
    is3D: true
};

function DashboardSysAdmin() {

    const infoUsuario = JSON.parse(localStorage.getItem('petfinder_user'));

    const [infoDashboard, setInfoDashboard] = useState([]);

    const [infoVisitanteMes1, setInfoVisitanteMes1] = useState([]);
    const [infoVisitanteMes2, setInfoVisitanteMes2] = useState([]);
    const [infoVisitanteMes3, setInfoVisitanteMes3] = useState([]);
    const [infoVisitanteMes4, setInfoVisitanteMes4] = useState([]);
    const [infoVisitanteMes5, setInfoVisitanteMes5] = useState([]);
    const [infoVisitanteMes6, setInfoVisitanteMes6] = useState([]);
    
    const [infoLeadUsuarioMes1, setInfoLeadUsuarioMes1] = useState([]);
    const [infoLeadUsuarioMes2, setInfoLeadUsuarioMes2] = useState([]);
    const [infoLeadUsuarioMes3, setInfoLeadUsuarioMes3] = useState([]);
    const [infoLeadUsuarioMes4, setInfoLeadUsuarioMes4] = useState([]);
    const [infoLeadUsuarioMes5, setInfoLeadUsuarioMes5] = useState([]);
    const [infoLeadUsuarioMes6, setInfoLeadUsuarioMes6] = useState([]);
    
    const [infoLeadMes1, setInfoLeadMes1] = useState([]);
    const [infoLeadMes2, setInfoLeadMes2] = useState([]);
    const [infoLeadMes3, setInfoLeadMes3] = useState([]);
    const [infoLeadMes4, setInfoLeadMes4] = useState([]);
    const [infoLeadMes5, setInfoLeadMes5] = useState([]);
    const [infoLeadMes6, setInfoLeadMes6] = useState([]);
    
    const [infoTotalLeadInstituicao, setInfoTotalLeadInstituicao] = useState([]);
    const [infoLeadInstituicaoAtivo, setInfoLeadInstituicaoAtivo] = useState();

    const [infoMes1, setInfoMes1] = useState([]);
    const [infoMes2, setInfoMes2] = useState([]);
    const [infoMes3, setInfoMes3] = useState([]);
    const [infoMes4, setInfoMes4] = useState([]);
    const [infoMes5, setInfoMes5] = useState([]);
    const [infoMes6, setInfoMes6] = useState([]);

    // Cards
    useEffect(() => {

        api.get(`/demandas/dashboard/${infoUsuario.id}`).then((res) => {
            setInfoDashboard(res.data)
        })
        
    }, [])

    // Gráfico visitante
    useEffect(() => {
        
        const dataAtual = new Date();
        const mesAtual = dataAtual.getMonth() + 1;

        api.get(`/usuarios/grafico-visitante/${dataAtual.getFullYear()}/0${mesAtual-1}`).then((res) => {
            setInfoVisitanteMes6(res.data[0])
            setInfoLeadMes6(res.data[1])
            setInfoMes6(mesAtual-1)
        })

        api.get(`/usuarios/grafico-visitante/${dataAtual.getFullYear()}/0${mesAtual-2}`).then((res) => {
            setInfoVisitanteMes5(res.data[0])
            setInfoLeadMes5(res.data[1])
            setInfoMes5(mesAtual-2)
        })

        api.get(`/usuarios/grafico-visitante/${dataAtual.getFullYear()}/0${mesAtual-3}`).then((res) => {
            setInfoVisitanteMes4(res.data[0])
            setInfoLeadMes4(res.data[1])
            setInfoMes4(mesAtual-3)
        })

        api.get(`/usuarios/grafico-visitante/${dataAtual.getFullYear()}/0${mesAtual-4}`).then((res) => {
            setInfoVisitanteMes3(res.data[0])
            setInfoLeadMes3(res.data[1])
            setInfoMes3(mesAtual-4)
        })

        api.get(`/usuarios/grafico-visitante/${dataAtual.getFullYear()}/0${mesAtual-5}`).then((res) => {
            setInfoVisitanteMes2(res.data[0])
            setInfoLeadMes2(res.data[1])
            setInfoMes2(mesAtual-5)
        })

        api.get(`/usuarios/grafico-visitante/${dataAtual.getFullYear()}/0${mesAtual-6}`).then((res) => {
            setInfoVisitanteMes1(res.data[0])
            setInfoLeadMes1(res.data[1])
            setInfoMes1(mesAtual-6)
        })

    }, [])

    // Gráfico lead Usuário
    useEffect(() => {
        
        const dataAtual = new Date();
        const mesAtual = dataAtual.getMonth() + 1;

        api.get(`/usuarios/lead-usuario/${dataAtual.getFullYear()}/0${mesAtual-1}`).then((res) => {
            setInfoLeadUsuarioMes6(res.data)
            setInfoMes6(mesAtual-1)
        })

        api.get(`/usuarios/lead-usuario/${dataAtual.getFullYear()}/0${mesAtual-2}`).then((res) => {
            setInfoLeadUsuarioMes5(res.data)
            setInfoMes5(mesAtual-2)
        })

        api.get(`/usuarios/lead-usuario/${dataAtual.getFullYear()}/0${mesAtual-3}`).then((res) => {
            setInfoLeadUsuarioMes4(res.data)
            setInfoMes4(mesAtual-3)
        })

        api.get(`/usuarios/lead-usuario/${dataAtual.getFullYear()}/0${mesAtual-4}`).then((res) => {
            setInfoLeadUsuarioMes3(res.data)
            setInfoMes3(mesAtual-4)
        })

        api.get(`/usuarios/lead-usuario/${dataAtual.getFullYear()}/0${mesAtual-5}`).then((res) => {
            setInfoLeadUsuarioMes2(res.data)
            setInfoMes2(mesAtual-5)
        })

        api.get(`/usuarios/lead-usuario/${dataAtual.getFullYear()}/0${mesAtual-6}`).then((res) => {
            setInfoLeadUsuarioMes1(res.data)
            setInfoMes1(mesAtual-6)
        })

    }, [])

    // Gráfico Lead Instituicao
    useEffect(() => {
        
        let soma = 0;
        let lead = 0;

        api.get(`/usuarios/lead-instituicao`).then((res) => {

            res.data.forEach(element => {
                soma+=element;
            });

            setInfoTotalLeadInstituicao(soma)

            lead = res.data[0]

            console.log(infoTotalLeadInstituicao)
            setInfoLeadInstituicaoAtivo(lead-infoTotalLeadInstituicao)

        })

    }, [])

    return (
        <>
            <HeaderApp/>

            <div className="dashboard-sysadmin" >
                <div className="dashboard-sysadmin-container">
                    <div className="dashboard-sysadmin-metricas-container">
                        <h2>Métricas de cadastro</h2>
                        <div className="dashboard-sysadmin-metricas-card-container">
                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{infoDashboard.qtdInstituicao}</p>
                                <p>Instituições</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{infoDashboard.qtdUsuario}</p>
                                <p>Usuários</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{infoDashboard.qtdAnimal}</p>
                                <p>Animais</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{infoDashboard.qtdPadrinho}</p>
                                <p>Padrinhos</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{infoDashboard.qtdAdmin}</p>
                                <p>Administradores</p>
                            </div>
                        </div>
                    </div>

                    <div className="dashboard-sysadmin-metricas-graficos-container">

                        <div className="dashboard-sysadmin-metricas-graficos">
                            <div className="dashboard-sysadmin-metricas-grafico">
                                <h2>Conversão de visitantes por mês</h2>
                                <div className="dashboard-sysadmin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={[
                                            ["Mês", "Qtd Visitantes", "Qtd Usuários"], 
                                            ["0"+infoMes1, infoVisitanteMes1, infoLeadMes1], 
                                            ["0"+infoMes2, infoVisitanteMes2, infoLeadMes2], 
                                            ["0"+infoMes3, infoVisitanteMes3, infoLeadMes3], 
                                            ["0"+infoMes4, infoVisitanteMes4, infoLeadMes4], 
                                            ["0"+infoMes5, infoVisitanteMes5, infoLeadMes5], 
                                            ["0"+infoMes6, infoVisitanteMes6, infoLeadMes6]
                                        ]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>

                            <div className="dashboard-sysadmin-metricas-grafico">
                                <h2>Conversão de leads para clientes</h2>
                                <div className="dashboard-sysadmin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={[
                                            ["Mês", "Qtd Leads", "Qtd Clientes"], 
                                            ["0"+infoMes1, infoLeadUsuarioMes1[0], infoLeadUsuarioMes1[1]], 
                                            ["0"+infoMes2, infoLeadUsuarioMes2[0], infoLeadUsuarioMes2[1]], 
                                            ["0"+infoMes3, infoLeadUsuarioMes3[0], infoLeadUsuarioMes3[1]], 
                                            ["0"+infoMes4, infoLeadUsuarioMes4[0], infoLeadUsuarioMes4[1]], 
                                            ["0"+infoMes5, infoLeadUsuarioMes5[0], infoLeadUsuarioMes5[1]], 
                                            ["0"+infoMes6, infoLeadUsuarioMes6[0], infoLeadUsuarioMes6[1]]
                                        ]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>
                        </div>

                        <div className="dashboard-sysadmin-metricas-graficos">

                            <div className="dashboard-sysadmin-metricas-grafico">
                                <h2>Conversão de leads para clientes instituição no mês anterior</h2>
                                <div className="dashboard-sysadmin-metricas-grafico-container">
                                    <Chart
                                        chartType="PieChart"
                                        data={[
                                            ["", ""], 
                                            ["Instituição Ativa", infoLeadInstituicaoAtivo], 
                                            ["Instituição Inativa", infoTotalLeadInstituicao-infoLeadInstituicaoAtivo]
                                        ]}
                                        options={options}
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

export default DashboardSysAdmin;