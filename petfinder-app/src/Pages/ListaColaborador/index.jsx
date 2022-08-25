import styles from "./styles.css";
import HeaderUser from "../../Components/HeaderUser";
import NavItem from "../../Components/NavItem";
import plus from "../../Images/plus.svg";
import ColaboradorListaItem from '../../Components/ColaboradorListaItem';
import React, {useState, useEffect} from 'react';
import api from "../../Api";

function ListaColaborador(){

    function handleAddItemList() {
        console.log("click!");
    }

    const [user, setUser] = useState([]);

    useEffect(() => {
        // corpo do useEffect. São as ações ou aquilo que eu quero que execute // ocorre quando a tela é renderizada 
        api.get("usuarios").then((res) => {
            setUser(res.data);
        })
      }, []);


    return(
        <>
            <div className="lista-colaborador-root">
                <HeaderUser itens={[
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
                            {/* {
                                setUser.map((user) => (
                                    <ColaboradorListaItem  nome={user.nome} cargo={user.nivelAcesso}/>
                                ))
                            } */}
                            <ColaboradorListaItem  nome="Erik" cargo="C1"/>
                        </div>
                    </div>
                </div>
            </div>
                
        </>
    )
}

export default ListaColaborador;