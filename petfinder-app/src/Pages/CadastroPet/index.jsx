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

    useEffect(() => {
        api.get("/pets/caracteristicas").then((res) => {
            try {
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
        console.log("onSubmit")
        api.post(
            "/pets", 
            formData, 
            {
                headers: {
                'Content-Type': 'multipart/form-data'
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

    const [nome, setNome] = useState("");
    const [sexo, setSexo] = useState("");
    const [dataNascimento, setDataNascimento] = useState("");
    const [porte, setPorte] = useState("");
    const [raca, setRaca] = useState("");
    const [especie, setEspecie] = useState("");
    const [isDoente, setIsDoente] = useState("");
    const [file, setFile] = useState({});
    const [descricao, setDescricao] = useState("");
    const [caracteristicas,] = useState([]);
    const [listaCaracteristicas, setListaCaracteristicas] = useState("");
    
    const [formData, setFormData] = useState(new FormData());

    useEffect(() => {
        let char = "";
        valuesCaracteristica.map((id, index) => {
            return char += (index === (valuesCaracteristica.length - 1) || (valuesCaracteristica.length === 1)) ? id : `${id},`;
        });
        setListaCaracteristicas(char);
    }, [valuesCaracteristica]);

    useEffect(() => {
        setFormData(new FormData(document.querySelector("#idForm")));
    }, [
        nome, 
        sexo,
        dataNascimento,
        porte,
        raca,
        especie,
        isDoente,
        file,
        descricao,
        caracteristicas,
        valuesCaracteristica
    ]);

    return (
        <>
            <HeaderApp/>
            <div className="cad-pet-form-container">
                <form id="idForm" className="cad-pet-form-geral" onSubmit={handleSubmit} method="post" encType="multipart/form-data">
                    <input type="text" name="instituicaoId" value={objUser.fkInstituicao.id} readOnly hidden/>
                    <input type="text" name="caracteristicas[]" value={listaCaracteristicas} readOnly hidden/>
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
                                value={nome}
                                onChange={evt => setNome(evt.target.value)}
                                required 
                            />
                        </div>

                        <div className="cad-pet-30-container ">
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="sexo">Sexo:</label>
                                <select id="sexo" className="cad-pet-input" required name="sexo" value={sexo} onChange={evt => setSexo(evt.target.value)}>
                                    <option value="Masculino">Masculino</option>
                                    <option value="Feminino">Feminino</option>
                                </select>
                            </div>
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="dataNasc">Data Nascimento:</label>
                                <input type="date" id="dataNasc" className="cad-pet-input cad-pet-date" required name="dataNasc" value={dataNascimento} onChange={evt => setDataNascimento(evt.target.value)}/>
                            </div>
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="porte">Porte:</label>
                                <select type="" id="porte" className="cad-pet-input" required name="porte" value={porte} onChange={evt => setPorte(evt.target.value)}>
                                    <option value="Pequeno">Pequeno</option>
                                    <option value="Medio">Medio</option>
                                    <option value="Grande">Grande</option>
                                </select>
                            </div>
                        </div>

                        <div className="cad-pet-30-container">
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="raca">Raça:</label>
                                <input type="" id="raca" className="cad-pet-input" required name="raca" value={raca} onChange={evt => setRaca(evt.target.value)}/>
                            </div>
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="especie">Especie:</label>
                                <input type="" id="especie" className="cad-pet-input" required name="especie" value={especie} onChange={evt => setEspecie(evt.target.value)}/>
                            </div>
                            <div className="cad-pet-25 cad-pet-input-container">
                                <label className="cad-pet-label" htmlFor="doente">Está doente:</label>
                                <select id="doente" className="cad-pet-input" required name="doente" value={isDoente} onChange={evt => setIsDoente(evt.target.value)}>
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
                            <input type="file" accept="image/*" id="perfilImage" className="cad-pet-hidden" required name="file"
                                onChange={(event) => {
                                    if (event.target.files.length > 0) {
                                        var src = URL.createObjectURL(event.target.files[0]);
                                        var preview = document.getElementById("img-preview");
                                        const { value, name } = event.target
                                        setValuesPet({ ...valuesPet, [name]: value, })
                                        preview.src = src;
                                        preview.style.display = "block";
                                        setFile({file:event.target.files[0]})
                                    }
                                }
                                } />
                        </div>

                        <div className="cad-pet-input-container">
                            <label className="cad-pet-label" htmlFor="descricao">Descrição:</label>
                            <textarea id="descricao" className="cad-pet-input cad-pet-textarea" required name="descricao" value={descricao} onChange={evt => setDescricao(evt.target.value)}/>
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
                                    preferencias.map((pref) => {
                                        return (
                                            <>
                                                <input
                                                    key={`i${pref.id}`}
                                                    className="cad-pet-hidden"
                                                    value={pref.caracteristica}
                                                    type="checkbox"
                                                    id={pref.id}
                                                />
                                                <button
                                                    type="button"
                                                    className="cad-pet-btn-preferencia"
                                                    id={pref.id + "-btn"}
                                                    key={`b${pref.id}`}
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
                                        )
                                    })
                                }
                            </div>
                        </div>
                        <div className="cad-pet-form-btn-container">
                            <button className="cad-pet-btn" onClick={handleChangePageDesc}>Voltar</button>
                            <input type="submit" className="cad-pet-btn" value="Cadastrar"/>
                        </div>
                    </div>
                </form>
            </div >
        </>
    )
}

export default CadastroPet;