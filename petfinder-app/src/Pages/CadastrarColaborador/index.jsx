import React, { useState } from "react";
import "./cadastro-colaborador.css";
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";

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

      <div className="cad-colab-container">
        <form action="" className="cad-colab-form-container-cad">
          <h1 className="cad-colab-title">CADASTRAR COLABORADOR</h1>
          <div className="cad-colab-container-label">
            <label className="cad-colab-">Nome completo: </label>
            <input
              type="text"
              name="nome_completo"
              value={nome}
              className="cad-colab-input-text"
              onChange={(e) => setNome(e.target.value)}
            />
          </div>

          <div className="cad-colab-container-label">
            <label>Cargo: </label>
            <select
              value={cargo}
              onChange={(e) => setCargo(e.target.value)}
              className="cad-colab-input-select"
            >
              <option value="">---</option>
              <option value="c1">C1</option>
              <option value="c2">C2</option>
              <option value="c3">C3</option>
            </select>
          </div>

          <div className="cad-colab-container-label">
            <label>E-mail: </label>
            <input
              type="text"
              name="email"
              value={email}
              className="cad-colab-input-text"
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>

          <div className="cad-colab-container-label">
            <label>Senha: </label>
            <input
              type="text"
              name="senha"
              value={senha}
              className="cad-colab-input-text"
              onChange={(e) => setSenha(e.target.value)}
            />
          </div>

          <input type="submit" className="cad-colab-btn-enviar" name="btn" value="Enviar" />
        </form>
      </div>
    </>
  );
}
