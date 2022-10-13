import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import { useParams } from "react-router-dom";
import api from "../../Api";
import HeaderApp from "../../Components/HeaderApp";
import './adicionar-mimos.css';
import IconImg from "../../Images/Icon-img.svg"
import CardPetSimples from "../../Components/CardPetSimples/card-pet-simples";
import testeGatinho from "../../Images/png_img/testeGatinho.jpg";
import noPremio from "../../Images/png_img/gatinhu.png";

function verificarPremio(mimos){

    if(mimos.length > 0){
        return (
                <div className="adicionar-mimos-box-lista">
                    {
                        mimos.map(m => (
                            <CardPetSimples srcImg={m.img}/>
                        ))
                    }  
                </div>
            )
    } else {
        return (
            <div className="adicionar-mimos-box-Nolista">
                <span>Ainda não há prêmio</span>  
                <img src={noPremio} alt="Gato triste" />
            </div>
        )
    }
}

function AdicionarMimos() {

    // states
    const [mimos, setMimos] = useState([]);
    const [mimo, setMimo] = useState({});
    const [pet, setPet] = useState({});
    const [formData, setFormData] = useState(new FormData());

    // params
    const params = useParams();

    useEffect(() => {
        api.get(`/pets/premios/get/${params.id}`).then(res => {
            console.log(res.data);
            if(res.status == 200) {
                setMimos(res.data);
            }
        }).catch(error => {console.log(error)})
        
      }, []);

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
        <div className="adicionar-mimos-root">
            <div className="adicionar-mimos-root-container">
                <h1  className="adicionar-mimos-titulo">Prêmios do Pet</h1>
                <div className="adicionar-mimos-box-premio">

                    <div className="adicionar-mimos-adicionar">
                        <div className="adicionar-mimos-container container-adicionar">
                            <h2 className="adicionar-mimos-subtitulo">Adicionar Prêmio</h2>
                            <form id="idForm" method="post" target="/test" encType="multipart/form-data" onSubmit={onHandleSubmit} className="adicionar-mimos-form">
                                <label for="file" className="adicionar-mimos-label-img">
                                    <img src={IconImg} alt="icone de imagem" />
                                    <span>Adicionar Imagem</span>
                                </label>
                                <input className="adicionar-mimos-input-img" type="file" id="file" name="file" onChange={(evt) => setMimo({file:evt.target.files[0]})}/>
                                <button className="adicionar-mimos-btn-adicionar" type="submit">Adicionar</button>
                            </form>
                        </div>
                    </div>

                    <div className="adicionar-mimos-lista">
                        <div className="adicionar-mimos-container">
                            <h2 className="adicionar-mimos-subtitulo">Lista de Prêmio</h2>
                            {verificarPremio(mimos)}
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </>
    );
}

export default AdicionarMimos;