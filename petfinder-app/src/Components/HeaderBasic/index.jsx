import { Link } from 'react-router-dom';
import logo from '../../Images/img_logo.svg'
import NavItem from '../NavItem';
import "./header-basic.css"
import React from "react";

function HeaderBasic() {

  return (
    <nav className="header-basic-navbar">
      <section className="header-basic-navbar-container">
        <a className="header-basic-navbar-container-logo link" href="/">
          <img className="header-basic-logo" src={logo} alt="" />
          <span className="header-basic-navbar-logo-nome">
            <span className="header-basic-logo-nome-verde">Pet</span>Finder
          </span>
        </a>

        <div className="header-basic-navbar-container-itens">
          <NavItem id="home" label="Home"/>

          <NavItem id="login" label="Login" navigateTo="/"/>

          <Link to="/cadastro-user" className="header-basic-nav-item">
            <div className="header-basic-bg-yellow">Cadastro</div>
          </Link>

        </div>
      </section>
    </nav>
  );
}

export default HeaderBasic;
