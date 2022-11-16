import React from "react";
import './btnDashboard.css';

function BtnDashboard({value, active, click}) {
    return(
        <button className={`btn-dashboard ${active ? "btn-dashboard-active" : ""}`} onClick={click}>
            {value}
        </button>
    );
}   

export default BtnDashboard;