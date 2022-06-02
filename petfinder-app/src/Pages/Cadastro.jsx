import HeaderBasic from "../Components/HeaderBasic";
import "../css/style.css"
import "../css/form.css"
import "../css/cadastro-usuario.css"
import { useEffect, useState } from "react";
import api from "../Api"
import FloatResgate from "../Components/FloatResgate";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import { useNavigate } from "react-router-dom";


function initialValuesUsuario() {
    return {
        nome: "",
        email: "",
        senha: "",
        nivelAcesso: "user",
    }
}

function initialValuesEndereco() {
    return {
        rua: "",
        complemento:  null,
        num: "",
        bairro: "",
        cidade: "",
        uf: "",
        cep: ""
    }

}

function Cadastro() {

    const [valuesUsuario, setValuesUsuario] = useState(initialValuesUsuario)
    const [valuesEndereco, setValuesEndereco] = useState(initialValuesEndereco)
    const [valuesInteresse, setValuesInteresse] = useState([])
    const [preferencias, setPreferencias] = useState([])

    const [formUser, setFormUser] = useState(true);
    const [formEndereco, setFormEndereco] = useState(false)
    const [formPreferencias, setFormPreferencias] = useState(false)

    const swal = withReactContent(Swal);
    const navigate = useNavigate();

    function handleChangeUser(event) {
        const { value, name } = event.target
        setValuesUsuario({ ...valuesUsuario, [name]: value, })
    }

    function handleChangeEndereco(event) {
        const { value, name } = event.target
        setValuesEndereco({ ...valuesEndereco, [name]: value, })
    }

    function handleSubmit(event) {
        event.preventDefault()
        let json = {
            usuario: {
                nome: valuesUsuario.nome,
                email: valuesUsuario.email,
                senha: valuesUsuario.senha,
                nivelAcesso: "user",
                endereco: {
                    rua: valuesEndereco.rua,
                    complemento: valuesEndereco.complemento,
                    num: valuesEndereco.num,
                    bairro: valuesEndereco.bairro,
                    cidade: valuesEndereco.cidade,
                    uf: valuesEndereco.uf,
                    cep: valuesEndereco.cep
                }
            },
            interesses: valuesInteresse
        }
        console.log(json)
        api.post("/usuarios", json, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then((res) => {
            swal.fire({
                icon: "success",
                title: <h1>Você será redirecionado para o login</h1>,
            }).then(()=>{
                navigate("/")
            })
        }).catch((error) => {
            swal.fire({
                icon: "error",
                title: <h1>Ops! Algo deu errado da nossa parte :(</h1>,
                text: "Por favor, tente novamente!"
            });
        })
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


    function handleChangeUserEndereco() {
        setFormEndereco(true);
        setFormUser(false);
    }

    function handleChangeEnderecoUser() {
        setFormEndereco(false);
        setFormUser(true);
    }

    function handleChangeEnderecoPreferencia() {
        setFormEndereco(false);
        setFormPreferencias(true);
    }

    function handleChangePreferenciaEndereco() {
        setFormEndereco(true);
        setFormPreferencias(false);
    }

    return (
        <>
            <HeaderBasic />
            <div className="cad-user-container">
                <form className="cad-user-form-container" onSubmit={handleSubmit}>

                    <div className={formUser ? ("form-usuario cad-user-form") : ("form-usuario cad-user-form cad-user-hide")}>
                        <div className="btn">
                            <button type="button" className="btn-paginas-selected"></button>
                            <button type="button" className="btn-paginas"></button>
                            <button type="button" className="btn-paginas"></button>
                        </div>

                        <h1>CADASTRO DO USUÁRIO</h1>
                        <div className="dados-pessoais">
                            <div className="input-container">
                                <label htmlFor="nome">Nome completo: </label>
                                <input
                                    id="nome"
                                    name="nome"
                                    type="text"
                                    value={valuesUsuario.nome}
                                    required
                                    onChange={handleChangeUser}
                                />
                            </div>

                            <div className="input-container">
                                <label htmlFor="email">E-mail: </label>
                                <input
                                    id="email"
                                    name="email"
                                    type="email"
                                    value={valuesUsuario.email}
                                    required
                                    onChange={handleChangeUser}
                                />
                            </div>

                            <div className="input-container">
                                <label htmlFor="senha">Senha: </label>
                                <input
                                    id="senha"
                                    name="senha"
                                    type="password"
                                    value={valuesUsuario.senha}
                                    required
                                    onChange={handleChangeUser}
                                />
                            </div>
                        </div>

                        <div className="button-container">
                            <button
                                type="button"
                                className="btn-form"
                                name="btnProximo"
                                onClick={handleChangeUserEndereco}
                            >
                                Próximo
                            </button>
                        </div>
                    </div>

                    <div className={formEndereco ? ("form-endereco cad-user-form") : ("form-endereco cad-user-form cad-user-hide")}>
                        <div className="btn">
                            <button type="button" className="btn-paginas"></button>
                            <button type="button" className="btn-paginas-selected"></button>
                            <button type="button" className="btn-paginas"></button>
                        </div>

                        <h1>CADASTRO DO USUÁRIO</h1>
                        <h2>Endereço</h2>
                        <div className="endereco">
                            <div className="input-container">
                                <label html="cep">CEP: </label>
                                <input
                                    id="cep"
                                    name="cep"
                                    type="text"
                                    value={valuesEndereco.cep}
                                    maxLength={8}
                                    minLength={8}
                                    pattern="[0-9]+"
                                    required
                                    onChange={handleChangeEndereco}
                                />
                            </div>

                            <div className="input-container">
                                <label html="rua">Rua: </label>
                                <input
                                    id="rua"
                                    name="rua"
                                    type="text"
                                    value={valuesEndereco.rua}
                                    required
                                    onChange={handleChangeEndereco}
                                />
                            </div>

                            <div className="num-compl-container">
                                <div className="input-container num">
                                    <label html="numero">Número: </label>
                                    <input
                                        id="numero"
                                        name="num"
                                        type="text"
                                        value={valuesEndereco.num}
                                        required
                                        onChange={handleChangeEndereco}
                                    />
                                </div>

                                <div className="input-container compl">
                                    <label html="">Complemento: </label>
                                    <input
                                        id="complemento"
                                        type="text"
                                        name="complemento"
                                        value={valuesEndereco.complemento}
                                        onChange={handleChangeEndereco} />
                                </div>
                            </div>

                            <div className="input-container">
                                <label html="bairro">Bairro: </label>
                                <input
                                    id="bairro"
                                    name="bairro"
                                    type="text"
                                    value={valuesEndereco.bairro}
                                    onChange={handleChangeEndereco}
                                    required
                                />
                            </div>

                            <div className="cidade-estado-container">
                                <div className="input-container cidade">
                                    <label html="cidade">Cidade: </label>
                                    <input
                                        id="cidade"
                                        name="cidade"
                                        type="text"
                                        value={valuesEndereco.cidade}
                                        required
                                        onChange={handleChangeEndereco}
                                    />
                                </div>

                                <div className="input-container estado">
                                    <label html="estado">UF: </label>
                                    <input
                                        id="estado"
                                        name="uf"
                                        minLength={2}
                                        maxLength={2}
                                        type="text"
                                        value={valuesEndereco.uf}
                                        required
                                        onChange={handleChangeEndereco}
                                    />
                                </div>
                            </div>
                        </div>

                        <div className="button">
                            <div className="button-container">
                                <button
                                    type="button"
                                    className="btn-form"
                                    name="btnVoltar"
                                    onClick={handleChangeEnderecoUser}
                                >
                                    Voltar
                                </button>
                            </div>

                            <div className="button-container">
                                <button
                                    type="button"
                                    className="btn-form"
                                    name="btnProximo"
                                    onClick={handleChangeEnderecoPreferencia}
                                >
                                    Proximo
                                </button>
                            </div>
                        </div>
                    </div>

                    <div className={formPreferencias ? ("form-preferencias cad-user-form") : ("form-preferencias cad-user-form cad-user-hide")}>
                        <div className="btn">
                            <button type="button" className="btn-paginas"></button>
                            <button type="button" className="btn-paginas"></button>
                            <button type="button" className="btn-paginas-selected"></button>
                        </div>

                        <h1>PREFERÊNCIAS</h1>

                        <div className="preferencias-container">
                            {
                                preferencias.map((pref) => (
                                    <>
                                        <input
                                            className="cad-user-hide"
                                            value={pref.caracteristicas}
                                            type="checkbox"
                                            id={pref.id}
                                        />
                                        <button
                                            type="button"
                                            className="btn-preferencia"
                                            id={pref.id + "-btn"}
                                            onClick={() => {
                                                let input = document.getElementById(pref.id)
                                                let btn = document.getElementById(pref.id + "-btn")
                                                const { value } = input

                                                input.checked = !document.getElementById(pref.id).checked
                                                
                                                if (input.checked) {
                                                    btn.classList.replace("btn-preferencia", "btn-preferencia-checked")
                                                    setValuesInteresse([...valuesInteresse,{caracteristicas: value }])
                                                }
                                                else {
                                                    btn.classList.replace("btn-preferencia-checked", "btn-preferencia")
                                                    setValuesInteresse( valuesInteresse.filter((e) => e.caracteristicas !== value))
                                                }
                                            }}
                                        >
                                            {pref.caracteristicas}
                                        </button>
                                    </>
                                ))
                            }
                        </div>

                        <div className="button">
                            <div className="button-container">
                                <button
                                    type="button"
                                    className="btn-form"
                                    name="btnVoltar"
                                    onClick={handleChangePreferenciaEndereco}
                                >
                                    Voltar
                                </button>
                            </div>

                            <div className="button-container">
                                <button
                                    type="submit"
                                    className="btn-form"
                                    name="btnCadastro"
                                >
                                    Finalizar
                                </button>
                            </div>
                        </div>
                    </div>

                </form>
            </div>
            <FloatResgate/>
        </>
    );
}

export default Cadastro;