import "./styles.css";
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import FormContainer from "../../Components/FormContainer";

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
        </>
    )
}

export default CadastroPet;