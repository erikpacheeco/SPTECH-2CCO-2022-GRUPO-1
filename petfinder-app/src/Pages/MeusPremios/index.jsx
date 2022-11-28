import React, { useState, useEffect } from "react";
import "./meusPremios.css";
import HeaderApp from "../../Components/HeaderApp";
import img from "../../Images/erase.svg";
import VLibras from "@djpfs/react-vlibras";
import api from "../../Api";
import CardPetSimplesPremios from "../../Components/CardPetSimplesPremios";
import { GoChevronLeft, GoChevronRight } from "react-icons/go"
import Slider from 'react-slick';
import FilterButton from "../../Components/FilterButton";

export default function MeusPremios() {
  const infoUsuario = JSON.parse(localStorage.getItem("petfinder_user"));
  const [allPremios, setAllPremios] = useState([]);
  const [allPetsName, setPetsName] = useState([]);
  const [filteredPets, setFilteredPets] = useState([]);
  const [usingFilters, setUsingFilters] = useState(false)

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

  function clearAllFilters() {
    let allFiltersSelected = document.querySelectorAll(".btn-filtro-input")
    for (let i = 0; i < allFiltersSelected.length; i++) {
      const element = allFiltersSelected[i];
      element.checked = false;
      let button = document.getElementById("btn-" + element.id)
      let img = document.getElementById("img-" + element.id)
      button.classList.remove("btn-filtro-checkbox-active")
      button.classList.add("btn-filtro-checkbox");
      img.classList.add("btn-filtro-hide")
    }

    setFilteredPets([])
  }

  useEffect(() => {
    api.get(`/demandas/premios/get/${infoUsuario.id}`).then((res) => {
      res.data.forEach(element => {
        if (!allPetsName.includes(element.pet)) {
          setPetsName([...allPetsName, element.pet])
        }
      });
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
              <img src={img} alt="meus-premios-icone-de-filtro" onClick={clearAllFilters} />
            </div>
            <div className="premios-filtros">
              <h2 className="premios-h2-filtros-titulos">Pets</h2>
              <div className="premios-container-filtro-backend">
                {
                  allPetsName.map((p, index) => {
                    return (<FilterButton id={index} label={p} onChange={(inputState) => {
                      if (inputState) {
                        setFilteredPets([...filteredPets, p])
                      } else {
                        setFilteredPets(filteredPets.filter((e) => e !== p))
                      }
                    }} />)
                  })
                }
              </div>
            </div>
          </div>

          <div className="premios-fotos-container">
            <div className="premios-fotos-container-sub" >
              {
                filteredPets.length == 0 ?
                  currentPets.map(p => (
                    <CardPetSimplesPremios srcImg={p.img} />
                  ))
                  :
                  currentPets.filter((e) => filteredPets.includes(e.pet))
                  .map(p => (<CardPetSimplesPremios srcImg={p.img} />))

              }
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
