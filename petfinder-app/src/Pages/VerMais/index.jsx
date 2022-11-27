import "./VerMais.css";
import HeaderApp from "../../Components/HeaderApp";
import img from "../../Images/erase.svg";
import VLibras from "@djpfs/react-vlibras";
import api from "../../Api";
import CardPet from "../../Components/CardPet";
import { useNavigate } from "react-router-dom";
import React, { useEffect, useState } from 'react';
import Slider from 'react-slick';
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import { GoChevronLeft, GoChevronRight } from "react-icons/go"
import FilterButton from "../../Components/FilterButton";

export default function VerMais() {
  const [instituicao, setInstituicao] = useState([]);
  const [caracteristicas, setCaracteristicas] = useState([]);

  const navigate = useNavigate();

  const [sexoPets, setAllSexoPets] = useState([]);
  const [portePets, setAllPortePets] = useState([]);
  const [doentePets, setAllDoentePets] = useState([]);
  const [especiePets, setAllEspeciePets] = useState([]);

  const [allPets, setAllPets] = useState([]);
  const [itensPerPage, setItensPerPage] = useState(30);
  const [currentPage, setCurrentPage] = useState(0);

  const pages = Math.ceil(allPets.length / itensPerPage);
  const startIndex = currentPage * itensPerPage;
  const endIndex = startIndex + itensPerPage;
  const currentPets = allPets.slice(startIndex, endIndex);

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

    setFiltersCaracteristicas([])
    setFiltersEspecie([])
  }

  const [filtersSexo, setFiltersSexo] = useState([])
  const [filtersPorte, setFiltersPorte] = useState([])
  const [filtersDoente, setFiltersDoente] = useState([])
  const [filtersEspecie, setFiltersEspecie] = useState([])
  const [filtersCaracteristicas, setFiltersCaracteristicas] = useState([])

  useEffect(() => {
    api.get("/pets/sexo").then((res) => {
      setAllSexoPets(res.data);
    });
    api.get("/pets/porte").then((res) => {
      setAllPortePets(res.data);
    });
    api.get("/pets/doente").then((res) => {
      setAllDoentePets([{key: true, alias: "Sim"},{key: false, alias: "Não"}]);
    });
    api.get("/pets/especie").then((res) => {
      setAllEspeciePets(res.data);
    });
    api.get("/pets/caracteristicas").then((res) => {
      setCaracteristicas(res.data);
    });
  }, []);

  useEffect(() => {
    let json = {
      sexo: filtersSexo.length === 0 ? null : filtersSexo,
      porte: filtersPorte.length === 0 ? null : filtersPorte,
      doente: filtersDoente.length === 0 ? null : filtersDoente,
      especie: filtersEspecie.length === 0 ? null : filtersEspecie,
      caracteristicas: filtersCaracteristicas.length === 0 ? null : filtersCaracteristicas
    }
    if (json.caracteristicas == null && json.especie == null && json.doente == null && json.porte == null && json.sexo == null) {
      api.get(`/pets/userPreferences/${JSON.parse(localStorage.getItem("petfinder_user")).id}/${999}`).then((res) => {
        setAllPets(res.data);
      });
    } else {
      api.post("/pets/filter", json, { headers: { 'Content-Type': 'application/json' } }).then((res) => {
        setAllPets(res.data);
      });

    }

  }, [filtersSexo, filtersPorte, filtersDoente, filtersEspecie, filtersCaracteristicas])

  return (
    <>

      <HeaderApp />
      <div className="ver-mais-container-geral">
        <h1 className="ver-mais-h1-titulo">Todos os Pet´s</h1>
        <div className="ver-mais-container-conteudo">
          <div className="ver-mais-container-filtros">
            <div className="ver-mais-container-filtros-titulo">
              <h2 className="ver-mais-h2-filtros">Filtros</h2>
              <img src={img} alt="ver-mais-icone-de-filtro" onClick={clearAllFilters} />
            </div>
            <div className="ver-mais-filtros">

              <h2 className="ver-mais-h2-filtros-titulos">Doente</h2>
              <div className="ver-mais-container-filtro-backend">
                {doentePets.map((p, index) => (
                  <div className="ver-mais-botao-filtro">
                    <FilterButton id={index} label={p.alias} onChange={(inputState) => {
                      if (inputState) {
                        setFiltersDoente([...filtersDoente, p.key])
                      } else {
                        setFiltersDoente(filtersDoente.filter((e) => e !== p.key))
                      }
                    }} />
                  </div>
                ))}

              </div>

              <h2 className="ver-mais-h2-filtros-titulos">Sexo</h2>
              <div className="ver-mais-container-filtro-backend">
                {sexoPets.map((p, index) => (
                  <div className="ver-mais-botao-filtro">
                    <FilterButton id={index} label={p} onChange={(inputState) => {
                      if (inputState) {
                        setFiltersSexo([...filtersSexo, p])
                      } else {
                        setFiltersSexo(filtersSexo.filter((e) => e !== p))
                      }
                    }} />
                  </div>
                ))}

              </div>

              <h2 className="ver-mais-h2-filtros-titulos">Espécie</h2>
              <div className="ver-mais-container-filtro-backend">
                {especiePets.map((p, index) => (
                  <div className="ver-mais-botao-filtro">
                    <FilterButton id={index} label={p} onChange={(inputState) => {
                      if (inputState) {
                        setFiltersEspecie([...filtersEspecie, p])
                      } else {
                        setFiltersEspecie(filtersEspecie.filter((e) => e !== p))
                      }
                    }} />
                  </div>
                ))}

              </div>

              <h2 className="ver-mais-h2-filtros-titulos">Porte</h2>
              <div className="ver-mais-container-filtro-backend">
                {portePets.map((p, index) => (
                  <div className="ver-mais-botao-filtro">
                    <FilterButton id={index} label={p} onChange={(inputState) => {
                      if (inputState) {
                        setFiltersPorte([...filtersPorte, p])
                      } else {
                        setFiltersPorte(filtersPorte.filter((e) => e !== p))
                      }
                    }} />
                  </div>
                ))}

              </div>

              <h2 className="ver-mais-h2-filtros-titulos">Características</h2>
              <div className="ver-mais-container-filtro-backend">
                {caracteristicas.map((c, index) => (
                  <div className="ver-mais-botao-filtro">
                    <FilterButton id={index} label={c.caracteristica} onChange={(inputState) => {
                      if (inputState) {
                        setFiltersCaracteristicas([...filtersCaracteristicas, c.caracteristica])
                      } else {
                        setFiltersCaracteristicas(filtersCaracteristicas.filter((e) => e !== c.caracteristica))
                      }
                    }} />
                  </div>
                ))}
              </div>
            </div>
          </div>

          <div className="ver-mais-fotos-container">
            <div className="ver-mais-fotos-container-sub">
              {currentPets.map((p) => (
                <CardPet
                  id={p.id}
                  nome={p.nome}
                  isDoente={p.doente}
                  backgroundImage={p.caminhoImagem}
                  onClick={() => navigate(`/perfil-pet-usuario/${p.id}`)}
                />
              ))}
            </div>
            <div className="ver-mais-container-botao-paginacao">
              <Slider {...settings}>
                {
                  Array.from(Array(pages), (allPets, index) => {
                    return <button className="ver-mais-botao-paginacao" value={index} onClick={(e) => setCurrentPage(Number(e.target.value))}>{index + 1}</button>
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

