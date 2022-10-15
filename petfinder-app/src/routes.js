import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./Pages/Login";
import CadastroUsuario from "./Pages/CadastroUsuario";
import CadastroInstituicao from "./Pages/CadastroInstituicao";
import CadastroColaborador from "./Pages/CadastroColaborador";
import HomeUser from "./Pages/HomeUser";
import ListaPet from "./Pages/ListaPet";
import CadastroPet from "./Pages/CadastroPet"
import EditarColaborador from "./Pages/EditarColaborador";
import ListaColaborador from "./Pages/ListaColaborador";
import ListaAdm from "./Pages/ListaAdm";
import EditarAdm from "./Pages/EditarAdm";
import CadastroAdm from "./Pages/CadastroAdm";
import PerfilPetUsuario from "./Pages/PerfilPetUsuario";
import PerfilPetInstituicao from "./Pages/PerfilPetInstituicao";
import MeusPremios from "./Pages/MeusPremios";
import VerMais from "./Pages/VerMais";
import DashboardSysAdmin from "./Pages/DashboardSysAdmin";
import DashboardChatOps from "./Pages/DashboardChatOps";
import DashboardAdmin from "./Pages/DashboardAdmin";
import ChatUsuario from "./Pages/ChatUsuario";
import PerfilUsuario from "./Pages/PerfilUsuario";
import ListaInstituicao from "./Pages/ListaInstituicao";
import Perfil from "./Pages/Perfil";
import ListaPadrinho from "./Pages/ListaPadrinho";
import React from "react";
import './global.css';
import AdicionarMimos from "./Pages/AdicionarMimos";

function Rotas() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/cadastro-user" element={<CadastroUsuario/>} />
                <Route path="/cadastro-instituicao" element={<CadastroInstituicao/>} />
                <Route path="/cadastro-colaborador/:id" element={<CadastroColaborador/>} />
                <Route path="/" element={<Login/>}/>
                <Route path="/home-user" element={<HomeUser/>} />
                <Route path="/lista-pet" element={<ListaPet/>} />
                <Route path="/cadastro-pet/:id" element={<CadastroPet/>} />
                <Route path="/editar-colaborador/:id" element={<EditarColaborador/>} />
                <Route path="/lista-colaborador/:id" element={<ListaColaborador/>} /> 
                <Route path="/lista-adm/:id" element={<ListaAdm/>} /> 
                <Route path="/editar-adm/:id" element={<EditarAdm/>} /> 
                <Route path="/cadastro-adm/:id" element={<CadastroAdm/>} />
                <Route path="/perfil-pet-usuario/:id" element={<PerfilPetUsuario/>} /> 
                <Route path="/perfil-pet-instituicao/:id" element={<PerfilPetInstituicao/>} /> 
                <Route path="/meus-premios/:id" element={<MeusPremios/>} />
                <Route path="/ver-mais" element={<VerMais/>} />
                <Route path="/dashboard-sysadmin" element={<DashboardSysAdmin/>} />
                <Route path="/dashboard-chatops" element={<DashboardChatOps/>} />
                <Route path="/dashboard-admin" element={<DashboardAdmin/>} />
                <Route path="/chat" element={<ChatUsuario/>} />
                <Route path="/perfil-usuario" element={<PerfilUsuario/>} />
                <Route path="/lista-instituicao" element={<ListaInstituicao/>} />
                <Route path="/perfil/:id" element={<Perfil/>} />
                <Route path="/adicionar-mimos/:id" element={<AdicionarMimos/>} />
                <Route path="/lista-padrinho" element={<ListaPadrinho/>} />
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;