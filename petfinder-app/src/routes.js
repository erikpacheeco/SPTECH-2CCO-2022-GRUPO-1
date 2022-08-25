import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./Pages/Login";
import Cadastro from "./Pages/Cadastro";
import HomeUser from "./Pages/HomeUser";
import ListaPet from "./Pages/ListaPet";
import CadastroPet from "./Pages/CadastroPet"
import EditarColaborador from "./Pages/EditarColaborador";
import CadastrarColaborador from "./Pages/CadastrarColaborador";
import ListaColaborador from "./Pages/ListaColaborador";
import PerfilPetUsuario from "./Pages/PerfilPetUsuario";
import PerfilPetInstituicao from "./Pages/PerfilPetInstituicao";
import MeusPremios from "./Pages/MeusPremios";

function Rotas() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/cadastro" element={<Cadastro/>} />
                <Route path="/" element={<Login/>}/>
                <Route path="/home-user" element={<HomeUser/>} />
                <Route path="/lista-pet" element={<ListaPet/>} />
                <Route path="/cadastro-pet" element={<CadastroPet/>} />
                <Route path="/editar-colaborador" element={<EditarColaborador/>} />
                <Route path="/cadastrar-colaborador" element={<CadastrarColaborador/>} />
                <Route path="/lista-colaborador" element={<ListaColaborador/>} /> 
                <Route path="/cadastrar-colaborador" element={<CadastrarColaborador/>} /> 
                <Route path="/perfil-pet-usuario" element={<PerfilPetUsuario/>} /> 
                <Route path="/perfil-pet-instituicao" element={<PerfilPetInstituicao/>} /> 
                <Route path="/meus-premios" element={<MeusPremios/>} />
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;