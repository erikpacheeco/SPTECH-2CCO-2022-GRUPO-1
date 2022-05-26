import "../css/form.css"

function Button(props){
    Button.defaultProps = {
        id : "id",
        label:"Label"
    }
    return(
        <button id={props.id} className="btn-form">{props.label}</button>
    );
}

export default Button;