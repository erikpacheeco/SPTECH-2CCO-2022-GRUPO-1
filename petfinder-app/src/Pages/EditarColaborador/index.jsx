import './styles.css';
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import React, { useState } from "react";
//import { useNavigate } from "react-router-dom";
//import Swal from "sweetalert2";
//import withReactContent from "sweetalert2-react-content";

function resetValues() {
    return { nome: "", cargo: "" }
}

function EditarColaborador() {
    const [values, setValues] = useState(resetValues)
    //const navigate = useNavigate()
    //const swal = withReactContent(Swal);

    function handleChange(event) {
        const { value, name } = event.target;
        setValues({ ...values, [name]: value, })
    }

    return(
        <>
            <HeaderApp itens={[
                <NavItem label="Dashboard" />,
                <NavItem label="Padrinhos" />,
                <NavItem label="Demandas" />
            ]}
            />
            <div className="container">
                <div className="form-container">
                    <div className="editar-colaborador-form">
                        <form>
                            <h1>Editar Colaborador</h1>

                            {/* Inputs */}
                            <div className="input-container">
                                <label /*htmlFor="email"*/>Nome Completo</label>
                                <input
                                    id="nome"
                                    type="text"
                                    name="nome"
                                    required
                                    value={values.nome}
                                    onChange={handleChange}
                                />
                            </div>

                            <div className="input-container">
                                <label /*htmlFor="senha"*/>Cargo</label>
                                <select  id="cargo" name="cargo" required>
                                    <option value="c1">C1</option>
                                    <option value="c2">C2</option>
                                    <option value="c3">C3</option>
                                </select>
                            </div>

                            {/* Buttons */}
                            <div className="button-container">
                                <button type="submit" id="salvar" className="btn-form">Salvar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            
        </>
    )
}

export default EditarColaborador;