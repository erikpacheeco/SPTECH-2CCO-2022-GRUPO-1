import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import FloatResgate from "../../Components/FloatResgate";
import HeaderBasic from "../../Components/HeaderBasic"
import "./cadastro-usuario.css"
import api from "../../Api"


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
        complemento: null,
        num: "",
        bairro: "",
        cidade: "",
        uf: "",
        cep: ""
    }

}

function CadastroUsuario() {

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
                }).then(() => {
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
            <div className="cadastro-usuario-container">
                <form className="cadastro-usuario-form-container" onSubmit={handleSubmit}>

                    <div className={formUser ? ("cadastro-usuario-form") : ("cadastro-usuario-form cadastro-usuario-hide")}>
                        <div className="cadastro-usuario-btn">
                            <button type="button" className="cadastro-usuario-btn-paginas-selected"></button>
                            <button type="button" className="cadastro-usuario-btn-paginas"></button>
                            <button type="button" className="cadastro-usuario-btn-paginas"></button>
                        </div>

                        <h1 className="cadastro-usuario-title">COMO SE CHAMA?</h1>
                        <div className="cadastro-usuario-input-container">
                            <label htmlFor="nome">Nome completo: </label>
                            <input
                                id="nome"
                                name="nome"
                                type="text"
                                value={valuesUsuario.nome}
                                onChange={handleChangeUser}
                                required
                            />
                        </div>

                        <div className="cadastro-usuario-input-container">
                            <label htmlFor="email">E-mail: </label>
                            <input
                                id="email"
                                name="email"
                                type="email"
                                value={valuesUsuario.email}
                                onChange={handleChangeUser}
                                required
                            />
                        </div>

                        <div className="cadastro-usuario-input-container">
                            <label htmlFor="senha">Senha: </label>
                            <input
                                id="senha"
                                name="senha"
                                type="password"
                                value={valuesUsuario.senha}
                                onChange={handleChangeUser}
                                required
                            />
                        </div>

                        <div className="cadastro-usuario-button-container">
                            <button
                                type="button"
                                className="cadastro-usuario-btn-form"
                                name="btnProximo"
                                onClick={handleChangeUserEndereco}
                            >
                                Próximo
                            </button>
                        </div>
                    </div>

                    <div className={formEndereco ? ("cadastro-usuario-form") : ("cadastro-usuario-form cadastro-usuario-hide")}>
                        <div className="cadastro-usuario-btn">
                            <button type="button" className="cadastro-usuario-btn-paginas"></button>
                            <button type="button" className="cadastro-usuario-btn-paginas-selected"></button>
                            <button type="button" className="cadastro-usuario-btn-paginas"></button>
                        </div>

                        <h1 className="cadastro-usuario-title">ONDE MORA?</h1>
                        <div className="cadastro-usuario-input-container">
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

                        <div className="cadastro-usuario-input-container">
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

                        <div className="cadastro-usuario-separate-container">
                            <div className="cadastro-usuario-input-container cadastro-usuario-20">
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

                            <div className="cadastro-usuario-input-container cadastro-usuario-70">
                                <label html="">Complemento: </label>
                                <input
                                    id="complemento"
                                    type="text"
                                    name="complemento"
                                    value={valuesEndereco.complemento}
                                    onChange={handleChangeEndereco} />
                            </div>
                        </div>

                        <div className="cadastro-usuario-input-container">
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

                        <div className="cadastro-usuario-separate-container">
                            <div className="cadastro-usuario-input-container cadastro-usuario-70">
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

                            <div className="cadastro-usuario-input-container cadastro-usuario-20">
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

                        <div className="cadastro-usuario-button-container">
                            <button
                                type="button"
                                className="cadastro-usuario-btn-form"
                                name="btnVoltar"
                                onClick={handleChangeEnderecoUser}
                            >
                                Voltar
                            </button>


                            <button
                                type="button"
                                className="cadastro-usuario-btn-form"
                                name="btnProximo"
                                onClick={handleChangeEnderecoPreferencia}
                            >
                                Proximo
                            </button>

                        </div>
                    </div>

                    <div className={formPreferencias ? ("cadastro-usuario-form") : ("cadastro-usuario-form cadastro-usuario-hide")}>
                        <div className="cadastro-usuario-btn">
                            <button type="button" className="cadastro-usuario-btn-paginas"></button>
                            <button type="button" className="cadastro-usuario-btn-paginas"></button>
                            <button type="button" className="cadastro-usuario-btn-paginas-selected"></button>
                        </div>

                        <h1 className="cadastro-usuario-title">O QUE BUSCA NO SEU PET IDEAL?</h1>
                        <div className="cadastro-usuario-btn-preferencia-container">
                            {
                                preferencias.map((pref) => (
                                    <>
                                        <input
                                            className="cadastro-usuario-hide"
                                            value={pref.caracteristica}
                                            type="checkbox"
                                            id={pref.id}
                                        />
                                        <button
                                            type="button"
                                            className="cadastro-usuario-btn-preferencia"
                                            id={pref.id + "-btn"}
                                            onClick={() => {
                                                let input = document.getElementById(pref.id)
                                                let btn = document.getElementById(pref.id + "-btn")
                                                const { value } = input

                                                input.checked = !document.getElementById(pref.id).checked

                                                if (input.checked) {
                                                    btn.classList.replace("cadastro-usuario-btn-preferencia", "cadastro-usuario-btn-preferencia-checked")
                                                    setValuesInteresse([...valuesInteresse, { caracteristicas: value }])
                                                }
                                                else {
                                                    btn.classList.replace("cadastro-usuario-btn-preferencia-checked", "cadastro-usuario-btn-preferencia")
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

                        <div className="cadastro-usuario-button-container">

                            <button
                                type="button"
                                className="cadastro-usuario-btn-form"
                                name="btnVoltar"
                                onClick={handleChangePreferenciaEndereco}
                            >
                                Voltar
                            </button>

                            <button
                                type="submit"
                                className="cadastro-usuario-btn-form"
                                name="btnCadastro"
                            >
                                Finalizar
                            </button>
                        </div>
                    </div>

                </form>
            </div>
            <FloatResgate />
        </>
    );
}

export default CadastroUsuario;