import './perfil-usuario.css';

import HeaderApp from "../../Components/HeaderApp";
import React from "react";
import EditarIcon from "../../Images/edit-two.svg"
import PerfilUsuarioFuncoes from "./perfil-usuario-funcoes.js"
// import { Chart } from "react-google-charts";
import MedalhaNoBronze from "./../../Images/pet-friendly-No-bronze.svg"
import MedalhaNoPrata from "./../../Images/pet-friendly-No-prata.svg"
import MedalhaNoOuro from "./../../Images/pet-friendly-No-ouro.svg"

// function VerificarComplemento(objUser){
//     if (objUser.endereco.complemento == null) {
//         return (
//             <input className="perfil-usuario-texto perfil-usuario-input"
//                 id="email"
//                 type="text" 
//                 name="email"
//                 value={objUser.endereco.complemento}
//                 // onChange={}
//             /> 
//         )
        
//     }
// }

function PerfilUsuario(){
    
    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));

    return(
        <>
            <HeaderApp/>
            <div className="perfil-usuario-root">
                <div className="perfil-usuario-root-container">
                    <div className="perfil-usuario-box-titulo">
                        <span>Meu Perfil</span>
                    </div>
                    <div className="perfil-usuario-box">
                        <form>
                            <div className="perfil-usuario-card">
                                <div className="perfil-usuario-card-container">
                                    <div className="perfil-usuario-div-edit">
                                        <input className="perfil-usuario-titulo perfil-usuario-input"
                                            id="nome"
                                            type="text" 
                                            name="nome"
                                            value={objUser.nome}
                                            // onChange={}
                                        />
                                        <button className="perfil-usuario-btn-edit" onClick={PerfilUsuarioFuncoes}>
                                            <span>Editar</span>
                                            <img src={EditarIcon} alt="Editar" />
                                        </button>
                                        {/* <button className="perfil-usuario-btn-salvar">
                                            <span>Salvar</span>
                                            <img src={EditarIcon} alt="Salvar" />
                                        </button> */}
                                    </div>
                                    <div className="perfil-usuario-div-edit">
                                        <input className="perfil-usuario-texto perfil-usuario-input"
                                            id="email"
                                            type="text" 
                                            name="email"
                                            value={objUser.email}
                                            // onChange={}
                                        />
                                    </div>
                                </div>
                            </div>
                            
                           <div className="perfil-usuario-card">
                                <div className="perfil-usuario-card-container">
                                    <span className="perfil-usuario-titulo">Endreço</span>
                                    <div className="perfil-usuario-div-edit">
                                        <input className="perfil-usuario-texto perfil-usuario-input"
                                            id="email"
                                            type="text" 
                                            name="email"
                                            value={objUser.endereco.rua}
                                            // onChange={}
                                        />
                                        <input className="perfil-usuario-texto perfil-usuario-input"
                                            id="email"
                                            type="text" 
                                            name="email"
                                            value={objUser.endereco.num}
                                            // onChange={}
                                        />
                                        {/* VerificarComplemento({objUser}); */}
                                        <input className="perfil-usuario-texto perfil-usuario-input"
                                            id="email"
                                            type="text" 
                                            name="email"
                                            value={objUser.endereco.bairro}
                                            // onChange={}
                                        />
                                    </div>
                                    <div className="perfil-usuario-div-edit">
                                        <input className="perfil-usuario-texto perfil-usuario-input"
                                            id="email"
                                            type="text" 
                                            name="email"
                                            value={objUser.endereco.cidade}
                                            // onChange={}
                                        />
                                        <input className="perfil-usuario-texto perfil-usuario-input"
                                            id="email"
                                            type="text" 
                                            name="email"
                                            value={objUser.endereco.uf}
                                            // onChange={}
                                        />
                                    </div>
                                </div>
                           </div>

                            <div className="perfil-usuario-card">
                                <div className="perfil-usuario-card-container">
                                    <div className="perfil-usuario-titulo">
                                        <span>Pontuação</span>
                                    </div>
                                    <div className="perfil-usuario-pontuacao">
                                        <div className="perfil-usuario-pontuacao-dash">
                                            
                                        </div>
                                        <div className="perfil-usuario-pontuacao-info">
                                            <span>Total: </span>
                                            <span>Próxima Medalha: </span>
                                            <div className="perfil-usuario-medalha">
                                                <img src={MedalhaNoBronze} alt="MedalhaNoBronze" />
                                                <img src={MedalhaNoPrata} alt="MedalhaNoPrata" />
                                                <img src={MedalhaNoOuro} alt="MedalhaNoOuro" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                           </div>
                        </form>

                        <div className="perfil-usuario-pets-apadrinhado">

                        </div>
                    </div>
                </div>
            </div>
        </>
    )

}

export default PerfilUsuario;