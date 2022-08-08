import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./Pages/Login";
import Cadastro from "./Pages/Cadastro";
import HomeUser from "./Pages/HomeUser";
import ListaPet from "./Pages/ListaPet";
import CadastroPet from "./Pages/CadastroPet"

function Rotas() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/cadastro" element={<Cadastro/>} />
                <Route path="/" element={<Login/>}/>
                <Route path="/home-user" element={<HomeUser/>} />
                <Route path="/lista-pet" element={<ListaPet/>} />
                <Route path="/cadastro-pet" element={<CadastroPet/>} />
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;