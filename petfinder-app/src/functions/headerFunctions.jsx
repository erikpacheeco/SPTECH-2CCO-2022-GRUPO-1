import NavItem from "../Components/NavItem"
import SideBarItem from "../Components/SideBarItem"

// Icones página
import Home from "../Images/home.svg";
import Dashboard from "../Images/data-graph.svg";
import UsuariosCadastrados from "../Images/colaboradores.svg";
import InstituicaoCadastrada from "../Images/instituicao-cadastrada.svg";
import Pets from "../Images/paw.svg";
import Demandas from "../Images/attention-icon.svg";
import Padrinhos from "../Images/padrinhos.svg";
import MeuPerfil from "../Images/people.svg";
import Mensagem from "../Images/message.svg";
import MeusPremios from "../Images/picture.svg";
import React from "react";

const headerFunction = {
    sideBarNivelAcesso: function (nivelAcesso) {
        // const [infoUsuario, setInfoUsuario] = useState(JSON.parse(localStorage.getItem('petfinder_user')));
        const infoUsuario  = JSON.parse(localStorage.getItem('petfinder_user'));
        
        let listaSideBar = []
    
        if(nivelAcesso === "sysadm"){
            listaSideBar = [
                // <SideBarItem icon={Home} label="Página Inicial" navigateTo={"/dashboard-sysadmin"}/>,
                <SideBarItem icon={Dashboard} label="Dashboard" navigateTo={"/dashboard-sysadmin"}/>,
                <SideBarItem icon={UsuariosCadastrados} label="Administradores Cadastrados" navigateTo={`/lista-adm/${infoUsuario.id}`}/>,
                <SideBarItem icon={InstituicaoCadastrada} label="Instituições Cadastradas" navigateTo={"/lista-instituicao"}/>,
                <SideBarItem icon={MeuPerfil} label="Meu Perfil" navigateTo={`/perfil/${infoUsuario.id}`}/>
            ]
        } else if(nivelAcesso === "adm"){
            listaSideBar = [
                <SideBarItem icon={Dashboard} label="Dashboard" navigateTo={"/dashboard-admin"}/>,
                <SideBarItem icon={Pets} label="Pets" navigateTo={"/lista-pet"}/>,
                <SideBarItem icon={Demandas} label="Demandas" navigateTo={"/chat"}/>,
                <SideBarItem icon={Padrinhos} label="Padrinhos" navigateTo={"/lista-padrinho"}/>,
                <SideBarItem icon={MeuPerfil} label="Meu Perfil" navigateTo={`/perfil/${infoUsuario.id}`}/>,
                <SideBarItem icon={UsuariosCadastrados} label="Colaboradores Cadastrados" navigateTo={`/lista-colaborador/${infoUsuario.id}`}/>
            ]
        } else if (nivelAcesso === "chatops"){
            listaSideBar = [
                <SideBarItem icon={MeuPerfil} label="Meu Perfil" navigateTo={`/perfil/${infoUsuario.id}`}/>,
                <SideBarItem icon={Dashboard} label="Dashboard" navigateTo={"/dashboard-chatops"}/>,
                <SideBarItem icon={Demandas} label="Demandas" navigateTo={"/chat"}/>
            ]
        } else {
            listaSideBar = [
                <SideBarItem icon={Home} label="Página Inicial" navigateTo={"/home-user"}/>,
                <SideBarItem icon={Mensagem} label="Mensagens" navigateTo={"/chat"}/>,
                <SideBarItem icon={MeuPerfil} label="Meu Perfil" navigateTo={"/perfil-usuario"}/>,
                <SideBarItem icon={MeusPremios} label="Meus Prêmios" navigateTo={`/meus-premios/${infoUsuario.id}`}/>
            ]
        }
    
        return listaSideBar;
    },
    
    headerNivelAcesso: function (nivelAcesso){
        const infoUsuario  = JSON.parse(localStorage.getItem('petfinder_user'));
        const paginaAtual = document.location.pathname;
        let listaHeader = []
    
        if(nivelAcesso === "sysadm"){
            listaHeader = [
                <NavItem isSelected={paginaAtual === "/dashboard-sysadmin" ? true : false} label="Dashboard" navigateTo={"/dashboard-sysadmin"}/>,
                <NavItem isSelected={paginaAtual === "" ? true : false} label="Admin Cadastrados" navigateTo={`/lista-adm/${infoUsuario.id}`}/>,
                <NavItem isSelected={paginaAtual === "" ? true : false} label="Instituições Cadastradas" navigateTo={"/lista-instituicao"}/>
            ]
        } else if(nivelAcesso === "adm"){
            listaHeader = [
                <NavItem isSelected={paginaAtual === "/dashboard-admin" ? true : false} label="Dashboard" navigateTo={"/dashboard-admin"}/>,
                <NavItem isSelected={paginaAtual === "" ? true : false} label="Padrinhos" navigateTo={"/lista-padrinho"}/>,
                <NavItem isSelected={paginaAtual === "/chat" ? true : false}label="Demandas" navigateTo={"/chat"}/>
            ]
        } else if(nivelAcesso === "chatops"){
            listaHeader = [
                <NavItem isSelected={paginaAtual === "/dashboard-chatops" ? true : false} label="Dashboard" navigateTo={"/dashboard-chatops"}/>,
                <NavItem isSelected={paginaAtual === "/chat" ? true : false} label="Demandas" navigateTo={"/chat"}/>
            ]
        } else {
            listaHeader = [
                <NavItem isSelected={paginaAtual === "/home-user" ? true : false} label="Página Inicial" navigateTo={"/home-user"}/>,
                <NavItem isSelected={paginaAtual === `/meus-premios/${infoUsuario.id}` ? true : false} label="Meus Prêmios" navigateTo={`/meus-premios/${infoUsuario.id}`}/>,
                <NavItem isSelected={paginaAtual === "/chat" ? true : false} label="Mensagens" navigateTo={"/chat"}/>
            ]
        }
    
        return listaHeader;
    }

 }


export default headerFunction;