import HeaderBasic from "../Components/HeaderBasic";
import "../css/login.css"
import "../css/form.css"
import Input from "../Components/Input";
import Form from "../Components/Form"
import Button from "../Components/Button"
import api from "../Api";

function Login() {

    api.get("/usuarios").then(res=>{
        console.log(res.data)
    })

    return (
        <>
            <HeaderBasic/>
            <div className="container">
                <Form
                    action = "GET"
                    title="Login"
                    isLogin= {true}
                    inputs={[
                        <Input id="email" label="E-mail" type="e-mail" required={true}/>,
                        <Input id="senha" label="Senha" type="password" required={true}/>,
                    ,]}
                    buttons={[
                        <Button id="login" label="Login"/>,
                        <Button id="cadastro" label="Cadastre-se"/>,
                    ,]}
                />
            </div>    
        </>
    );
}

export default Login;