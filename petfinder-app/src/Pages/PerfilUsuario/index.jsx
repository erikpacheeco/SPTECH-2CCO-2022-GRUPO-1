import './perfil-usuario.css';

import HeaderApp from "../../Components/HeaderApp";
import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import EditarIcon from "../../Images/edit-two.svg";
import MedalhaNoBronzeIcon from "./../../Images/pet-friendly-No-bronze.svg";
import MedalhaNoPrataIcon from "./../../Images/pet-friendly-No-prata.svg";
import MedalhaNoOuroIcon from "./../../Images/pet-friendly-No-ouro.svg";
import MedalhaBronzeIcon from "./../../Images/pet-friendly-bronze.svg";
import MedalhaPrataIcon from "./../../Images/pet-friendly-prata.svg";
import MedalhaOuroIcon from "./../../Images/pet-friendly-ouro.svg";
import noPet from "../../Images/png_img/gatinhu.png";
import api from "../../Api";
import CardPet from "../../Components/CardPet";
import Swal from 'sweetalert2';
import withReactContent from "sweetalert2-react-content";

// function handleSubmitColaborador(event) {
//     const swal = withReactContent(Swal);
//     event.preventDefault()
//     let json = {
//         id: infoColaborador.id,
//         nome: values.nome,
//         email: infoColaborador.email,
//         nivelAcesso: values.cargo,
//         endereco: {
//             id: infoColaborador.endereco.id,
//             rua: infoColaborador.endereco.rua,
//             num: infoColaborador.endereco.num,
//             complemento: infoColaborador.endereco.complemento,
//             bairro: infoColaborador.endereco.bairro,
//             cidade: infoColaborador.endereco.cidade,
//             uf: infoColaborador.endereco.uf,
//             cep: infoColaborador.endereco.cep,
//             latitude: infoColaborador.endereco.latitude,
//             longitude: infoColaborador.endereco.longitude
//         },
//         fkInstituicao: {
//             id: infoColaborador.fkInstituicao.id,
//             nome: infoColaborador.fkInstituicao.nome,
//             telefone: infoColaborador.fkInstituicao.telefone,
//             termoAdocao: infoColaborador.fkInstituicao.termoAdocao,
//             endereco: {
//               id: infoColaborador.fkInstituicao.endereco.id,
//               rua: infoColaborador.fkInstituicao.endereco.rua,
//               num: infoColaborador.fkInstituicao.endereco.num,
//               complemento: infoColaborador.fkInstituicao.endereco.complemento,
//               bairro: infoColaborador.fkInstituicao.endereco.bairro,
//               cidade: infoColaborador.fkInstituicao.endereco.cidade,
//               uf: infoColaborador.fkInstituicao.endereco.uf,
//               cep: infoColaborador.fkInstituicao.endereco.cep,
//               latitude: infoColaborador.fkInstituicao.endereco.latitude,
//               longitude: infoColaborador.fkInstituicao.endereco.longitude
//             }
//           },
//         logado: infoColaborador.logado
//     }
//     api.put(`/usuarios/${infoColaborador.id}`, json, {
//         headers: {
//             'Content-Type': 'application/json'
//         }
//     })
//         .then((res) => {
//             swal.fire({
//                 icon: "success",
//                 title: <h2>Usuário atualizado com sucesso!</h2>,
//             }).then(() => {
//                 navigate(`/lista-colaborador/${infoColaborador.id}`)
//             })
//         }).catch((error) => {
//             swal.fire({
//                 icon: "error",
//                 title: <h2>Ops! Algo deu errado da nossa parte :(</h2>,
//                 text: "Por favor, tente novamente!"
//             });
//             console.log(error)
//         })
// }

function verificarApadrinhado(pets, objUser){
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
                </div>
            </div>
            )
    } else {
        return (
            <div className="perfil-usuario-box-Nolista">
                <span>Ainda não ajudou algum pet</span>  
                <img src={noPet} alt="Gato triste" />
                {verificarUsuarioAjuda(objUser)}
            </div>
        )
    }
}  

function verificarUsuarioAjuda(objUser){
    const navigate = useNavigate()
    if(objUser.nivelAcesso === "user"){
        return (
            <button onClick={() => navigate("/home-user")}>Ajude Aqui!</button>
        )
    }
}


