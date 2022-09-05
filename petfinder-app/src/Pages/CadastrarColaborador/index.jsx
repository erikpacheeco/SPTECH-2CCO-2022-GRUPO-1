import React, { useState } from "react";
import "./style.css";
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import VLibras from "@djpfs/react-vlibras"

export default function CadastrarColaborador() {
  const [nome, setNome] = useState("");
  const [cargo, setCargo] = useState("---");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  return (
    <>
      <HeaderApp
        itens={[
          <NavItem label="Dashboard" />,
          <NavItem label="Padrinhos" />,
          <NavItem label="Demandas" />,
        ]}
      />

      <div class="container">
        <form action="" class="form-container-cad">
          <h1 className="title">CADASTRAR COLABORADOR</h1>
          <div className="container-label">
            <label>Nome completo: </label>
            <input
              type="text"
              name="nome_completo"
              value={nome}
              class="input-text"
              onChange={(e) => setNome(e.target.value)}
            />
          </div>

          <div className="container-label">
            <label>Cargo: </label>
            <select
              value={cargo}
              onChange={(e) => setCargo(e.target.value)}
              class="input-select"
            >
              <option value="">---</option>
              <option value="c1">C1</option>
              <option value="c2">C2</option>
              <option value="c3">C3</option>
            </select>
          </div>

          <div className="container-label">
            <label>E-mail: </label>
            <input
              type="text"
              name="email"
              value={email}
              class="input-text"
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>

          <div className="container-label">
            <label>Senha: </label>
            <input
              type="text"
              name="senha"
              value={senha}
              class="input-text"
              onChange={(e) => setSenha(e.target.value)}
            />
          </div>

          <input type="submit" class="btn-enviar" name="btn" value="Enviar" />
        </form>
      </div>

      <VLibras forceOnload={true}></VLibras>
    </>
  );
}
