import './side-bar-item.css';
import React from "react";

function SideBarItem(props){

    SideBarItem.defaultProps = {
        id: "sideBarItem",
        label: "Label"
    }
    
    return (
        <>
            <button className="btn-side-bar-itens">
                <div className="btn-side-bar-itens-container">
                    <img src={props.icon} alt={props.icon} />
                    <p>{props.label}</p>
                </div>
            </button>
        </>
    )

}

export default SideBarItem;