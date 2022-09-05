import "./styles.css";
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import FormContainer from "../../Components/FormContainer";
import React from "react";
import VLibras from "@djpfs/react-vlibras"

function CadastroPet() {
    return(
        <>
            <HeaderApp itens={[
                <NavItem label="Dashboard" />,
                <NavItem label="Padrinhos" />,
                <NavItem label="Demandas" />,
                <NavItem isSelected={true} label="Pets" />
            ]} />

            <FormContainer title="Cadastro de Pet"/>
            <VLibras forceOnload={true}></VLibras>
        </>
    )
}

export default CadastroPet;