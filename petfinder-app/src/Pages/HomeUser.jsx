import CardPet from "../Components/CardPet";
import Carrossel from "../Components/Carrossel";
import HeaderUser from "../Components/HeaderUser"
import NavItem from "../Components/NavItem";
import "../css/home-user.css"
import "../css/style.css"

export default function HomeUser() {
    return (
        <>
            <HeaderUser itens={[
                <NavItem isSelected={true} label="Página Inicial" />,
                <NavItem label="Meus Prêmios" />,
                <NavItem label="Mensagens" />
            ]}
            />

            <main>
                <div class="container-main">
                    <section className="section-pet">
                        <div class="titulo">
                            <h1>Não pode nos adotar? Nos ajude!</h1>
                            <h3>Ver Mais</h3>
                        </div>
                        <div class="lista-ajuda">
                            <Carrossel show={4} infinteLoop>
                                <CardPet/>
                                <CardPet/>
                                <CardPet/>
                                <CardPet/>
                                <CardPet/>
                                <CardPet/>
                                <CardPet/>
                                <CardPet/>
                                <CardPet/>
                            </Carrossel>
                        </div>
                    </section>
                    <section class="section-pet">
                        <div class="lista-ajuda">
                            <section class="section-pet">
                                <div class="titulo">
                                    <h1>Adote um desses animaizinhos</h1>
                                    <h3>Ver Mais</h3>
                                </div>
                                <div class="lista-adotar">
                                    <div class="container-adotar">
                                        <figure class="img-pet">
                                            <p>Gato de Botas</p>
                                            <img src="./img/saude 2.png" alt="" />
                                            <img src="./img/download.jpg" alt="" />
                                        </figure>
                                    </div>
                                </div>
                            </section>
                        </div>
                    </section>
                </div>
            </main>
        </>
    );
}

