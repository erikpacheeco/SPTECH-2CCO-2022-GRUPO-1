let btnProximo = document.querySelector("#btnProximo");
let btnCadastrar = document.querySelector("#btnCadastrar");
let sessionInstituicao = document.querySelector(".session-instituicao");
let inCEP = document.querySelector("#inCEP");
let sessionUser = document.querySelector(".session-user");
let btnVoltar = document.querySelector("#btnVoltar");

let prCircle1 = document.querySelector("#prCircle1");
let prCircle2 = document.querySelector("#prCircle2");

let inRua = document.querySelector('#inRua');
let inBairro = document.querySelector('#inBairro');
let inCidade = document.querySelector('#inCidade');
let inEstado = document.querySelector('#inEstado');
let inComplemento = document.querySelector('#inComplemento');



inCEP.addEventListener("keyup", (evt) => {
    let ultimoChar = inCEP.value[inCEP.value.length - 1];

    if (isNaN(ultimoChar)) {
        inCEP.value = inCEP.value.replace(ultimoChar, "");
    }

    if (inCEP.value.length === 8) {
        preencherEndereco()
    }
})

function preencherEndereco() {
    fetch(`https://viacep.com.br/ws/${inCEP.value}/json/`)
    .then((response) => response.json())
    .then((data) => {
        inRua.value = data.logradouro;
        inBairro.value = data.bairro;
        inCidade.value = data.localidade;
        inEstado.value = data.uf;
        inComplemento.value = data.complemento;
    })
}

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