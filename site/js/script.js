let idSolicitar = document.querySelector("#idSolicitar");
let idTextSolicitar = document.querySelector("#textSolicitar");

idSolicitar.addEventListener("mouseover", (evt) => {
  idTextSolicitar.style.display = "block";
});

idSolicitar.addEventListener("mouseout", (evt) => {
    idTextSolicitar.style.display = "none";
});