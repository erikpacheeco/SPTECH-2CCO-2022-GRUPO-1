// retorna inteiro pseudorandomico dentro da faixa de parametros
function randomNum(min, max) {
	return Math.random() * (max - min) + min;
}

// retorna circulo verde, de um lado específico da página
let newCircle = (side, height) => {

    // criando elemento
    let circle = document.createElement("div");

    // definindo distancia da borda
    let sideDistance = randomNum(1, 10);

    // adicionando classes e definindo regras de estilo
    circle.classList.add("circle");
    circle.style.top = height + "px";

    if (side === "left") {
        circle.style.left = `${sideDistance}%`;
    } else if (side === "right") {
        circle.style.right = `${sideDistance}%`;
    }

    return circle;
}

let body = document.querySelector("body");

// adicionando circulos
for (let i = 0; i < 4; i++) {
    let position = i % 2 == 0 ? "left" : "right";
    // console.log(position);
    body.appendChild(newCircle(position, (i * 300) + randomNum(20, 150)))
}