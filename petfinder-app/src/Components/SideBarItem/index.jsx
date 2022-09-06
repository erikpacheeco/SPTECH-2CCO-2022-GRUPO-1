import './side-bar-item.css';
import React from "react";
import { useNavigate } from 'react-router-dom';

function SideBarItem(props) {

    SideBarItem.defaultProps = {
        id: "sideBarItem",
        navigateTo: "/",
        label: "Label"
    }

    const navigate = useNavigate()
    return (
        <>
            <button className="btn-side-bar-itens" onClick={() => {
                navigate(props.navigateTo);
            }}>
                <div className="btn-side-bar-itens-container">
                    <img src={props.icon} alt={props.icon} />
                    <p>{props.label}</p>
                </div>
            </button>
        </>
    )

}

export default SideBarItem;