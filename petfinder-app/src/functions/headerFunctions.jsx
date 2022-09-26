import NavItem from "../Components/NavItem"
import SideBarItem from "../Components/SideBarItem"

// Icones página
import Home from "../Images/home.svg";
import Dashboard from "../Images/data-graph.svg";
import UsuariosCadastrados from "../Images/colaboradores.svg";
import InstituicaoCadastrada from "../Images/instituicao-cadastrada.svg";
import Duvida from "../Images/duvida.svg";
import Pets from "../Images/paw.svg";
import Demandas from "../Images/attention-icon.svg";
import Padrinhos from "../Images/padrinhos.svg";
import MeuPerfil from "../Images/people.svg";
import PerfilInstituicao from "../Images/user-business.svg";
import Mensagem from "../Images/message.svg";
import MeusPremios from "../Images/picture.svg";
import Resgate from "../Images/icon_resgate.svg";
import React from "react";

const infoUsuario = JSON.parse(localStorage.getItem('petfinder_user'));

 const headerFunction = {
    
    sideBarNivelAcesso: function (nivelAcesso) {
        let listaSideBar = []
    
        if(nivelAcesso == "sysadm"){
            listaSideBar = [
                // <SideBarItem icon={Home} label="Página Inicial" navigateTo={"/dashboard-sysadmin"}/>,
                <SideBarItem icon={Dashboard} label="Dashboard" navigateTo={"/dashboard-sysadmin"}/>,
                <SideBarItem icon={UsuariosCadastrados} label="Administradores Cadastrados" navigateTo={`/lista-colaborador/${infoUsuario.id}`}/>,
                <SideBarItem icon={InstituicaoCadastrada} label="Instituições Cadastradas" navigateTo={"/"}/>,
                <SideBarItem icon={Duvida} label="Dúvidas" navigateTo={"/"}/>,
            ]
        } else if(nivelAcesso == "adm"){
            listaSideBar = [
                <SideBarItem icon={Dashboard} label="Dashboard" navigateTo={"/dashboard-admin"}/>,
                <SideBarItem icon={Pets} label="Pets" navigateTo={"/lista-pet"}/>,
                <SideBarItem icon={Demandas} label="Demandas" navigateTo={"/chat"}/>,
                <SideBarItem icon={Padrinhos} label="Padrinhos" navigateTo={"/"}/>,
                <SideBarItem icon={MeuPerfil} label="Meu Perfil" navigateTo={"/"}/>,
                <SideBarItem icon={PerfilInstituicao} label="Perfil Instituição" navigateTo={"/"}/>,
                <SideBarItem icon={UsuariosCadastrados} label="Colaboradores Cadastrados" navigateTo={`/lista-colaborador/${infoUsuario.id}`}/>,
                <SideBarItem icon={Duvida} label="Dúvida" navigateTo={"/"}/>
            ]
        } else if (nivelAcesso == "chatops"){
            listaSideBar = [
                <SideBarItem icon={MeuPerfil} label="Meu Perfil" navigateTo={"/"}/>,
                <SideBarItem icon={Dashboard} label="Dashboard" navigateTo={"/dashboard-chatops"}/>,
                <SideBarItem icon={Demandas} label="Demandas" navigateTo={"/chat"}/>,
                <SideBarItem icon={Duvida} label="Nova Dúvida" navigateTo={"/"}/>
            ]
        } else {
            listaSideBar = [
                <SideBarItem icon={Home} label="Página Inicial" navigateTo={"/home-user"}/>,
                <SideBarItem icon={Mensagem} label="Mensagens" navigateTo={"/chat"}/>,
                <SideBarItem icon={MeuPerfil} label="Meu Perfil" navigateTo={"/"}/>,
                <SideBarItem icon={MeusPremios} label="Meus Prêmios" navigateTo={"/meus-premios"}/>,
                <SideBarItem icon={Resgate} label="Solicitação de Resgate" navigateTo={"/"}/>,
            ]
        }
    
        return listaSideBar;
    },
    
    headerNivelAcesso: function (nivelAcesso){
        let listaHeader = []
    
        if(nivelAcesso == "sysadm"){
            listaHeader = [
                <NavItem label="Dashboard" navigateTo={"/dashboard-sysadmin"}/>,
                <NavItem label="Admin Cadastrados" navigateTo={`/lista-colaborador/${infoUsuario.id}`}/>,
                <NavItem label="Instituições Cadastradas" navigateTo={"/"}/>,
                <NavItem label="Dúvidas" navigateTo={"/"}/>
            ]
        } else if(nivelAcesso == "adm"){
            listaHeader = [
                <NavItem label="Dashboard" navigateTo={"/dashboard-admin"}/>,
                <NavItem label="Padrinhos" navigateTo={"/"}/>,
                <NavItem label="Demandas" navigateTo={"/chat"}/>
            ]
        } else if(nivelAcesso == "chatops"){
            listaHeader = [
                <NavItem label="Demandas" navigateTo={"/chat"}/>
            ]
        } else {
            listaHeader = [
                <NavItem label="Página Inicial" navigateTo={"/home-user"}/>,
                <NavItem label="Meus Prêmios" navigateTo={"/meus-premios"}/>,
                <NavItem label="Mensagens" navigateTo={"/chat"}/>
            ]
        }
    
        return listaHeader;
    }

 }


export default headerFunction;