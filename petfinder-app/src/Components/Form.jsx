import Input from "./Input";


function Form(props) {
    Form.defaultProps = {
        action: "",
        title: "Form",
        inputs: [<Input/>, <Input/>]
    }
    return (
        <div className="form-container">
            <form action={props.action}>
                <h1>{props.title}</h1>

                {
                    props.inputs.map((input)=>(
                        <Input 
                            label={input.props.label}
                            id={input.props.id}
                            required={input.props.required}
                            type={input.props.type}
                        />
                    ))
                }

                <div className="label-container">
                    <a className="link-senha link">Esqueci a senha</a>
                </div>

                <div className="button-container">
                    <input type="submit" value="Login" className="btn-form" />
                    <button value="Cadastre-se" className="btn-form">Cadastre-se</button>
                </div>
            </form>
        </div>
    );
}

export default Form;