// solicitar resgate
let containerResgate = document.createElement("div");
let spanTextSolicitar = document.createElement("span");
let iconResgate = document.createElement("img");

// span
spanTextSolicitar.id = "textSolicitar";
spanTextSolicitar.classList.add("text-resgate");
spanTextSolicitar.innerHTML = "Solicitar Resgate";

// img
iconResgate.classList.add("icon-resgate");
iconResgate.src = "./svg/icon_resgate.svg";
iconResgate.alt = "Telefone com pata de cachorro como símbolo";

// container
containerResgate.id = "idSolicitar";
containerResgate.classList.add("container-resgate");

// append
containerResgate.appendChild(spanTextSolicitar);
containerResgate.appendChild(iconResgate);

document.querySelector("body").appendChild(containerResgate);

// events

containerResgate.addEventListener("mouseover", (evt) => {
  spanTextSolicitar.style.display = "block";
});

containerResgate.addEventListener("mouseout", (evt) => {
    spanTextSolicitar.style.display = "none";
});

containerResgate.addEventListener("click", (evt) => {
  window.location.href = "#";
})

// fim solicitar resgate