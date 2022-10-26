import './lista-adm.css';
import HeaderApp from "../../Components/HeaderApp";
import plus from "../../Images/plus.svg";
import React, {useState, useEffect} from 'react';
import api from "../../Api";
import VLibras from "@djpfs/react-vlibras"
import { useNavigate } from "react-router-dom";
import AdmListItem from '../../Components/AdmListItem';

function ListaAdm(){

    const [adm, setAdm] = useState([]);
    const navigate = useNavigate();

    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));
    console.log("info adm");
    console.log(adm);
    console.log("info obj user");
    console.log(objUser);

    function handleAddItemList() {
        navigate(`/cadastro-adm/${objUser.id}`)
    }

    useEffect(() => {
        api.get(`/usuarios/nivel-acesso/sysadm`).then((res) => {
            setAdm(res.data);
            console.log(res.data);
        })
      }, []);

    return(
        <>
            <div className="lista-adm-root">
            <HeaderApp/>

                <div className="lista-adm">
                    <div className="lista-adm-container">
                        <div className="lista-adm-tittle">
                            <h2>Administradores Cadastrados</h2>
                            <div className="lista-adm-add-icon" onClick={handleAddItemList}>
                                <img src={plus} alt=""/>
                            </div>
                        </div>
                        <div className="lista-adm">
                            {
                                adm.map(a => (
                                    <AdmListItem key={a.id} nome={a.nome} id={a.id}/>
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

export default ListaAdm;