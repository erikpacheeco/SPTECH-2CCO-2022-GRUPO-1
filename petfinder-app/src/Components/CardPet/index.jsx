import doente from "../../Images/png_img/saude_2.png"
import "./card-pet.css"

export default function CardPet(props) {

    CardPet.defaultProps= {
        isDoente: false,
        nome: "Label",
    }

    return (
        <figure class="card-pet-img-pet" style={{backgroundImage: `url("http://localhost:8080${props.backgroundImage}")`}}>
            <p>{props.nome}</p>
            <img
                className={
                    props.isDoente ? ("") : ("card-user-hide")
                } src={doente} alt={props.tipo}
            />
        </figure>
    );
}