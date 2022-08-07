import app_menu from "../Images/application-menu.svg"
import perfil from "../Images/perfil-header.svg"
import NavItem from "../Components/NavItem"
import "../css/style.css"

function HeaderUser(props) {
    HeaderUser.defaultProps = {
        itens : [<NavItem isSelected = {true}/>,<NavItem/>,<NavItem/>]
    }
    
    return (
    <header>
        <nav className="nav">
            <div className="container-nav">
                <img className="application-menu" src={app_menu} alt="icone do menu"/>
                <div className="container-btn-nav navbar-container-itens">
                    {
                        props.itens.map((element)=> {
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
                <img className="perfil-nav" src={perfil} alt="botão para o perfil"/>
            </div>
        </nav>
    </header>
    );
}

export default HeaderUser;