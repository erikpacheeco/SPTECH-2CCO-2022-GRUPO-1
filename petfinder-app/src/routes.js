import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./Pages/Login";
import Cadastro from "./Pages/CadastroUsuario";
import HomeUser from "./Pages/HomeUser";
import ListaPet from "./Pages/ListaPet";
import CadastroPet from "./Pages/CadastroPet"
import EditarColaborador from "./Pages/EditarColaborador";
import CadastrarColaborador from "./Pages/CadastrarColaborador";
import ListaColaborador from "./Pages/ListaColaborador";
import PerfilPetUsuario from "./Pages/PerfilPetUsuario";
import PerfilPetInstituicao from "./Pages/PerfilPetInstituicao";
import MeusPremios from "./Pages/MeusPremios";
import VerMais from "./Pages/VerMais";
import DashboardSysAdmin from "./Pages/DashboardSysAdmin";
import DashboardChatOps from "./Pages/DashboardChatOps";
import DashboardAdmin from "./Pages/DashboardAdmin";
import React from "react";

function Rotas() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/cadastro-user" element={<Cadastro/>} />
                <Route path="/" element={<Login/>}/>
                <Route path="/home-user" element={<HomeUser/>} />
                <Route path="/lista-pet" element={<ListaPet/>} />
                <Route path="/cadastro-pet" element={<CadastroPet/>} />
                <Route path="/editar-colaborador" element={<EditarColaborador/>} />
                <Route path="/cadastro-colaborador" element={<CadastrarColaborador/>} />
                <Route path="/lista-colaborador" element={<ListaColaborador/>} /> 
                <Route path="/perfil-pet-usuario/:id" element={<PerfilPetUsuario/>} /> 
                <Route path="/perfil-pet-instituicao/:id" element={<PerfilPetInstituicao/>} /> 
                <Route path="/meus-premios" element={<MeusPremios/>} />
                <Route path="/ver-mais" element={<VerMais/>} />
                <Route path="/dashboard-sysadmin" element={<DashboardSysAdmin/>} />
                <Route path="/dashboard-chatops" element={<DashboardChatOps/>} />
                <Route path="/dashboard-admin" element={<DashboardAdmin/>} />
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;