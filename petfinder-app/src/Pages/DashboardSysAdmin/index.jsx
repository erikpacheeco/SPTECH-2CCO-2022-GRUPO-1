import './DashboardSysAdmin.css';
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import React from "react";
import { Chart } from "react-google-charts";
import VLibras from "@djpfs/react-vlibras"
import { useEffect, useState } from "react";
import api from "../../Api"
import SideBarItem from '../../Components/SideBarItem';
import inicial from "../../Images/home.svg"
import dash from "../../Images/data-graph.svg"
import adm from "../../Images/colaboradores.svg"
import instituicoes from "../../Images/padrinhos.svg"
import duvida from "../../Images/duvida.svg"

export const data = [
    ["Mês", "Padrinhos", "Mimos Postados"],
    ["Fev", 21, 10],
    ["Mar", 23, 7],
    ["Abr", 16, 2],
    ["Mai", 23, 9],
    ["Jun", 39, 20]
];

function DashboardSysAdmin() {
      
    const [infoUsuario, setInfoUsuario] = useState([])
    
    const [infoTotalInstituicao, setTotalInstituicao] = useState([])
    const [infoTotalUsuario, setTotalUsuario] = useState([])
    const [infoTotalPet, setTotalPet] = useState([])
    const [infoTotalPadrinho, setTotalPadrinho] = useState([])
    const [infoTotalAdm, setTotalAdm] = useState([])

    
    useEffect(() => {

        const infoUsuario = JSON.parse(localStorage.getItem('petfinder_user'));
        if (infoUsuario) {
            setInfoUsuario(infoUsuario);
        }
        
        api.get(`/instituicoes`).then((res) => {
            setTotalInstituicao(res.data)
        })
        api.get(`/usuarios`).then((res) => {
            setTotalUsuario(res.data)
        })
        api.get(`/pets`).then((res) => {
            setTotalPet(res.data)
        })
        /*
        api.get(`/usuarios/padrinhos`).then((res) => {
            setTotalPet(res.data)
        })
        */
        api.get(`/usuarios/nivel-acesso/${infoUsuario.nivelAcesso}`).then((res) => {
            setTotalAdm(res.data)
        })
    })
    
    return(
        <>
            <HeaderApp 
                sideItens={[
                    <SideBarItem label="Página Inicial" icon={inicial} navigateTo={"/dashboard-sysadmin"}/>,
                    <SideBarItem label="Dashboard" icon={dash} navigateTo={"/dashboard-sysadmin"}/>,
                    <SideBarItem label="Administradores Cadastrados" icon={adm} navigateTo={"/dashboard-sysadmin"}/>,
                    <SideBarItem label="Instituições Cadastrados" icon={instituicoes} navigateTo={"/dashboard-sysadmin"}/>,
                    <SideBarItem label="Dúvidas" icon={duvida} navigateTo={"/chat"}/>,
                ]}
                
                itens={[
                    <NavItem isSelected={true} label="Dashboard" navigateTo={"/dashboard-sysadmin"}/>,
                    <NavItem label="Admin Cadastrados" navigateTo={`/lista-colaborador/${infoUsuario.id}`}/>,
                    <NavItem label="Instituições Cadastrados" navigateTo={"/dashboard-sysadmin"}/>,
                    <NavItem label="Dúvidas" navigateTo={"/chat"}/>
                ]}
            />

            <div className="dashboard-sysadmin">
                <div className="dashboard-sysadmin-container">
                    <div className="dashboard-sysadmin-metricas-container">
                        <h2>Métricas de cadastro</h2>
                        <div className="dashboard-sysadmin-metricas-card-container">
                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{infoTotalInstituicao.length}</p>
                                <p>Instituições</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{infoTotalUsuario.length}</p>
                                <p>Usuários</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{infoTotalPet.length}</p>
                                <p>Animais</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{infoTotalPadrinho.length}</p>
                                <p>Padrinhos</p>
                            </div>

                            <div className="dashboard-sysadmin-metricas-card">
                                <p>{infoTotalAdm.length}</p>
                                <p>Administradores</p>
                            </div>
                        </div>
                    </div>

                    <div className="dashboard-sysadmin-metricas-graficos-container">
                        
                        <div className="dashboard-sysadmin-metricas-graficos">
                            <div className="dashboard-sysadmin-metricas-grafico">
                                <h2>Apadrinhamento por semana</h2>
                                <div className="dashboard-sysadmin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={[["", "Apadrin."], ["Seg", 38], ["Ter", 43], ["Qua", 35], ["Qui", 20], ["Sex", 44]]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>

                            <div className="dashboard-sysadmin-metricas-grafico">
                                <h2>Apadrinhamento por mês</h2>
                                <div className="dashboard-sysadmin-metricas-grafico-container">
                                    <Chart
                                        chartType="Line"
                                        data={data}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>
                        </div>

                        <div className="dashboard-sysadmin-metricas-graficos">
                            
                            <div className="dashboard-sysadmin-metricas-grafico">
                                <h2>Adoções por mês</h2>
                                <div className="dashboard-sysadmin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={[["", "Adoções"], ["Fev", 38], ["Mar", 43], ["Abr", 35], ["Mai", 20], ["Jun", 44]]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>

                            <div className="dashboard-sysadmin-metricas-grafico">
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