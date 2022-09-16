import axios from "axios";
import host from "./Host.js";

const api_msg = axios.create({
    baseURL:`${host}:8081/api-msg`
}) 

export default api_msg;
