import styles from "./styles.css";
import HeaderApp from "../../Components/HeaderApp";
import ColaboradorListaItem from '../../Components/ColaboradorListaItem';
import React, {useState, useEffect} from 'react';
import api from "../../Api";
import VLibras from "@djpfs/react-vlibras"
import headerFunctions from "../../functions/headerFunctions";
import { useNavigate } from "react-router-dom";
import plus from "../../Images/plus.svg";


function ListaColaborador(){
    
    const [colaborador, setColaborador] = useState([]);
    const [user, setUser] = useState();
    
    const navigate = useNavigate();
    
    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));
    
    function handleAddItemList() {
        navigate(`/cadastro-colaborador/${objUser.id}`)
    }
 
    useEffect(() => {

        // corpo do useEffect. São as ações ou aquilo que eu quero que execute // ocorre quando a tela é renderizada 
        api.get(`/usuarios/por-instituicao/${objUser.fkInstituicao.id}`).then((res) => {
            setColaborador(res.data);

            console.log(res.data);
        })
        
      }, []);


    return(
        <>
            <div className="lista-colaborador-root">
            <HeaderApp/>

                <div className="lista-colaborador">
                    <div className="lista-colaborador-container">
                        <div className="lista-colaborador-tittle">
                            <h2>Colaboradores Cadastrados</h2>
                            <div className="lista-colaborador-add-icon" onClick={handleAddItemList}>
                                <img src={plus} />
                            </div>
                        </div>
                        <div className="lista-colaborador">
                            {
                                colaborador.map(c => (
                                    <ColaboradorListaItem key={c.id} nome={c.nome} cargo={c.cargo}  id={c.id}/>
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

export default ListaColaborador;