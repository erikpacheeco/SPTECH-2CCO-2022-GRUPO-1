import './PerfilPetUsuario.css';
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import { useEffect, useState } from "react";
import api from "../../Api"

function PerfilPetUsuario() {

    const [valuesInteresse, setValuesInteresse] = useState([])
    const [preferencias, setPreferencias] = useState([])

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

    return(
        <>
            <HeaderApp itens={[
                <NavItem label="Página Inicial" />,
                <NavItem label="Meus Prêmios" />,
                <NavItem label="Mensagens" />
            ]}/>

            <div className="perfil-pet-usuario">
                <div className="perfil-pet-usuario-container">

                    <div className="perfil-pet-usuario-foto">
                        <img src="https://img.freepik.com/fotos-gratis/o-gato-vermelho-ou-branco-eu-no-estudio-branco_155003-13189.jpg?w=2000" alt="" />
                    </div>

                    <div className="perfil-pet-usuario-container-info">

                        <div className="perfil-pet-usuario-container-info-instituicao">
                            
                            <div className="perfil-pet-usuario-info-instituicao-text">
                                <h1>Delivery de Gatinhos</h1>
                                <h1>12km</h1>
                            </div>
                        
                        </div>

                        <div className="perfil-pet-usuario-info">

                            <div className="perfil-pet-usuario-info-text">

                                <div className="perfil-pet-usuario-info-pet">
                                    <div className="perfil-pet-usuario-info-pet-text">
                                        <div className="perfil-pet-usuario-info-nome">
                                            <h2>Vanessa</h2>
                                        </div>
                                        
                                        <div className="perfil-pet-usuario-info-idade">
                                            <p>Idade: </p>
                                            <span>111</span>
                                        </div>

                                        <div className="perfil-pet-usuario-info-especie">
                                            <p>Especie: </p>
                                            <span>111</span>
                                        </div>

                                        <div className="perfil-pet-usuario-info-porte">
                                            <p>Porte: </p>
                                            <span>111</span>
                                        </div>

                                        <div className="perfil-pet-usuario-info-raca">
                                            <p>Raça: </p>
                                            <span>111</span>
                                        </div>

                                        <div className="perfil-pet-usuario-info-mimos">
                                            <p>Mimos por mês: </p>
                                            <span>111</span>
                                        </div>
                                    </div>
                                </div>

                                <div className="perfil-pet-usuario-info-descricao">
                                    <p>Descrição: </p>
                                    <textarea className="perfil-pet-usuario-descricao-textarea" id="" cols="30" rows="15"></textarea>
                                    
                                </div>

                            </div>
                    
                        </div>
                    
                        <div className="perfil-pet-usuario-info-adocao-container">

                            <div className="perfil-pet-usuario-info-adocao">

                                <div className="perfil-pet-usuario-info-adocao-caracteristica">
                                    <p>Como eu sou/estou: </p>
                                    <div className="perfil-pet-usuario-caracteristica-btn">
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
                                </div>

                                <div className="perfil-pet-usuario-info-adocao-acao-container">
                                    <div className="perfil-pet-usuario-info-adocao-acao">
                                        <p>Quem cuida de mim?</p>
                                        <h2>PETY</h2>
                                    </div>

                                    <div className="perfil-pet-usuario-info-adocao-acao-btn">
                                        <button>Apadrinhar</button>
                                        <button>Doar</button>
                                    </div>
                                </div>

                            </div>    

                            <div className="perfil-pet-usuario-info-adocao-adote">
                                <div className="perfil-pet-usuario-info-adocao-adote-btn">
                                    <button>Me Adote</button>
                                </div>
                            </div>                                                                           
                        
                        </div>
                    </div>    
                </div>
            </div>
            
        </>
    )
}

export default PerfilPetUsuario;