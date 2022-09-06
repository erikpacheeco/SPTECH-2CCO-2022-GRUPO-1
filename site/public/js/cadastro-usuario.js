let inCEP = document.querySelector("#inCEP")

inCEP.addEventListener("keyup", (evt) => {
    let ultimoChar = inCEP.value[inCEP.value.length - 1];
    console.log("foi");


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