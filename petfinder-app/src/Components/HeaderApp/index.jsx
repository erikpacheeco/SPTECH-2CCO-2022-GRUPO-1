import app_menu from "../../Images/application-menu.svg"
import perfil from "../../Images/people.svg"
import NavItem from "../NavItem"
import "./header-app.css"

function HeaderApp(props) {
    HeaderApp.defaultProps = {
        itens: [<NavItem isSelected={true} />, <NavItem />, <NavItem />]
    }

    return (
        <header>
            <nav className="header-app-nav">
                <div className="header-app-container-nav">
                    <img className="header-app-application-menu" src={app_menu} alt="icone do menu" />
                    <div className="header-app-container-btn-nav header-app-nav-container-itens">
                        {
                            props.itens.map((element) => {
                                return (<NavItem
                                    isSelected={element.props.isSelected}
                                    navigateTo={element.props.navigateTo}
                                    id={element.props.id}
                                    label={element.props.label}
                                    key={props.itens.indexOf(element)}
                                />)
                            })
                        }
                    </div>
                    <div className="header-app-perfil-nav">
                        <img  src={perfil} alt="botão para o perfil" />
                        <p className="header-app-p">Perfil</p>
                    </div>
                </div>
            </nav>
        </header>
    );
}

export default HeaderApp;