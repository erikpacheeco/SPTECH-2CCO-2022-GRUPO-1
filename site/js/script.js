let idSolicitar = document.querySelector("#idSolicitar");
let idImgSolicitar = document.querySelector("#imgSolicitar");
let idTextSolicitar = document.querySelector("#textSolicitar");

idImgSolicitar.addEventListener("mouseover", (evt) => {
    idImgSolicitar.style.display = "none";
    idTextSolicitar.style.display = "block";
});

idImgSolicitar.addEventListener("mouseout", (evt) => {
    idImgSolicitar.style.display = "inline-block";
    idTextSolicitar.style.display = "none";
});