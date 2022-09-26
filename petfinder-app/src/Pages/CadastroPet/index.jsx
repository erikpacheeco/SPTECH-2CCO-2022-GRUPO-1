import "./cadastro-pet.css";
import NavItem from "../../Components/NavItem";
import React, { useEffect, useState } from "react";
import api from "../../Api"
import HeaderApp from "../../Components/HeaderApp";
import headerFunctions from "../../functions/headerFunctions";
import SideBarItem from "../../Components/SideBarItem";


let petJson = {
    nome: "",
    dataNasc: "",
    especie: "",
    porte: "",
    raca: "",
    sexo: "",
    caminhoImagem: "",
    descricao: "",
    doente: "",
    caracteristicas: []
}

function CadastroPet() {

    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));

    const [activePage, setActivePage] = useState([false, true, true]);
    const [preferencias, setPreferencias] = useState([]);
    const [valuesInteresse, setValuesInteresse] = useState([]);

    useEffect(() => {
        api.get("/pets/caracteristicas").then((res) => {
            try {
                console.log(res.data)
                setPreferencias(res.data)
            } catch (error) {
                console.log(error)
            }
        })
    }, [])

    function handleChangePageBase(event) {
        setActivePage([false, true, true])
    }
    function handleChangePageDesc(event) {
        setActivePage([true, false, true])
    }
    function handleChangePagePref(event) {
        setActivePage([true, true, false])
    }

    function handleSubmit(event) {

    }

    return (
        <>
            <HeaderApp
                sideItens={headerFunctions.sideBarNivelAcesso(objUser.nivelAcesso)}
                itens={headerFunctions.headerNivelAcesso(objUser.nivelnivelAcesso)}
            />
            <div className="cad-pet-form-container">
                <div className="cad-pet-form-geral">
                    <div className="cad-pet-form-btn-pg-container">
                        <button type="button" className={activePage[0] ? "cad-pet-btn-pg" : "cad-pet-btn-pg-selected"} onClick={handleChangePageBase} />
                        <button type="button" className={activePage[1] ? "cad-pet-btn-pg" : "cad-pet-btn-pg-selected"} onClick={handleChangePageDesc} />
                        <button type="button" className={activePage[2] ? "cad-pet-btn-pg" : "cad-pet-btn-pg-selected"} onClick={handleChangePagePref} />
                    </div>

                    <div className={activePage[0] ? "cad-pet-hidden" : ""}>
                        <h1 className="cad-pet-title">Quem é o novo Pet?</h1>

                        <div className="cad-pet-input-container">
                            <label className="cad-pet-label" htmlFor="nome">Nome:</label>
                            <input id="nome" className="cad-pet-input" required />
                        </div>

                        <div className="cad-pet-30-container ">
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="sexo">Sexo:</label>
                                <select id="sexo" className="cad-pet-input" required>
                                    <option value="Masc">Masculino</option>
                                    <option value="Fem">Feminino</option>
                                </select>
                            </div>
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="dataNasc">Data Nascimento:</label>
                                <input type="date" id="dataNasc" className="cad-pet-input cad-pet-date" required />
                            </div>
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="porte">Porte:</label>
                                <select type="" id="porte" className="cad-pet-input" required >
                                    <option value="Pequeno">Pequeno</option>
                                    <option value="Medio">Medio</option>
                                    <option value="Grande">Grande</option>
                                </select>
                            </div>
                        </div>

                        <div className="cad-pet-30-container">
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="raca">Raça:</label>
                                <input type="" id="raca" className="cad-pet-input" required />
                            </div>
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="especie">Especie:</label>
                                <input type="" id="especie" className="cad-pet-input" required />
                            </div>
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="doente">Está doente:</label>
                                <select id="doente" className="cad-pet-input" required>
                                    <option value="false">Não</option>
                                    <option value="true">Sim</option>
                                </select>
                            </div>
                        </div>

                        <div className="cad-pet-form-btn-container">
                            <button className="cad-pet-btn" onClick={handleChangePageDesc}>Próximo</button>
                        </div>

                    </div>

                    <div className={activePage[1] ? "cad-pet-hidden" : ""}>
                        <h1 className="cad-pet-title">Nos conte mais sobre ele</h1>

                        <div className="cad-pet-input-container cad-pet-img-container">
                            <img id="img-preview" className="cad-pet-img cad-pet-hidden" src="" onChange />
                            <label className="cad-pet-upload-img" htmlFor="perfilImage">Selecionar Imagem</label>
                            <input type="file" accept="image/*" id="perfilImage" className="cad-pet-hidden" required
                                onChange={(event) => {
                                    if (event.target.files.length > 0) {
                                        var src = URL.createObjectURL(event.target.files[0]);
                                        var preview = document.getElementById("img-preview");
                                        preview.src = src;
                                        preview.style.display = "block";
                                    }
                                }
                                } />
                        </div>

                        <div className="cad-pet-input-container">
                            <label className="cad-pet-label" htmlFor="descricao">Descrição:</label>
                            <textarea id="descricao" className="cad-pet-input cad-pet-textarea" required />
                        </div>

                        <div className="cad-pet-form-btn-container">
                            <button className="cad-pet-btn" onClick={handleChangePageBase}>Voltar</button>
                            <button className="cad-pet-btn" onClick={handleChangePagePref}>Próximo</button>
                        </div>
                    </div>

                    <div className={activePage[2] ? "cad-pet-hidden" : ""}>
                        <h1 className="cad-pet-title">O que melhor define o novo Pet?</h1>
                        <div className="cad-pet-btn-preferencia-container">
                            {
                                preferencias.map((pref) => (
                                    <>
                                        <input
                                            className="cad-pet-hidden"
                                            value={pref.caracteristica}
                                            type="checkbox"
                                            id={pref.id}
                                        />
                                        <button
                                            type="button"
                                            className="cad-pet-btn-preferencia"
                                            id={pref.id + "-btn"}
                                            onClick={() => {
                                                let input = document.getElementById(pref.id)
                                                let btn = document.getElementById(pref.id + "-btn")
                                                const { value } = input

                                                input.checked = !document.getElementById(pref.id).checked

                                                if (input.checked) {
                                                    btn.classList.replace("cad-pet-btn-preferencia", "cad-pet-btn-preferencia-checked")
                                                    setValuesInteresse([...valuesInteresse, { caracteristicas: value }])
                                                }
                                                else {
                                                    btn.classList.replace("cad-pet-btn-preferencia-checked", "cad-pet-btn-preferencia")
                                                    setValuesInteresse(valuesInteresse.filter((e) => e.caracteristicas !== value))
                                                }
                                            }}
                                        >
                                            {pref.caracteristica}
                                        </button>
                                    </>
                                ))
                            }
                        </div>
                        <div className="cad-pet-form-btn-container">
                            <button className="cad-pet-btn" onClick={handleChangePageDesc}>Voltar</button>
                            <button className="cad-pet-btn" onClick={handleSubmit}>Próximo</button>
                        </div>
                    </div>
                </div>
            </div >
        </>
    )
}

export default CadastroPet;