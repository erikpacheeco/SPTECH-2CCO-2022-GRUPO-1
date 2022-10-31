import "./VerMais.css";
import HeaderApp from "../../Components/HeaderApp";
import img from "../../Images/erase.svg";
import VLibras from "@djpfs/react-vlibras";
import api from "../../Api";
import CardPet from "../../Components/CardPet";
import { useNavigate } from "react-router-dom";
import React, { useEffect, useState } from 'react';

export default function VerMais() {
  const [instituicao, setInstituicao] = useState([]);
  const [caracteristicas, setCaracteristicas] = useState([]);

  const navigate = useNavigate();

  const [distinctPets, setAllDistinctPets] = useState([]);

  const [allPets, setAllPets] = useState([]);
  // const [sickPets, setSickPets] = useState([]);

  useEffect(() => {
    api.get("/instituicoes").then((res) => {
      setInstituicao(res.data);
    });
    api.get("/pets/caracteristicas").then((res) => {
      setCaracteristicas(res.data);
    });
    api.get("/pets").then((res) => {
      setAllPets(res.data);
    });
    // api.get(`/pets/doentes/${8}`).then((res) => {
    //   setSickPets(res.data);
    // });
    api.get("/pets/distinct").then((res) => {
      setAllDistinctPets(res.data);
    });
  }, []);

  return (
    <>
      <HeaderApp/>
      <div class="ver-mais-container-geral">
        <h1 className="ver-mais-h1-titulo">Todos os Pet´s</h1>
        <div className="ver-mais-container-conteudo">
          <div className="ver-mais-container-filtros">
            <div className="ver-mais-container-filtros-titulo">
              <h2 className="ver-mais-h2-filtros">Filtros</h2>
              <img src={img} alt="ver-mais-icone-de-filtro"></img>
            </div>
            <div className="ver-mais-filtros">
              <h2 className="ver-mais-h2-filtros-titulos">Instituições</h2>

              <div className="ver-mais-container-filtro-backend">
                {instituicao.map((i) => (
                  <p className="ver-mais-p-filtro">{i.nome}</p>
                ))}
              </div>

              <h2 className="ver-mais-h2-filtros-titulos">Pets</h2>
              <div className="ver-mais-container-filtro-backend">
                {distinctPets.map((p) => (
                  <p className="ver-mais-p-filtro">{p}</p>
                ))}
              </div>

              <h2 className="ver-mais-h2-filtros-titulos">Características</h2>
              <div className="ver-mais-container-filtro-backend">
                {caracteristicas.map((c) => (
                  <p className="ver-mais-p-filtro">{c.caracteristica}</p>
                ))}
              </div>
            </div>
            

          </div>

          <div className="ver-mais-fotos-container">
            <div className="ver-mais-fotos-container-sub">
              {allPets.map((p) => (
                <CardPet
                  id={p.id}
                  nome={p.nome}
                  isDoente={p.doente}
                  backgroundImage={p.caminhoImagem}
                  onClick={() => navigate(`/perfil-pet-usuario/${p.id}`)}
                />
              ))}
            </div>
          </div>
        </div>
      </div>
      <VLibras forceOnload={true}></VLibras>
    </>
  );
}
