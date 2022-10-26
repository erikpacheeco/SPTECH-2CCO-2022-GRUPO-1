import './lista-padrinho.css';
import HeaderApp from "../../Components/HeaderApp";
import PadrinhoListItem from "../../Components/PadrinhoListItem";
import api from "../../Api"
import React, { useEffect, useState } from 'react';
import VLibras from "@djpfs/react-vlibras"

function ListaPadrinho() {

    const [allPadrinhos, setAllPadrinhos] = useState([]);

    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));

    console.log(objUser.fkInstituicao.id)
    useEffect(() => {
    
        api.get(`/usuarios/padrinhos/${objUser.fkInstituicao.id}`).then((res) => {
            setAllPadrinhos(res.data)
        })
        
    }, [])

    console.log(allPadrinhos)

    return (
        <>

            <div className="lista-padrinho-root">
                <HeaderApp/>

                <div className="lista-padrinho">
                    <div className="lista-padrinho-container">
                        <div className="lista-padrinho-title">
                            <h2>Padrinhos</h2>
                        </div>
                        <div id="idCadastroPadrinhoList" className="lista-padrinho-list">
                            {
                                allPadrinhos.map((padrinho) => (
                                    <PadrinhoListItem
                                        id={padrinho.id}
                                        nome={padrinho.nome}
                                    />
                                ))
                            }
                        </div>
                    </div>
                </div>
                
            </div>

            <VLibras forceOnload={true}></VLibras>
        </>
    )
}

export default ListaPadrinho;