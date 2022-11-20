import React, { useState, useEffect } from "react";
import "./meusPremios.css";
import HeaderApp from "../../Components/HeaderApp";
import img from "../../Images/erase.svg";
import VLibras from "@djpfs/react-vlibras";
import api from "../../Api";
import CardPetSimplesPremios from "../../Components/CardPetSimplesPremios";
import { GoChevronLeft, GoChevronRight } from "react-icons/go"
import Slider from 'react-slick';

export default function meusPremios() {
  const infoUsuario = JSON.parse(localStorage.getItem("petfinder_user"));
  const [instituicao, setInstituicao] = useState([]);
  const [allPets, setAllPets] = useState([]);
  const [allPremios, setAllPremios] = useState([]);

  const [itensPerPage, setItensPerPage] = useState(30);
  const [currentPage, setCurrentPage] = useState(0);

  const pages = Math.ceil(allPremios.length / itensPerPage);
  const startIndex = currentPage * itensPerPage;
  const endIndex = startIndex + itensPerPage;
  const currentPets = allPremios.slice(startIndex, endIndex);

  const settings = {
    slidesToShow: 5,
    slidesToScroll: 1,
    nextArrow: <GoChevronRight color="#7F2AB5" />,
    prevArrow: <GoChevronLeft color="#7F2AB5" />,
    infinite: false
  }

  useEffect(() => {
    api.get(`/instituicoes/apadrinhamentos/usuario/${infoUsuario.id}`)
      .then((res) => {
        setInstituicao(res.data);
      });
    api.get(`/pets/apadrinhamentos/usuario/${infoUsuario.id}`).then((res) => {
      if(res.status === 200) setAllPets(res.data);
    });
    api.get(`/demandas/premios/get/${infoUsuario.id}`).then((res) => {
      setAllPremios(res.data);
    });
  }, []);

  return (
    <>
      <HeaderApp/>

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
              {currentPets.map(p => (
                <CardPetSimplesPremios srcImg={p.img} />
              ))}
            </div>
            <div className="premios-container-botao-paginacao">
              <Slider {...settings}>
                {
                  Array.from(Array(pages), (allPremios, index) => {
                    return <button className="premios-botao-paginacao" value={index} onClick={(e) => setCurrentPage(Number(e.target.value))}>{index + 1}</button>
                  })
                }
              </Slider>
            </div>
          </div>
        </div>
      </div>

      <VLibras forceOnload={true}></VLibras>
    </>
  );
}
