import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import { useParams } from "react-router-dom";
import api from "../../Api";
import HeaderApp from "../../Components/HeaderApp";
import './adicionar-mimos.css';

function AdicionarMimos() {

    // states
    const [mimos, setMimos] = useState([1, 2, 3, 4, 5, 6, 7, 8]);
    const [mimo, setMimo] = useState({});
    const [pet, setPet] = useState({});
    const [formData, setFormData] = useState(new FormData());

    // params
    const params = useParams();

    // init
    useEffect(() => {
        api.get(`/pets/${params.id}`)
        .then(res => {
            setPet(res.data);
        })
        .catch(err => {
            console.error(err);
        });
    }, []);
    
    useEffect(() => {
        setFormData(new FormData(document.querySelector("#idForm")));
        for (const value of formData.values()) {
            console.log(value);
        }
    }, [mimo]);

    function onHandleSubmit(evt) {
        evt.preventDefault();
        api.post(
            `/pets/${params.id}/premios`,
            formData,
            {
                headers: {
                    'Content-Type': 'multipart/form-data',
                }
            }
        )
        .then(res => {
            console.log(res.data);
        })
        .catch(err => {
            console.error(err);
        })
    }

    return (
    <>
        <HeaderApp />

        <div className="adicionar-mimos-main-container">
            <h1>Mimos do Pet</h1>

            {/* form de envio de mimo */}
            <h2>Adicionar Mimo</h2>
            <form id="idForm" method="post" target="/test" encType="multipart/form-data" onSubmit={onHandleSubmit} className="adicionar-mimos-form">
                <input type="file" name="file" onChange={(evt) => setMimo({file:evt.target.files[0]})}/>
                <button type="submit">submit</button>
            </form>

            {/* lista de mimos */}
            <div className="adicionar-mimos-lista-mimos-container">
                <h2>Lista de mimos</h2>

                {mimos.map(mimo => {
                    return (<div key={mimo} className="adicionar-mimos-card">mimo</div>);
                })}
            </div>
        </div>
    </>
    );
}

export default AdicionarMimos;