function verificarUsuarioEditar(objUser){
    if(objUser.nivelAcesso === "user"){
        return (
            // <button onClick={handleSubmitColaborador}>
            <button>
                <span>Editar</span>
                <img src={EditarIcon} alt="" />
            </button> 
        )
    }
}



function PerfilUsuario(){
    
    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));
    const [usuario, setUsuario] = useState();
    const [pets, setPets] = useState([]);
    
    const [infoPadrinho, setInfoPadrinho] = useState([]);

    useEffect(() => {
        if(objUser.nivelAcesso === "user"){
            console.log("TESTETETSTE");
            setUsuario(objUser);
            console.log(objUser);
            console.log(usuario);
            
        }else{
            const idUsuario = useParams();
            api.get(`/usuario/${idUsuario}`).then(res => {
                console.log(res.data);
                if(res.status === 200) {
                    setUsuario(res.data);
                }
            }).catch(error => {console.log(error)})
        }
    }, []);

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
                        <span>{objUser.nivelAcesso === "user" ? "Meu Perfil" : "Perfil do Padrinho"}</span>
                    </div>
                    <div className="perfil-usuario-box">
                        <form>
                            <div className="perfil-usuario-card">
                                <div className="perfil-usuario-card-container">
                                    <div className="perfil-usuario-div-editar">
                                        <span className="perfil-usuario-card-titulo">Informação Pessoal</span>  
                                        {verificarUsuarioEditar(objUser)}
                                    </div>
                                    <input
                                        id="nome"
                                        type="text"
                                        name="nome"
                                        value={objUser.nome}
                                        // onChange={handleChange}
                                        className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input70" 
                                    />
                                    <input
                                        id="email"
                                        type="text"
                                        name="email"
                                        value={objUser.email}
                                        // onChange={handleChange}
                                        className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input70" 
                                    />
                                </div>
                            </div>
                            
                           <div className="perfil-usuario-card">
                                <div className="perfil-usuario-card-container">
                                    <span className="perfil-usuario-card-titulo">Endereço</span>
                                    <div className="perfil-usuario-div-rua">
                                        <input
                                            id="rua"
                                            type="text"
                                            name="rua"
                                            value={objUser.endereco.rua}
                                            // onChange={handleChange}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input50" 
                                        />
                                        <input
                                            id="num"
                                            type="text"
                                            name="num"
                                            value={objUser.endereco.num}
                                            // onChange={handleChange}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input15" 
                                        />
                                        <input
                                            id="cep"
                                            type="text"
                                            name="cep"
                                            value={objUser.endereco.cep}
                                            // onChange={handleChange}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input15" 
                                        />
                                    </div>
                                    <div>
                                        <input
                                            id="bairro"
                                            type="text"
                                            name="bairro"
                                            value={objUser.endereco.bairro}
                                            // onChange={handleChange}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input35" 
                                        />
                                        <input
                                            id="cidade"
                                            type="text"
                                            name="cidade"
                                            value={objUser.endereco.cidade}
                                            // onChange={handleChange}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input35" 
                                        />
                                        <input
                                            id="uf"
                                            type="text"
                                            name="uf"
                                            value={objUser.endereco.uf}
                                            // onChange={handleChange}
                                            className="perfil-usuario-card-texto perfil-usuario-input perfil-usuario-input15" 
                                        />
                                    </div>
                                </div>
                           </div>

                            <div className="perfil-usuario-card">
                                <div className="perfil-usuario-card-container-pontuacao">
                                    <div className="perfil-usuario-box-dash">
                                        <span className="perfil-usuario-card-titulo">Pontuação</span>
                                    </div>
                                    <div className="perfil-usuario-box-pontuacao">
                                        <span>Total:</span>
                                        <span>Próxima Medalha: </span>
                                        <div>
                                            <img src="" alt="" />
                                        </div>
                                    </div>
                                </div>
                           </div>
                        </form>

                        <div className="perfil-usuario-pets-apadrinhado">
                            <div className="perfil-usuario-pets-apadrinhado-container">
                                {verificarApadrinhado(pets, objUser)}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )

}

export default PerfilUsuario;