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
        <nav class="nav">
            <div class="container-nav">
                <img class="application-menu" src={app_menu} alt="icone do menu"/>
                <div class="container-btn-nav navbar-container-itens">
                    {
                        props.itens.map((element)=>(
                            <NavItem 
                                isSelected={element.props.isSelected} 
                                navigateTo={element.props.navigateTo}
                                id={element.props.id}
                                label={element.props.label}
                            />
                        ))
                    }
                </div>
                <img class="perfil-nav" src={perfil} alt="botão para o perfil"/>
            </div>
        </nav>
    </header>
    );
}

export default HeaderUser;