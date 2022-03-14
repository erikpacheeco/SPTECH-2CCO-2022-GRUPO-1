let btnProximo = document.querySelector("#btnProximo");
let btnCadastrar = document.querySelector("#btnCadastrar");
let sessionInstituicao = document.querySelector(".session-instituicao");
let sessionUser = document.querySelector(".session-user");
let btnVoltar = document.querySelector("#btnVoltar");

let prCircle1 = document.querySelector("#prCircle1");
let prCircle2 = document.querySelector("#prCircle2");

btnVoltar.addEventListener("click", (evt) => {
    prCircle2.classList.remove("pr-filled");
    prCircle1.classList.add("pr-filled");
    hideForm2();
})

btnProximo.addEventListener("click", (evt) => {
    prCircle1.classList.remove("pr-filled");
    prCircle2.classList.add("pr-filled");
    hideForm1();
})

let hideForm1 = () => {
    btnProximo.style.display = "none";
    sessionInstituicao.style.display = "none";
    btnCadastrar.style.display = "inline-block";
    sessionUser.style.display = "block";
    btnVoltar.style.display = "inline-block";
}

let hideForm2 = () => {
    btnProximo.style.display = "inline-block";
    sessionInstituicao.style.display = "block";
    btnCadastrar.style.display = "none";
    sessionUser.style.display = "none";
    btnVoltar.style.display = "none";
}