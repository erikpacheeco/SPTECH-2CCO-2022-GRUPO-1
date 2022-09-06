import Carousel from 'react-multi-carousel';
import 'react-multi-carousel/lib/styles.css';
import { useEffect, useState } from "react";
import CardPet from "../../Components/CardPet";
import HeaderApp from "../../Components/HeaderApp"
import NavItem from "../../Components/NavItem";
import SideBarItem from '../../Components/SideBarItem';
import perfil from "../../Images/people.svg"
import home from "../../Images/home.svg"
import message from "../../Images/message.svg"
import premio from "../../Images/picture.svg"
import "./home-user.css"
import api from "../../Api"
import React from "react";
import { useNavigate } from "react-router-dom";


export default function HomeUser() {

    const [allPets, setAllPets] = useState([]);
    const [sickPets, setSickPets] = useState([]);
    const [cont, setCont] = useState(0);
    const navigate = useNavigate()

    const responsive = {
        superLargeDesktop: {
            // the naming can be any, depends on you.
            breakpoint: { max: 4000, min: 3000 },
            items: 8
        },
        desktop: {
            breakpoint: { max: 3000, min: 1024 },
            items: 6
        },
        tablet: {
            breakpoint: { max: 1024, min: 464 },
            items: 2
        },
        mobile: {
            breakpoint: { max: 464, min: 0 },
            items: 1
        }
    };

    useEffect(() => {
        api.get("/pets").then((res) => {
            setAllPets(res.data);
        })
        api.get(`/pets/doentes/${8}`).then((res) => {
            setSickPets(res.data)
        })
    })

    return (
        <>
            <HeaderApp

                sideItens={[
                    <SideBarItem label="Página Inicial" icon={home} navigateTo={"/home-user"}/>,
                    <SideBarItem label="Mensagens" icon={message} navigateTo="/chat-user"/>,
                    <SideBarItem label="Meu Perfil" icon={perfil} navigateTo=""/>,
                    <SideBarItem label="Meus Prêmios" icon={premio} navigateTo="/meus-premios"/>
                ]}

                itens={[
                    <NavItem isSelected={true} label="Página Inicial" navigateTo="/home-user"/>,
                    <NavItem label="Meus Prêmios" navigateTo="/meus-premios"/>,
                    <NavItem label="Mensagens" navigateTo="/chat-user"/>
                ]}
            />

            <main className="home-user-container-main">
                <div className="home-user-container">
                    <section className="home-user-section-pet">
                        <div className="home-user-container-center">
                            <div className="home-user-title">
                                <h1>Não pode nos adotar? Ajude nossos pets doentinhos!</h1>
                                <h3>Ver Mais</h3>
                            </div>
                        </div>
                        <div className="home-user-container-center">
                            <div className="home-user-lista-ajuda">
                                <Carousel responsive={responsive}>
                                    {
                                        sickPets.map((p) => (
                                            <CardPet nome={p.nome} isDoente={false} backgroundImage={p.caminhoImagem} />
                                        ))
                                    }
                                </Carousel>
                            </div>
                        </div>
                    </section>
                    <section className="home-user-section-pet">
                        <div className="home-user-container-center">
                            <div className="home-user-title">
                                <h1>Adote um desses animaizinhos</h1>
                                <h3>Ver Mais</h3>
                            </div>
                        </div>
                        <div className="home-user-lista-adotar">
                            <div className="home-user-container-adotar">
                                {
                                    allPets.map((p) => (
                                        <CardPet
                                            id={p.id}
                                            nome={p.nome}
                                            isDoente={p.doente}
                                            backgroundImage={p.caminhoImagem}
                                            onClick={() =>
                                                navigate(`/perfil-pet-usuario/${p.id}`)
                                            }
                                        />

                                    ))
                                }
                            </div>
                        </div>
                    </section>
                </div>
            </main>
        </>
    );
}

