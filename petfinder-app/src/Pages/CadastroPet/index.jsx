import "./styles.css";
import HeaderUser from "../../Components/HeaderUser";
import NavItem from "../../Components/NavItem";
import FormContainer from "../../Components/FormContainer";

function CadastroPet() {
    return(
        <>
            <HeaderUser itens={[
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