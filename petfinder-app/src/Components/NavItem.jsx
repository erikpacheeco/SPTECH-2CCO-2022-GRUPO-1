import { useNavigate } from "react-router-dom";

function NavItem(props) {
    NavItem.defaultProps = {
        id: "navItem",
        label: "Label",
        navigateTo: "/",
        isSelected: false,
    }


    const navigate = useNavigate()
    return (
        <button
            id={props.id}
            name="nav-item"
            className={props.isSelected ? ("nav-item text-green") : ("nav-item")}
            onClick={() => {
                navigate(props.navigateTo)
            }}
        >
            {props.label}
        </button>
    );
}

export default NavItem;