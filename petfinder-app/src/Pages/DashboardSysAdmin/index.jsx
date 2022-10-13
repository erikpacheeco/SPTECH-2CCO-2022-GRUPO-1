import './DashboardSysAdmin.css';
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
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
    
    const [infoLeadInstituicaoMesAnterior, setInfoLeadInstituicaoMesAnterior] = useState([]);

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

        api.get(`/usuarios/visitante/${dataAtual.getFullYear()}/0${mesAtual-1}`).then((res) => {
            setInfoVisitanteMes6(res.data)
            setInfoMes6(mesAtual-1)
        })

        api.get(`/usuarios/visitante/${dataAtual.getFullYear()}/0${mesAtual-2}`).then((res) => {
            setInfoVisitanteMes5(res.data)
            setInfoMes5(mesAtual-2)
        })

        api.get(`/usuarios/visitante/${dataAtual.getFullYear()}/0${mesAtual-3}`).then((res) => {
            setInfoVisitanteMes4(res.data)
            setInfoMes4(mesAtual-3)
        })

        api.get(`/usuarios/visitante/${dataAtual.getFullYear()}/0${mesAtual-4}`).then((res) => {
            setInfoVisitanteMes3(res.data)
            setInfoMes3(mesAtual-4)
        })

        api.get(`/usuarios/visitante/${dataAtual.getFullYear()}/0${mesAtual-5}`).then((res) => {
            setInfoVisitanteMes2(res.data)
            setInfoMes2(mesAtual-5)
        })

        api.get(`/usuarios/visitante/${dataAtual.getFullYear()}/0${mesAtual-6}`).then((res) => {
            setInfoVisitanteMes1(res.data)
            setInfoMes1(mesAtual-6)
        })

        
        api.get(`/usuarios/lead/${dataAtual.getFullYear()}/0${mesAtual-1}`).then((res) => {
            setInfoLeadMes6(res.data)
        })

        api.get(`/usuarios/lead/${dataAtual.getFullYear()}/0${mesAtual-2}`).then((res) => {
            setInfoLeadMes5(res.data)
        })

        api.get(`/usuarios/lead/${dataAtual.getFullYear()}/0${mesAtual-3}`).then((res) => {
            setInfoLeadMes4(res.data)
        })

        api.get(`/usuarios/lead/${dataAtual.getFullYear()}/0${mesAtual-4}`).then((res) => {
            setInfoLeadMes3(res.data)
        })

        api.get(`/usuarios/lead/${dataAtual.getFullYear()}/0${mesAtual-5}`).then((res) => {
            setInfoLeadMes2(res.data)
        })

        api.get(`/usuarios/lead/${dataAtual.getFullYear()}/0${mesAtual-6}`).then((res) => {
            setInfoLeadMes1(res.data)
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
        
        const dataAtual = new Date();
        const mesAnterior = dataAtual.getMonth();

        api.get(`/usuarios/lead-instituicao/${dataAtual.getFullYear()}/0${mesAnterior}`).then((res) => {
            setInfoLeadInstituicaoMesAnterior(res.data)
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
                                            ["0"+infoMes1, infoVisitanteMes1.length, infoLeadMes1.length], 
                                            ["0"+infoMes2, infoVisitanteMes2.length, infoLeadMes2.length], 
                                            ["0"+infoMes3, infoVisitanteMes3.length, infoLeadMes3.length], 
                                            ["0"+infoMes4, infoVisitanteMes4.length, infoLeadMes4.length], 
                                            ["0"+infoMes5, infoVisitanteMes5.length, infoLeadMes5.length], 
                                            ["0"+infoMes6, infoVisitanteMes6.length, infoLeadMes6.length]
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
                                            ["0"+infoMes1, infoLeadUsuarioMes1.length, 9], 
                                            ["0"+infoMes2, infoLeadUsuarioMes2.length, 12], 
                                            ["0"+infoMes3, infoLeadUsuarioMes3.length, 5], 
                                            ["0"+infoMes4, infoLeadUsuarioMes4.length, 15], 
                                            ["0"+infoMes5, infoLeadUsuarioMes5.length, 20], 
                                            ["0"+infoMes6, infoLeadUsuarioMes6.length, 22]
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
                                            ["Instituição Ativa", 20], 
                                            ["Instituição Inativa", 31]
                                        ]}
                                        options={options}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>

                            {/* <div className="dashboard-sysadmin-metricas-grafico">
                                <h2>Reporte de resgate por mês</h2>
                                <div className="dashboard-sysadmin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={[["", "Reporte"], ["Fev", 38], ["Mar", 43], ["Abr", 35], ["Mai", 20], ["Jun", 44]]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div> */}

                        </div>
                    </div>

                </div>
            </div>

            <VLibras forceOnload={true}></VLibras>
        </>
    )
}

export default DashboardSysAdmin;