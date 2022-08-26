import './PerfilPetInstituicao.css';
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";

function PerfilPetInstituicao() {
    return(
        <>
            <HeaderApp itens={[
                <NavItem label="Pets" />,
                <NavItem label="Demandas" />
            ]}/>

            <div className="perfil-pet-instituicao">
                <div className="perfil-pet-instituicao-container">

                    <div className="perfil-pet-instituicao-foto">
                        <h1>Antônio</h1>
                        <img src="https://img.freepik.com/fotos-gratis/o-gato-vermelho-ou-branco-eu-no-estudio-branco_155003-13189.jpg?w=2000" alt="" />
                    </div>

                    <div className="perfil-pet-instituicao-informacao-container">

                        <div className="perfil-pet-instituicao-informacao">
                            <div className="perfil-pet-instituicao-info-pet-container">
                                <div className="perfil-pet-instituicao-info-pet">
                                    <div className="perfil-pet-instituicao-info">
                                        <div className="perfil-pet-instituicao-idade">
                                            <p>Idade: </p>
                                            <input type="text" />
                                        </div>
                                        <div className="perfil-pet-instituicao-especie">
                                            <p>Espécie: </p>
                                            <input type="text" />
                                        </div>
                                    </div>
                                    <div className="perfil-pet-instituicao-info">
                                        <div className="perfil-pet-instituicao-peso">
                                            <p>Peso: </p>
                                            <input type="text" />
                                        </div>
                                        <div className="perfil-pet-instituicao-raca">
                                            <p>Raça: </p>
                                            <input type="text" />
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="perfil-pet-instituicao-descricao-pet-container">
                                <div className="perfil-pet-instituicao-descricao-pet">
                                    <p>Descrição: </p>
                                    <textarea id="" cols="67" rows="8"></textarea>
                                </div>
                            </div>
                        
                            <div className="perfil-pet-instituicao-caracteristica-pet-container"> 
                                <div className="perfil-pet-instituicao-caracteristica-pet">
                                    <p>Como eu sou/estou: </p>
                                    <div className="perfil-pet-instituicao-caracteristica">
                                        <p>Preguiçoso(a)</p>
                                        <p>Pequeno(a)</p>
                                        <p>Curioso(a)</p>
                                        <p>Gordo(a)</p>
                                        <p>Brincalhão(a)</p>
                                        <p>Doente</p>
                                        <p>Preguiçoso(a)</p>
                                        <p>Pequeno(a)</p>
                                        <p>Curioso(a)</p>
                                        <p>Gordo(a)</p>
                                        <p>Brincalhão(a)</p>
                                        <p>Doente</p>
                                    </div>
                                </div>                                                                        
                            </div>
                        </div>
                    </div>    
                </div>
                <div className="perfil-pet-instituicao-btn">
                    <button>Adicionar Mimos</button>
                    <button>Editar</button>
                </div>
            </div>
            
        </>
    )
}

export default PerfilPetInstituicao;