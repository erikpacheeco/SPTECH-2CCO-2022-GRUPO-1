import Input from "./Input";
import Button from "./Button"


function Form(props) {
    Form.defaultProps = {
        action: "",
        title: "Form",
        inputs: [<Input/>, <Input/>],
        buttons: [<Button/>, <Button/>],
        isLogin: false
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
                    {
                        props.isLogin 
                            ?(<a className="link-senha link">Esqueci a senha</a>) 
                            :(<></>)
                    }
                </div>

                <div className="button-container">
                    {
                        props.buttons.map((button)=>(
                            <Button id={button.props.id} label={button.props.label}/>
                        ))
                    }
                </div>
            </form>
        </div>
    );
}

export default Form;