import './perfil-usuario.css';

import HeaderApp from "../../Components/HeaderApp";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
// import EditarIcon from "../../Images/edit-two.svg";
// import MedalhaNoBronze from "./../../Images/pet-friendly-No-bronze.svg";
// import MedalhaNoPrata from "./../../Images/pet-friendly-No-prata.svg";
// import MedalhaNoOuro from "./../../Images/pet-friendly-No-ouro.svg";
import noPet from "../../Images/png_img/gatinhu.png";
import api from "../../Api";
import CardPet from "../../Components/CardPet";

function verificarApadrinhado(pets){
    const navigate = useNavigate()
    if(pets.length > 0){
        return (
            <div className="perfil-usuario-box-lista">
                <span>Pets que ajudei</span>
                <div className="perfil-usuario-box-lista-pet">
                    {
                        pets.map((p, index) => (
                            <CardPet
                                key={index} 
                                id={p.id}
                                nome={p.nome}
                                isDoente={p.isDoente}
                                backgroundImage={p.caminhoImagem}
                                onClick={() =>
                                    navigate(`/perfil-pet-usuario/${p.id}`)
                                }>
                            </CardPet>
                        ))
                    }
                    {
                        pets.map((p, index) => (
                            <CardPet
                                key={index} 
                                id={p.id}
                                nome={p.nome}
                                isDoente={p.isDoente}
                                backgroundImage={p.caminhoImagem}
                                onClick={() =>
                                    navigate(`/perfil-pet-usuario/${p.id}`)
                                }>
                            </CardPet>
                        ))
                    }
                    {
                        pets.map((p, index) => (
                            <CardPet
                                key={index} 
                                id={p.id}
                                nome={p.nome}
                                isDoente={p.isDoente}
                                backgroundImage={p.caminhoImagem}
                                onClick={() =>
                                    navigate(`/perfil-pet-usuario/${p.id}`)
                                }>
                            </CardPet>
                        ))
                    }
                </div>
            </div>
                
            )
    } else {
        return (
            <div className="perfil-usuario-box-Nolista">
                <span>Ainda não ajudou algum pet</span>  
                <img src={noPet} alt="Gato triste" />
                <button onClick={() => navigate("/home-user")}>Ajude Aqui!</button>
            </div>
        )
    }
}  

function PerfilUsuario(){
    
    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));
    const [pets, setPets] = useState([]);

    useEffect(() => {
        api.get(`/pets/apadrinhamentos/usuario/${objUser.id}`).then(res => {
            console.log(res.data);
            if(res.status === 200) {
                setPets(res.data);
            }
        }).catch(error => {console.log(error)})
        
      }, []);

    return(
        <>
            <HeaderApp/>
            <div className="perfil-usuario-root">
                <div className="perfil-usuario-root-container">
                    <div className="perfil-usuario-box-titulo">
                        <span>Meu Perfil</span>
                    </div>
                    <div className="perfil-usuario-box">
                        <form>
                            <div className="perfil-usuario-card">
                                <div className="perfil-usuario-card-container">
                                    
                                </div>
                            </div>
                            
                           <div className="perfil-usuario-card">
                                <div className="perfil-usuario-card-container">
                                    
                                </div>
                           </div>

                            <div className="perfil-usuario-card">
                                <div className="perfil-usuario-card-container">
                                    
                                </div>
                           </div>
                        </form>

                        <div className="perfil-usuario-pets-apadrinhado">
                            <div className="perfil-usuario-pets-apadrinhado-container">
                                {verificarApadrinhado(pets)}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )

}

export default PerfilUsuario;