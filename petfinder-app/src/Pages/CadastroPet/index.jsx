import "./cadastro-pet.css";
import React, { useEffect, useState } from "react";
import api from "../../Api"
import HeaderApp from "../../Components/HeaderApp";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import { useNavigate } from "react-router-dom";

let petJson = {
    nome: "",
    dataNasc: "",
    especie: "",
    porte: "Pequeno",
    raca: "",
    sexo: "Masc",
    caminhoImagem: "",
    descricao: "",
    isDoente: false,
    caracteristicas: []
}

function CadastroPet() {

    const [activePage, setActivePage] = useState([false, true, true]);
    const [preferencias, setPreferencias] = useState([]);
    const [valuesCaracteristica, setValuesCaracteristica] = useState([]);

    const [valuesPet, setValuesPet] = useState(petJson)

    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));

    const swal = withReactContent(Swal);
    const navigate = useNavigate();

    function handleChangePet(event) {
        const { value, name } = event.target
        setValuesPet({ ...valuesPet, [name]: value, })
    }

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
        event.preventDefault()
        let json = {
            instituicaoId: objUser.fkInstituicao.id,
            nome: valuesPet.nome,
            doente: valuesPet.isDoente,
            dataNasc: valuesPet.dataNasc+"T14:48:00.000Z",
            especie: valuesPet.especie,
            raca: valuesPet.raca,
            porte: valuesPet.porte,
            sexo: valuesPet.sexo,
            descricao: valuesPet.descricao,
            caminhoImagem: valuesPet.caminhoImagem,
            caracteristicas: valuesCaracteristica
        }
        console.log(json)
        api.post("/pets", json, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                swal.fire({
                    icon: "success",
                    title: <h1>Pet cadastrado com sucesso :)</h1>,
                }).then(() => {
                    navigate(`/lista-pet`)
                })
            }).catch((error) => {
                swal.fire({
                    icon: "error",
                    title: <h1>Ops! Algo deu errado da nossa parte :(</h1>,
                    text: "Por favor, tente novamente!"
                });
            })
    }

    return (
        <>
            <HeaderApp/>
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
                            <input 
                                id="nome" 
                                className="cad-pet-input" 
                                name="nome"
                                value={valuesPet.nome}
                                onChange={handleChangePet}
                                required 
                            />
                        </div>

                        <div className="cad-pet-30-container ">
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="sexo">Sexo:</label>
                                <select id="sexo" className="cad-pet-input" required name="sexo" value={valuesPet.sexo} onChange={handleChangePet}>
                                    <option value="Masc">Masculino</option>
                                    <option value="Fem">Feminino</option>
                                </select>
                            </div>
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="dataNasc">Data Nascimento:</label>
                                <input type="date" id="dataNasc" className="cad-pet-input cad-pet-date" required name="dataNasc" value={valuesPet.dataNasc} onChange={handleChangePet}/>
                            </div>
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="porte">Porte:</label>
                                <select type="" id="porte" className="cad-pet-input" required name="porte" value={valuesPet.porte} onChange={handleChangePet}>
                                    <option value="Pequeno">Pequeno</option>
                                    <option value="Medio">Medio</option>
                                    <option value="Grande">Grande</option>
                                </select>
                            </div>
                        </div>

                        <div className="cad-pet-30-container">
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="raca">Raça:</label>
                                <input type="" id="raca" className="cad-pet-input" required name="raca" value={valuesPet.raca} onChange={handleChangePet}/>
                            </div>
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="especie">Especie:</label>
                                <input type="" id="especie" className="cad-pet-input" required name="especie" value={valuesPet.especie} onChange={handleChangePet}/>
                            </div>
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="doente">Está doente:</label>
                                <select id="doente" className="cad-pet-input" required name="isDoente" value={valuesPet.isDoente} onChange={handleChangePet}>
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
                            <img id="img-preview" className="cad-pet-img cad-pet-hidden" src="" onChange alt=""/>
                            <label className="cad-pet-upload-img" htmlFor="perfilImage">Selecionar Imagem</label>
                            <input type="file" accept="image/*" id="perfilImage" className="cad-pet-hidden" required name="caminhoImagem" value={valuesPet.caminhoImagem}
                                onChange={(event) => {
                                    if (event.target.files.length > 0) {
                                        var src = URL.createObjectURL(event.target.files[0]);
                                        var preview = document.getElementById("img-preview");
                                        const { value, name } = event.target
                                        setValuesPet({ ...valuesPet, [name]: value, })
                                        preview.src = src;
                                        preview.style.display = "block";
                                    }
                                }
                                } />
                        </div>

                        <div className="cad-pet-input-container">
                            <label className="cad-pet-label" htmlFor="descricao">Descrição:</label>
                            <textarea id="descricao" className="cad-pet-input cad-pet-textarea" required name="descricao" value={valuesPet.descricao} onChange={handleChangePet}/>
                        </div>

                        <div className="cad-pet-form-btn-container">
                            <button className="cad-pet-btn" onClick={handleChangePageBase}>Voltar</button>
                            <button className="cad-pet-btn" onClick={handleChangePagePref}>Próximo</button>
                        </div>
                    </div>

                    <div className={activePage[2] ? "cad-pet-hidden" : ""}>
                        <div className="cad-pet-container">
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

                                                    input.checked = !document.getElementById(pref.id).checked

                                                    if (input.checked) {
                                                        btn.classList.replace("cad-pet-btn-preferencia", "cad-pet-btn-preferencia-checked")
                                                        setValuesCaracteristica([...valuesCaracteristica, input.id])
                                                    }
                                                    else {
                                                        btn.classList.replace("cad-pet-btn-preferencia-checked", "cad-pet-btn-preferencia")
                                                        setValuesCaracteristica(valuesCaracteristica.filter((e) => e !== input.id))
                                                    }
                                                }}
                                            >
                                                {pref.caracteristica}
                                            </button>
                                        </>
                                    ))
                                }
                            </div>
                        </div>
                        <div className="cad-pet-form-btn-container">
                            <button className="cad-pet-btn" onClick={handleChangePageDesc}>Voltar</button>
                            <button className="cad-pet-btn" onClick={handleSubmit}>Cadastrar</button>
                        </div>
                    </div>
                </div>
            </div >
        </>
    )
}

export default CadastroPet;