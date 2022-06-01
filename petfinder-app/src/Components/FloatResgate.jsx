import "../css/style.css"
import "../css/cadastro-usuario.css"
import icon_resgate from "../Images/icon_resgate.svg"

function FloatResgate() {
    return (
        <div
            id="idSolicitar"
            className="container-resgate"
            onMouseOver={()=>{
                if(document.getElementById("textSolicitar").style.display == "none"){
                    document.getElementById("textSolicitar").style.display = "block"
                }
            }}
            onMouseOut={()=>{
                if(document.getElementById("textSolicitar").style.display == "block"){
                    document.getElementById("textSolicitar").style.display = "none"
                }
            }}
        >
            <span
                id="textSolicitar"
                className="text-resgate"
                onMouseOver={() => {
                    document.getElementById("textSolicitar").style.display = "block";
                }}
                onMouseOut={() => {
                    document.getElementById("textSolicitar").style.display = "none";
                }}
            >
                Solicitar Resgate
            </span>
            <img
                className="icon-resgate"
                src={icon_resgate}
                alt="Telefone com pata de cachorro como símbolo"
                onMouseOver={() => {
                    document.getElementById("textSolicitar").style.display = "block";
                }}
            />
        </div>
    );
}

export default FloatResgate;