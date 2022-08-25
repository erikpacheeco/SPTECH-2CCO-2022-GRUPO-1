import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./Pages/Login";
import Cadastro from "./Pages/Cadastro";
import HomeUser from "./Pages/HomeUser";
import ListaPet from "./Pages/ListaPet";
import CadastroPet from "./Pages/CadastroPet"
import EditarColaborador from "./Pages/EditarColaborador";
import CadastrarColaborador from "./Pages/CadastrarColaborador";
<<<<<<< HEAD
import ListaColaborador from "./Pages/ListaColaborador";
=======
import PerfilPetUsuario from "./Pages/PerfilPetUsuario";
import PerfilPetInstituicao from "./Pages/PerfilPetInstituicao";
>>>>>>> 677c41dc245f2b5182182797e3e52f52bc087f44

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
<<<<<<< HEAD
                <Route path="/cadastrar-colaborador" element={<CadastrarColaborador/>} />
                <Route path="/lista-colaborador" element={<ListaColaborador/>} /> 
=======
                <Route path="/cadastrar-colaborador" element={<CadastrarColaborador/>} /> 
                <Route path="/perfil-pet-usuario" element={<PerfilPetUsuario/>} /> 
                <Route path="/perfil-pet-instituicao" element={<PerfilPetInstituicao/>} /> 
>>>>>>> 677c41dc245f2b5182182797e3e52f52bc087f44
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;