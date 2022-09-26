import check from "../../Images/check.svg"
import close_chat from "../../Images/close-chat.svg"
import api from "../../Api";

function ActionButton({type, demandaId, userId, handleChangeDemandaAtual}) {

    function request() {
        api.patch(`/demandas/status/${demandaId}`, {
            userId: userId,
            action: type
        })
        .then(res => {
            handleChangeDemandaAtual(res.data);
            console.log(res.data);
        }).catch(err => {
            console.warn(err);
        });
    }

    return <img onClick={request} src={type === "accept" ? check : close_chat} alt={`${type} button`} />;
}

export default ActionButton;