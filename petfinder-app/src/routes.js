import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./Pages/Login";
import CadastroUsuario from "./Pages/CadastroUsuario";
import HomeUser from "./Pages/HomeUser";
// import ListaPet from "./Pages/ListaPet";
// import CadastroPet from "./Pages/CadastroPet"
// import EditarColaborador from "./Pages/EditarColaborador";

function Rotas() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/cadastro-user" element={<CadastroUsuario/>} />
                <Route path="/" element={<Login/>}/>
                <Route path="/home-user" element={<HomeUser/>} />
                {/* <Route path="/lista-pet" element={<ListaPet/>} /> */}
                {/* <Route path="/cadastro-pet" element={<CadastroPet/>} /> */}
                {/* <Route path="/editar-colaborador" element={<EditarColaborador/>} /> */}
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;