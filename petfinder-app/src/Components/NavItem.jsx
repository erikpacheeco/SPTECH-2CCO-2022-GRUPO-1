function NavItem(props) {

    return (
        <button id={props.id} className="nav-item" >{props.label}</button>
    );
}

export default NavItem;