import HeaderBasic from "../Components/HeaderBasic";
import "../css/style.css"
import "../css/form.css"
import "../css/cadastro-usuario.css"
import { useState } from "react";

function initialValues(){
    return
}


function Cadastro() {

    const [values, setValues] = useState(initialValues);

    return (
        <>
            <HeaderBasic />

            <div className="cad-user-container">
                <form className="cad-user-form-container">
                    <div className="form-usuario cad-user-form cad-user-hide">
                        <div className="btn">
                            <button type="button" className="btn-paginas"></button>
                            <button type="button" className="btn-paginas"></button>
                            <button type="button" className="btn-paginas"></button>
                        </div>

                        <h1>CADASTRO DO USUÁRIO</h1>
                        <div className="dados-pessoais">
                            <div className="input-container">
                                <label htmlFor="nome">Nome completo: </label>
                                <input id="nome" type="text" />
                            </div>

                            <div className="input-container">
                                <label htmlFor="">Telefone para contato: </label>
                                <input type="text" />
                            </div>

                            <div className="input-container">
                                <label htmlFor="">E-mail: </label>
                                <input type="email" />
                            </div>

                            <div className="input-container">
                                <label htmlFor="">Senha: </label>
                                <input type="text" />
                            </div>
                        </div>

                        <div className="button-container">
                            <button type="button" className="btn-form" name="btnProximo">Próximo</button>
                        </div>
                    </div>

                    <div className="form-endereco cad-user-form ">
                        <div className="btn">
                            <button type="button" className="btn-paginas"></button>
                            <button type="button" className="btn-paginas"></button>
                            <button type="button" className="btn-paginas"></button>
                        </div>

                        <h1>CADASTRO DO USUÁRIO</h1>
                        <h2>Endereço</h2>
                        <div className="endereco">
                            <div className="input-container">
                                <label html="">CEP: </label>
                                <input type="text" id="inCEP" maxLength={8} pattern="[0-9]+" required />
                            </div>

                            <div className="input-container">
                                <label html="">Rua: </label>
                                <input type="text" id="inRua" required />
                            </div>

                            <div className="num-compl-container">
                                <div className="input-container num">
                                    <label html="">Número: </label>
                                    <input type="text" required />
                                </div>

                                <div className="input-container compl">
                                    <label html="">Complemento: </label>
                                    <input type="text" id="inComplemento" />
                                </div>
                            </div>

                            <div className="input-container">
                                <label html="">Bairro: </label>
                                <input type="text" id="inBairro" required />
                            </div>

                            <div className="cidade-estado-container">
                                <div className="input-container cidade">
                                    <label html="">Cidade: </label>
                                    <input type="text" id="inCidade" required />
                                </div>

                                <div className="input-container estado">
                                    <label html="">Estado: </label>
                                    <input type="text" id="inEstado" required />
                                </div>
                            </div>
                        </div>

                        <div className="button">
                            <div className="button-container">
                                <input type="submit" className="btn-form" name="btnVoltar" value="Voltar" />
                            </div>

                            <div className="button-container">
                                <input type="submit" className="btn-form" name="btnProximo" value="Proximo" />
                            </div>
                        </div>
                    </div>

                    <div className="form-preferencias cad-user-form cad-user-hide" >
                        <div className="btn">
                            <button type="button" className="btn-paginas"></button>
                            <button type="button" className="btn-paginas"></button>
                            <button type="button" className="btn-paginas"></button>
                        </div>

                        <h1>PREFERÊNCIAS</h1>

                        <div className="preferencias-container">
                            <button className="btn-preferencia">Médio</button>
                            <button className="btn-preferencia">Pequeno</button>
                            <button className="btn-preferencia">Grande</button>
                            <button className="btn-preferencia">Dócil</button>
                            <button className="btn-preferencia">SRD</button>
                            <button className="btn-preferencia">Preguiçoso</button>
                            <button className="btn-preferencia">Esfomeado</button>
                            <button className="btn-preferencia">Carinhoso</button>
                            <button className="btn-preferencia">Feliz</button>
                            <button className="btn-preferencia">Animado</button>
                            <button className="btn-preferencia">Brincalhão</button>
                            <button className="btn-preferencia">Amigável</button>
                        </div>

                        <div className="button">
                            <div className="button-container">
                                <input type="submit" className="btn-form" name="btnVoltar" value="Voltar" />
                            </div>

                            <div className="button-container">
                                <input type="submit" className="btn-form" name="btnCadastrar" value="Cadastrar" />
                            </div>
                        </div>
                    </div>

                </form>
            </div>
        </>
    );
}

export default Cadastro;