import "./lista-instituicao.css";
import HeaderApp from "../../Components/HeaderApp";
import React, { useEffect, useState } from "react";
import headerFunctions from "../../functions/headerFunctions";
import SideBarItem from "../../Components/SideBarItem";
import NavItem from "../../Components/NavItem";
import api from "../../Api";
import InstituicaoListItem from "../../Components/InstituicaoListaItem"
function ListaInstituicao() {
  const [allInstituicoes, setAllInstituicoes] = useState([]);

  const objUser = JSON.parse(localStorage.getItem("petfinder_user"));

  useEffect(() => {
    api.get('/instituicoes').then(res => {
        setAllInstituicoes(res.data)
    })
  })

  return (
    <>
      <div className="lista-instituicao-container-geral">
        <HeaderApp
          sideItens={headerFunctions.sideBarNivelAcesso(objUser.nivelAcesso)}
          itens={headerFunctions.headerNivelAcesso(objUser.nivelnivelAcesso)}
        />

        <div className="lista-instituicao">
          <div className="lista-instituicao-container">
            <div className="lista-instituicao-title">
              <h2>Instiuições Cadastrados</h2>
            </div>
            <div id="idCadastroinsituicaoList" className="lista-instituicao-list">
              {
                allInstituicoes.map(i => (
                    <InstituicaoListItem
                     key={i.id} nome={i.nome}
                     qtd={i.id}
                     qtdCol={i.id}
                    />
                ))
              }
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default ListaInstituicao;
