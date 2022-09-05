import styles from "./styles.css";
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import plus from "../../Images/plus.svg";
import ColaboradorListaItem from '../../Components/ColaboradorListaItem';
import React, {useState, useEffect} from 'react';
import api from "../../Api";
import SideBarItem from "../../Components/SideBarItem";
import Dashboard from "../../Images/data-graph.svg";
import Pets from "../../Images/paw.svg";
import Demandas from "../../Images/attention-icon.svg";
import Padrinhos from "../../Images/padrinhos.svg";
import MeuPerfil from "../../Images/people.svg";
import PerfilInstituicao from "../../Images/user-business.svg";
import Colaboradores from "../../Images/colaboradores.svg";
import Duvida from "../../Images/duvida.svg";
import VLibras from "@djpfs/react-vlibras"


function ListaColaborador(){

    function handleAddItemList() {
        console.log("click!");
    }

    const [colaborador, setColaborador] = useState([]);
    const [user, setUser] = useState();

    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));

    // console.log(objUser.fkInstituicao.id);
 

    useEffect(() => {

        // corpo do useEffect. São as ações ou aquilo que eu quero que execute // ocorre quando a tela é renderizada 
        api.get(`/usuarios/por-instituicao/${objUser.fkInstituicao.id}`).then((res) => {
            setColaborador(res.data);

            console.log(res.data[0].cargo);
        })
        
      }, []);


    return(
        <>
            <div className="lista-colaborador-root">
                <HeaderApp
                
                    sideItens={[
                        <SideBarItem icon={Dashboard} label="Dashboard"/>,
                        <SideBarItem icon={Pets} label="Pets"/>,
                        <SideBarItem icon={Demandas} label="Demandas"/>,
                        <SideBarItem icon={Padrinhos} label="Padrinhos"/>,
                        <SideBarItem icon={MeuPerfil} label="Meu Perfil"/>,
                        <SideBarItem icon={PerfilInstituicao} label="Perfil Instituição"/>,
                        <SideBarItem icon={Colaboradores} label="Colaboradores Cadastrados"/>,
                        <SideBarItem icon={Duvida} label="Dúvida"/>
                    ]}
                
                    itens={[
                        <NavItem label="Dashboard" />,
                        <NavItem label="Padrinhos" />,
                        <NavItem label="Demandas" />,
                        <NavItem isSelected={true} label="Colaboradores" />
                    ]}
                />

                <div className="lista-colaborador">
                    <div className="lista-colaborador-container">
                        <div className="lista-colaborador-tittle">
                            <h2>Colaboradores Cadastrados</h2>
                            <div className="lista-colaborador-add-icon" onClick={handleAddItemList}>+</div>
                        </div>
                        <div className="lista-colaborador">
                            {
                                colaborador.map(c => (
                                    <ColaboradorListaItem key={c.id} nome={c.nome} cargo={c.cargo}/>
                                ))
                            }
                        </div>
                    </div>
                </div>
            </div>

            <VLibras forceOnload={true}></VLibras>
        </>
    )
}

export default ListaColaborador;