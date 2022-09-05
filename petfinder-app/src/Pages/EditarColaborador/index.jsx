import './EditarColaborador.css';
import HeaderApp from "../../Components/HeaderApp";
import NavItem from "../../Components/NavItem";
import React, { useState } from "react";

function resetValues() {
    return { nome: "", cargo: "" }
}

function EditarColaborador() {
    const [values, setValues] = useState(resetValues)

    function handleChange(event) {
        const { value, name } = event.target;
        setValues({ ...values, [name]: value, })
    }

    return(
        <>
            <HeaderApp 
                sideItens={[
                    
                ]}
                
                itens={[
                    <NavItem label="Dashboard" />,
                    <NavItem label="Padrinhos" />,
                    <NavItem label="Demandas" />
                ]}
            />
            <div className="editar-colaborador-container">
                <div className="editar-colaborador-form-container">
                    <form>
                        <div className="editar-colaborador-form-titulo">
                            <h1>Editar Colaborador</h1>
                        </div>

                        <div className="editar-colaborador-input-container">
                            <div className="editar-colaborador-input">
                                <label htmlFor="text">Nome Completo</label>
                                <input
                                    id="nome"
                                    type="text"
                                    name="nome"
                                    required
                                    value={values.nome}
                                    onChange={handleChange}
                                />

                                <div className="editar-colaborador-input">
                                    <label htmlFor="text">Cargo</label>
                                    <select  id="cargo" name="cargo" required>
                                        <option value="c1">C1</option>
                                        <option value="c2">C2</option>
                                        <option value="c3">C3</option>
                                    </select>
                                </div>

                                <div className="editar-colaborador-button-container">
                                    <button type="submit" id="salvar" className="editar-colaborador-btn-form">Salvar</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            
        </>
    )
}

export default EditarColaborador;