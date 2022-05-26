import "../css/form.css"

function Input(props) {
    Input.defaultProps = {
        id : "id",
        label:"Label",
        required: false,
        type: "text"
    }
    return (
        <div className="input-container">
            <label htmlFor={props.id}>{props.label}</label>
            <input id={props.id} type={props.type} required={props.required}/>
        </div>
    );
}


export default Input;