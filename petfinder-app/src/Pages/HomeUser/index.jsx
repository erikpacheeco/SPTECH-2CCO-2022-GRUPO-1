
import { useEffect, useState } from "react";
import CardPet from "../../Components/CardPet";
import HeaderApp from "../../Components/HeaderApp";
import "./home-user.css"
import api from "../../Api"
import React from "react";
import { useNavigate } from "react-router-dom";
import Slider from 'react-slick';
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import {GoChevronLeft, GoChevronRight} from "react-icons/go"



export default function HomeUser() {

    const settings = {
        slidesToShow: 5,
        slidesToScroll: 1,
        nextArrow: <GoChevronRight color="#7F2AB5"/>,
        prevArrow: <GoChevronLeft color="#7F2AB5"/>,
        infinite: true
    }

    const [allPets, setAllPets] = useState([]);
    const [sickPets, setSickPets] = useState([]);
    const navigate = useNavigate()

    useEffect(() => {
        api.get(`/pets/userPreferences/${JSON.parse(localStorage.getItem("petfinder_user")).id}/${20}`).then((res) => {
            setAllPets(res.data);
        })
        api.get(`/pets/doentes/${8}`).then((res) => {
            setSickPets(res.data)
        })
    })

    return (
        <>
            <HeaderApp />

            <main className="home-user-container-main">
                <div className="home-user-container">
                    <section className="home-user-section-pet">
                        <div className="home-user-container-center">
                            <div className="home-user-title">
                                <h1>Não pode nos adotar? Ajude nossos pets doentinhos!</h1>
                                <h3 onClick={() =>
                                    navigate(`/ver-mais`)
                                }>Ver Mais</h3>
                            </div>
                        </div>
                        <div className="home-user-container-center">
                            <div className="home-user-lista-ajuda">
                                <Slider {...settings}>
                                    {
                                        sickPets.map((p) => (
                                            <CardPet
                                                id={p.id}
                                                nome={p.nome}
                                                isDoente={p.isDoente}
                                                backgroundImage={p.caminhoImagem}
                                                onClick={() =>
                                                    navigate(`/perfil-pet-usuario/${p.id}`)
                                                }
                                            />
                                        ))
                                    }
                                </Slider>
                            </div>
                        </div>
                    </section>
                    <section className="home-user-section-pet">
                        <div className="home-user-container-center">
                            <div className="home-user-title">
                                <h1>Adote um desses animaizinhos</h1>
                                <h3 onClick={() =>
                                    navigate(`/ver-mais`)
                                }>Ver Mais</h3>
                            </div>
                        </div>
                        <div className="home-user-lista-adotar">
                            <div className="home-user-container-adotar">
                                {
                                    allPets.map((p) => (
                                        <CardPet
                                            id={p.id}
                                            nome={p.nome}
                                            isDoente={p.isDoente}
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

