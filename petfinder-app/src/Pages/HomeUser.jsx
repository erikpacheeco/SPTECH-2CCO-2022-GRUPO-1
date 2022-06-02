import CardPet from "../Components/CardPet";
import Carousel from 'react-multi-carousel';
import 'react-multi-carousel/lib/styles.css';
import HeaderUser from "../Components/HeaderUser"
import NavItem from "../Components/NavItem";
import "../css/home-user.css"
import "../css/style.css"
import { useEffect, useState } from "react";
import api from "../Api"

export default function HomeUser() {

    const [allPets, setAllPets] = useState([]);
    const [sickPets, setSickPets] = useState([]);
    const [cont, setCont] = useState(0);


    const responsive = {
        superLargeDesktop: {
            // the naming can be any, depends on you.
            breakpoint: { max: 4000, min: 3000 },
            items: 8
        },
        desktop: {
            breakpoint: { max: 3000, min: 1024 },
            items: 5
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
        api.get("pets").then((res) => {
            setAllPets(res.data);
        })
        api.get(`/pets/doentes/${8}`).then((res) => {
            setSickPets(res.data)
        })
    })

    return (
        <>
            <HeaderUser itens={[
                <NavItem isSelected={true} label="Página Inicial" />,
                <NavItem label="Meus Prêmios" />,
                <NavItem label="Mensagens" />
            ]}
            />

            <main className="big">
                <div className="container-main">
                    <section className="section-pet">
                        <div className="titulo">
                            <h1>Não pode nos adotar? Nos ajude!</h1>
                            <h3>Ver Mais</h3>
                        </div>
                        <div className="lista-ajuda">
                            <Carousel responsive={responsive}>
                                {
                                    sickPets.map((p) => (
                                        <CardPet nome={p.nome} isDoente={true} backgroundImage={p.caminhoImagem}/>
                                    ))
                                }
                            </Carousel>
                        </div>
                    </section>
                    <section className="section-pet">
                        <div className="titulo">
                            <h1>Adote um desses animaizinhos</h1>
                            <h3>Ver Mais</h3>
                        </div>
                        <div className="lista-adotar">
                            <div className="container-adotar">
                                {
                                    allPets.map((p) => (
                                        <CardPet nome={p.nome} isDoente={p.doente} backgroundImage={p.caminhoImagem}/>
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

