import './DashboardAdmin.css';
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import React from "react";
import { Chart } from "react-google-charts";
import VLibras from "@djpfs/react-vlibras"
import { useEffect, useState } from "react";
import api from "../../Api"
import dash from "../../Images/data-graph.svg"
import pet from "../../Images/paw.svg"
import padrinho from "../../Images/padrinhos.svg"
import perfil from "../../Images/people.svg"
import perfilInstituicao from "../../Images/user-business.svg"
import colaborador from "../../Images/colaboradores.svg"
import duvida from "../../Images/duvida.svg"
import SideBarItem from '../../Components/SideBarItem';

export const data = [
    ["Mês", "Padrinhos", "Prêmios Postados"],
    ["Fev", 21, 10],
    ["Mar", 23, 7],
    ["Abr", 16, 2],
    ["Mai", 23, 9],
    ["Jun", 39, 20]
];

function DashboardAdmin() {
    const [infoUsuario, setInfoUsuario] = useState([])

    const [infoTotalPadrinho, setTotalPadrinho] = useState([])
    const [infoTotalResgate, setTotalResgate] = useState([])
    //const [infoTotalUsuario, setTotalUsuario] = useState([])
    //const [infoTotalPet, setTotalPet] = useState([])
    //const [infoTotalInstituicao, setTotalInstituicao] = useState([])
    const [infoTotalAdm, setTotalAdm] = useState([])

    useEffect(() => {
        const infoUsuario = JSON.parse(localStorage.getItem('petfinder_user'));
        if (infoUsuario) {
            setInfoUsuario(infoUsuario);
        }

        api.get(`/usuarios/padrinhos/${infoUsuario.fkInstituicao.id}`).then((res) => {
            setTotalPadrinho(res.data)
        })
        
        /*
        api.get(`/pets/premios-instituicao/${infoUsuario.fkInstituicao.id}`).then((res) => {
            setTotalResgate(res.data)
        })
        api.get(`/usuarios/por-instituicao/${infoUsuario.fkInstituicao.id}`).then((res) => {
            setTotalUsuario(res.data)
        })
        api.get(`/pets/instituicao/${infoUsuario.fkInstituicao.id}`).then((res) => {
            setTotalPet(res.data)
        })
        api.get(`/instituicoes`).then((res) => {
            setTotalInstituicao(res.data)
        })
        */
        api.get(`/usuarios/acesso/${infoUsuario.fkInstituicao.id}/${infoUsuario.nivelAcesso}`).then((res) => {
            setTotalAdm(res.data)
        })
    })

    return(
        <>
            <HeaderApp 
                sideItens={[
                    <SideBarItem label="Página Inicial" icon={dash} navigateTo={"/dashboard-admin"}/>,
                    <SideBarItem label="Pets" icon={pet} navigateTo={"/lista-pet"}/>,
                    <SideBarItem label="Padrinhos" icon={padrinho} navigateTo={"/dashboard-admin"}/>,
                    <SideBarItem label="Meu Perfil" icon={perfil} navigateTo={"/dashboard-admin"}/>,
                    <SideBarItem label="Perfil Instituição" icon={perfilInstituicao} navigateTo={"/dashboard-admin"}/>,
                    <SideBarItem label="Colaboradores Cadastrados" icon={colaborador} navigateTo={"/"}/>,
                    <SideBarItem label="Dúvida" icon={duvida} navigateTo={"/"}/>
                ]}
                
                itens={[
                    <NavItem isSelected={true} label="Dashboard" navigateTo="/meus-premios"/>,
                    <NavItem label="Admin Cadastrados" />,
                    <NavItem label="Instituições Cadastrados" />,
                    <NavItem label="Dúvidas" />
                ]}
            />

            <div className="dashboard-admin">
                <div className="dashboard-admin-container">
                    <div className="dashboard-admin-metricas-container">
                        <h2>Métricas de cadastro</h2>
                        <div className="dashboard-admin-metricas-card-container">
                            <div className="dashboard-admin-metricas-card">
                                <p>X</p>
                                <p>Instituições</p>
                            </div>

                            <div className="dashboard-admin-metricas-card">
                                <p>X</p>
                                <p>Usuários</p>
                            </div>

                            <div className="dashboard-admin-metricas-card">
                                <p>X</p>
                                <p>Animais</p>
                            </div>

                            <div className="dashboard-admin-metricas-card">
                                <p>X</p>
                                <p>Padrinhos</p>
                            </div>

                            <div className="dashboard-admin-metricas-card">
                                <p>X</p>
                                <p>Administradores</p>
                            </div>
                        </div>
                    </div>

                    <div className="dashboard-admin-metricas-graficos-container">
                        
                        <div className="dashboard-admin-metricas-graficos">
                            <div className="dashboard-admin-metricas-grafico">
                                <h2>Pets resgatados esse mês</h2>
                                <div className="dashboard-admin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={[["", "Resgate"], ["Fev", 38], ["Mar", 43], ["Abr", 35], ["Mai", 20], ["Jun", 44]]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>

                            <div className="dashboard-admin-metricas-grafico">
                                <h2>Padrinhos por mês</h2>
                                <div className="dashboard-admin-metricas-grafico-container">
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

                        <div className="dashboard-admin-metricas-graficos">
                            
                            <div className="dashboard-admin-metricas-grafico-1">
                                <h2>Prêmios adicionados por</h2>
                                <div className="dashboard-admin-metricas-grafico-botoes">

                                    <input
                                        type="checkbox"
                                    />
                                    <button
                                        type="button"
                                        onClick={() => {
                                        }}
                                    >
                                        Semana
                                    </button>

                                    <input
                                        type="checkbox"
                                    />
                                    <button
                                        type="button"
                                        onClick={() => {
                                        }}
                                    >
                                        Mês
                                    </button>
                                </div>
                                <div className="dashboard-admin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={[["", "Prêmios"], ["Fev", 38], ["Mar", 43], ["Abr", 35], ["Mai", 20], ["Jun", 44]]}
                                        width="100%"
                                        height="100%"
                                        legendToggle
                                    />
                                </div>
                            </div>

                            <div className="dashboard-admin-metricas-grafico">
                                <h2>Categorias de demandas mais frequentes por</h2>

                                <div className="dashboard-admin-metricas-grafico-botoes">

                                    <input
                                        type="checkbox"
                                    />
                                    <button
                                        type="button"
                                        onClick={() => {
                                        }}
                                    >
                                        Semana
                                    </button>

                                    <input
                                        type="checkbox"
                                    />
                                    <button
                                        type="button"
                                        onClick={() => {
                                        }}
                                    >
                                        Mês
                                    </button>
                                </div>
                                
                                <div className="dashboard-admin-metricas-grafico-container">
                                    <Chart
                                        chartType="Bar"
                                        data={[["", "Demanda"], ["Pagamento", 38], ["Adoção", 43], ["Resgate", 35]]}
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