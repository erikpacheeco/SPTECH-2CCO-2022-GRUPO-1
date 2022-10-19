import React, { useEffect } from "react";
import { useState } from "react";
import "./fileUploadModal.css";
import previewDocImport from "../../Images/file-doc.svg";
import previewCorrectImport from "../../Images/file-success.svg";
import previewImgImport from "../../Images/image-files.svg";
import closeIcon from "../../Images/close-small-red.svg";
import apiMsg from "../../ApiMsg";

function FileUploadModal({
        isOpen, 
        setModalIsOpen,
        remetenteId,
        demandaId
    }) {

    const [uploadType, setUploadType] = useState("img");
    const [previewDoc, setPreviewDoc] = useState(previewDocImport);
    const [previewImg, setPreviewImg] = useState(previewImgImport);
    const [formData, setFormData] = useState(new FormData());

    function handleOnChangeImgFile(event, type) {
        setFormData(type == "img" ? new FormData(document.querySelector("#idFormImg")) : new FormData(document.querySelector("#idFormFile")));
        if (event.target.files.length > 0) {
            setPreviewImg(URL.createObjectURL(event.target.files[0]));
        }
    }

    function handleOnChangeDocFile(event, type) {
        setFormData(type == "img" ? new FormData(document.querySelector("#idFormImg")) : new FormData(document.querySelector("#idFormFile")));
        if (event.target.files.length > 0) {
            setPreviewDoc(previewCorrectImport);
        }
    }

    function handleOnSubmit(evt) {
        evt.preventDefault();

        apiMsg.post(
            "/message/file",
            formData,
            {
                headers: {
                    'Content-type': "multipart/form-data"
                }
            }
        )
        .then((res) => {
            setModalIsOpen(false);
            setPreviewImg(previewImgImport);
            setPreviewDoc(previewDocImport);
        })
        .catch(err => {
            console.error(err);
        });
    }

    return (
        <div className={`file-modal-container file-upload-modal-${isOpen ? "open" : "closed"}`}>

            <div className="file-modal-label">
                <div className="file-modal-label-text">Selecione um tipo de arquivo:</div>
                <img src={closeIcon} alt="" className="file-modal-label-close" onClick={() => setModalIsOpen(false)}/>
            </div>

            <div className={`file-modal-type-btn ${uploadType == "img" ? "file-modal-type-btn-active" : ""}`} onClick={() => {setUploadType("img")}}>Imagem</div>
            <div className={`file-modal-type-btn ${uploadType == "file" ? "file-modal-type-btn-active" : ""}`} onClick={() => {setUploadType("file")}}>Arquivo</div>

            {/* form imagem */}
            <form onSubmit={evt => handleOnSubmit(evt, "img")} className={`file-upload-modal-form file-upload-modal-form-${uploadType == "img" ? "open" : "closed"}`} id="idFormImg" encType="multipart/form-data">
                <input type="hidden" name="tipo" value="img"/>
                <input type="hidden" name="remetenteId" value={remetenteId}/>
                <input type="hidden" name="demandaId" value={demandaId}/>
                <label className="file-upload-modal-form-label" htmlFor="idInputImg">
                    <div>Selecione um arquivo</div>
                    <img  className="file-upload-modal-preview" src={previewImg} alt="preview" />
                    <input className="file-upload-modal-form-input" type="file" name="file" id="idInputImg" hidden readOnly onChange={evt => handleOnChangeImgFile(evt, "img")} accept="image/*" required/>
                </label>
                <input className="file-upload-modal-form-enviar" type="submit" value="Enviar Imagem"/>
            </form>

            {/* form file */}
            <form onSubmit={evt => handleOnSubmit(evt, "file")} className={`file-upload-modal-form file-upload-modal-form-${uploadType == "file" ? "open" : "closed"}`} id="idFormFile" encType="multipart/form-data">
                <input type="hidden" name="tipo" value="doc"/>
                <input type="hidden" name="remetenteId" value={remetenteId}/>
                <input type="hidden" name="demandaId" value={demandaId}/>
                <label className="file-upload-modal-form-label" htmlFor="idInputFile">
                    <div>Selecione um arquivo</div>
                    <img  className="file-upload-modal-preview" src={previewDoc} alt="preview" />
                    <input className="file-upload-modal-form-input" type="file" name="file" id="idInputFile" hidden readOnly onChange={evt => handleOnChangeDocFile(evt, "doc")} accept=".pdf, .doc, .docx" required/>
                </label>
                <input className="file-upload-modal-form-enviar" type="submit" value="Enviar Arquivo"/>
            </form>
        </div>
    );
}

export default FileUploadModal;