import logo from '../Images/img_logo.svg'
import "../css/style.css"
import NavItem from './NavItem';
import { Link } from 'react-router-dom';

function HeaderBasic() {

  return (
    <nav className="navbar">
      <section className="navbar-container">
        <a className="navbar-container-logo link" href="./index.html">
          <img className="logo" src={logo} alt="" />
          <span className="navbar-logo-nome">
            <span className="logo-nome-verde">Pet</span>Finder
          </span>
        </a>

        <div className="navbar-container-itens">
          <NavItem id="home" label="Home" />

          <Link to="/login">
            <NavItem id="login" label="Login" />
          </Link>
          
          <Link to="/cadastro" className="nav-item">
            <div className="bg-yellow">Cadastro</div>
          </Link>

        </div>
      </section>
    </nav>
  );
}

export default HeaderBasic;
