import styles from "./styles.css";
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import plus from "../../Images/plus.svg";
import ColaboradorListaItem from '../../Components/ColaboradorListaItem';
import React, {useState, useEffect} from 'react';
import api from "../../Api";

function ListaColaborador(){

    function handleAddItemList() {
        console.log("click!");
    }

    const [colaborador, setColaborador] = useState([]);
    const [user, setUser] = useState();

    const objUser = JSON.parse(localStorage.getItem("petfinder_user"));

    console.log(objUser.fkInstituicao.id);
 

    useEffect(() => {

        // corpo do useEffect. São as ações ou aquilo que eu quero que execute // ocorre quando a tela é renderizada 
        api.get(`/usuarios/por-instituicao/${objUser.fkInstituicao.id}`).then((res) => {
            setColaborador(res.data);

            console.log(res.data[0].cargo);
        })
        
      }, []);


    return(
        <>
            <div className="lista-colaborador-root">
                <HeaderApp itens={[
                    <NavItem label="Dashboard" />,
                    <NavItem label="Padrinhos" />,
                    <NavItem label="Demandas" />,
                    <NavItem isSelected={true} label="Colaboradores" />
                ]}
                />

                <div className="lista-colaborador">
                    <div className="lista-colaborador-container">
                        <div className="lista-colaborador-tittle">
                            <h2>Colaboradores Cadastrados</h2>
                            <div className="lista-colaborador-add-icon" onClick={handleAddItemList}>+</div>
                        </div>
                        <div className="lista-colaborador">
                            {
                                colaborador.map(c => (
                                    <ColaboradorListaItem key={c.id} nome={c.nome} cargo={c.cargo}/>
                                ))
                            }
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ListaColaborador;