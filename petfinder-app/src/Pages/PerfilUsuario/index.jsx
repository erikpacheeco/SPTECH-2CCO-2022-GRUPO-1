import './perfil-usuario.css';

import HeaderApp from "../../Components/HeaderApp";
import React from "react";

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
                            <div className="perfil-usuario-informacoes-pessoal">
                                <div className="perfil-usuario-informacoes-container">
                                    <div className="perfil-usuario-informacoes-edit">
                                        <input className="perfil-usuario-titulo perfil-usuario-input perfil-usuario-input85"
                                            id="nome"
                                            type="text" 
                                            name="nome"
                                            value={objUser.nome}
                                            // onChange={}
                                        />
                                        <button>Editar</button>
                                    </div>
                                    <input className="perfil-usuario-texto perfil-usuario-input"
                                        id="email"
                                        type="text" 
                                        name="email"
                                        value={objUser.email}
                                        // onChange={}
                                    />
                                </div>
                            </div>
                            
                            <div className="perfil-usuario-informacoes-endereco">
                                <div className="perfil-usuario-informacoes-container">
                                    <span>Endereço</span>
                                    <div>
                                        <input className="perfil-usuario-texto"
                                            id="cidade"
                                            type="text" 
                                            name="cidade"
                                            value={objUser.endereco.rua}
                                            // onChange={}
                                        />
                                        <input className="perfil-usuario-texto"
                                            id="cidade"
                                            type="text" 
                                            name="cidade"
                                            value={objUser.endereco.num}
                                            // onChange={}
                                        />
                                        {/* <input className="perfil-usuario-texto"
                                            id="cidade"
                                            type="text" 
                                            name="cidade"
                                            value={objUser.endereco.complemento == null ? "" : objUser.endereco.complemento}
                                            // onChange={}
                                        /> */}
                                        <input className="perfil-usuario-texto"
                                            id="cidade"
                                            type="text" 
                                            name="cidade"
                                            value={objUser.endereco.complemento == null ? "" : objUser.endereco.complemento}
                                            // onChange={}
                                        />
                                    </div>
                                    <div>
                                        <input className="perfil-usuario-texto"
                                            id="cidade"
                                            type="text" 
                                            name="cidade"
                                            value={objUser.endereco.cidade}
                                            // onChange={}
                                        />
                                        <input className="perfil-usuario-texto"
                                            id="estado"
                                            type="text" 
                                            name="estado"
                                            value={objUser.endereco.uf}
                                            // onChange={}
                                        />
                                    </div>
                                </div>
                            </div>
                            
                            <div className="perfil-usuario-informacoes-pontuacao">
                                <div className="perfil-usuario-informacoes-container">

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