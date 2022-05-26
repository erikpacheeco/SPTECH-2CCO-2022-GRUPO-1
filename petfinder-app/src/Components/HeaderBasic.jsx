import logo from '../Images/img_logo.svg'
import "../css/style.css"
import NavItem from './NavItem';

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
          <NavItem id="home" label="Home"/>
          <NavItem id="login" label="Login"/>
          <a className="nav-item">
            <div className="bg-yellow">Cadastro</div>
          </a>
        </div>
      </section>
    </nav>
  );
}

export default HeaderBasic;
