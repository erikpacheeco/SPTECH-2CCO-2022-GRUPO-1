import React, { useState, useEffect } from "react";
import "./meusPremios.css";
import HeaderApp from "../../Components/HeaderApp";
import img from "../../Images/erase.svg";
import VLibras from "@djpfs/react-vlibras";
import api from "../../Api";
import CardPetSimplesPremios from "../../Components/CardPetSimplesPremios";

export default function meusPremios() {
  const infoUsuario = JSON.parse(localStorage.getItem("petfinder_user"));
  const [instituicao, setInstituicao] = useState([]);
  const [allPets, setAllPets] = useState([]);
  const [allPremios, setAllPremios] = useState([]);

  useEffect(() => {
    api.get(`/instituicoes/apadrinhamentos/usuario/${infoUsuario.id}`)
      .then((res) => {
        setInstituicao(res.data);
      });
    api.get(`/pets/apadrinhamentos/usuario/${infoUsuario.id}`).then((res) => {
      setAllPets(res.data);
    });
    api.get(`/demandas/premios/get/${infoUsuario.id}`).then((res) => {
      setAllPremios(res.data);
    });
  }, []);

  return (
    <>
      <HeaderApp />

      <div class="premios-container-geral">
        <h1 className="premios-h1-titulo">Meus Prêmios</h1>
        <div className="premios-container-conteudo">
          <div className="premios-container-filtros">
            <div className="premios-container-filtros-titulo">
              <h2 className="premios-h2-filtros">Filtros</h2>
              <img src={img} alt="meus-premios-icone-de-filtro"></img>
            </div>
            <div className="premios-filtros">
              <h2 className="premios-h2-filtros-titulos">Instituições</h2>

              <div className="premios-container-filtro-backend">
                {instituicao.map((i) => (
                  <p className="ver-mais-p-filtro">{i.nome}</p>
                ))}
              </div>

              <h2 className="premios-h2-filtros-titulos">Pets</h2>
              <div className="premios-container-filtro-backend">
              {allPets.map((p) => (
                <p className="ver-mais-p-filtro">{p.nome}</p>
              ))}
              </div>
            </div>
            
          </div>

          <div className="premios-fotos-container">
            <div className="premios-fotos-container-sub" >
            {allPremios.map(p => (
              <CardPetSimplesPremios srcImg={p.img} />
            ))}
          </div>
          </div>
        </div>
      </div>

      <VLibras forceOnload={true}></VLibras>
    </>
  );
}
