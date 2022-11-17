import React from "react";
import "./filter-button.css"
import check from  "../../Images/check-small.svg"
import { useState } from "react";

export default function FilterButton(props) {

    FilterButton.defaultProps = {
        id: '',
        label: 'filter',
    }

    function handleChangeCheckbox() {
        let btn = document.getElementById("btn-" + props.id + "-" + props.label);
        let img = document.getElementById("img-" + props.id + "-" + props.label);
        let input = document.getElementById(props.id + "-" + props.label)
        if (btn.classList.contains("btn-filtro-checkbox")) {
            btn.classList.remove("btn-filtro-checkbox")
            btn.classList.add("btn-filtro-checkbox-active");
            img.classList.remove("btn-filtro-hide")
            input.checked = true;
        } else {
            btn.classList.remove("btn-filtro-checkbox-active")
            btn.classList.add("btn-filtro-checkbox");
            img.classList.add("btn-filtro-hide")
            input.checked = false;
        }

        props.onChange(input.checked)
    }

    return (
        <>
            <button id={"btn-" + props.id + "-" + props.label} className="btn-filtro-checkbox btn-filtro-container .btn-filtro-btn" onClick={handleChangeCheckbox}>
                <img src={check} id={"img-" + props.id + "-" + props.label} className="btn-filtro-hide"/>
            </button>
            <input type="checkbox" id={props.id + "-" + props.label} className="btn-filtro-hide btn-filtro-input" onChange={() => {
                handleChangeCheckbox()
            }} />
            <label className="btn-filtro-label" htmlFor={ props.id + "-" + props.label}>{props.label}</label>
        </>
    );
}