let btnProximo = document.querySelector("#btnProximo");
let btnCadastrar = document.querySelector("#btnCadastrar");
let sessionInstituicao = document.querySelector(".session-instituicao");
let sessionUser = document.querySelector(".session-user");
let btnVoltar = document.querySelector("#btnVoltar");
let idActualForm = document.querySelector("#idActualForm");

btnVoltar.addEventListener("click", (evt) => {
    idActualForm.innerHTML = "1";
    toForm2();
})

btnProximo.addEventListener("click", (evt) => {
    idActualForm.innerHTML = "2";
    toForm1();
})

let toForm2 = () => {
    btnProximo.style.display = "inline-block";
    sessionInstituicao.style.display = "block";
    btnCadastrar.style.display = "none";
    sessionUser.style.display = "none";
    btnVoltar.style.display = "none";
}

let toForm1 = () => {
    btnProximo.style.display = "none";
    sessionInstituicao.style.display = "none";
    btnCadastrar.style.display = "inline-block";
    sessionUser.style.display = "block";
    btnVoltar.style.display = "inline-block";
}