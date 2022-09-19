import './lista-pet.css';
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import PetListItem from "../../Components/PetListItem";
import add from "../../Images/plus.svg"
import file from "../../Images/file-txt.svg"
import api from "../../Api"
import React, { useEffect, useState } from 'react';
import { calculateNewValue } from '@testing-library/user-event/dist/utils';
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

function ListaPet() {

    const [allPets, setAllPets] = useState([]);
    const [pets, setPets] = useState([]);

    function handleAddItemList() {
        console.log(allPets)
    }

    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));

    // function handleFileImport(event) {
    //     event.preventDefault()
    //     console.log(allPets)
    // }

    useEffect(() => {
        api.get(`/pets/instituicao/${objUser.fkInstituicao.id}`).then(res => {
            setAllPets(res.data)
        })
    })

    return (
        <>

            <div className="lista-pet-root">
                <HeaderApp
                    
                    sideItens={[
                        // <SideBarItem icon={Dashboard} label="Dashboard"/>,
                        // <SideBarItem icon={Pets} label="Pets"/>,
                        // <SideBarItem icon={Demandas} label="Demandas"/>,
                        // <SideBarItem icon={Padrinhos} label="Padrinhos"/>,
                        // <SideBarItem icon={MeuPerfil} label="Meu Perfil"/>,
                        // <SideBarItem icon={PerfilInstituicao} label="Perfil Instituição"/>,
                        // <SideBarItem icon={Colaboradores} label="Colaboradores Cadastrados"/>,
                        // <SideBarItem icon={Duvida} label="Dúvida"/>
                    ]}
                
                    itens={[
                        <NavItem label="Dashboard" />,
                        <NavItem label="Padrinhos" />,
                        <NavItem label="Demandas" />,
                        <NavItem isSelected={true} label="Colaboradores" />
                    ]}
                />

                <div className="lista-pet">
                    <div className="lista-pet-container">
                        <div className="lista-pet-title">
                            <h2>Pets Cadastrados</h2>
                            <div className="lista-pet-buttons">
                                <label htmlFor="fileImport">
                                    <div className="lista-pet-add-icon" onClick={handleAddItemList}>
                                        <img src={file} />
                                        <input type="file" accept=".txt" id="fileImport" style={{display: "none"}}/>
                                    </div>
                                </label>
                                <div className="lista-pet-add-icon" onClick={handleAddItemList}>
                                    <img src={add} />
                                </div>
                            </div>
                        </div>
                        <div id="idCadastroPetList" className="lista-pet-list">
                            {
                                allPets.map((pet) => (
                                    <PetListItem
                                        nome={pet.nome}
                                        especie={pet.especie}
                                        raca={pet.raca}
                                        idade={pet.idade}
                                        porte={pet.porte}
                                    />
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

export default ListaPet;