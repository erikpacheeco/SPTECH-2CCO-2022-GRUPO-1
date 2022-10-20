import axios from "axios";
import host from "./Host.js";

const api_msg = axios.create({
    baseURL:`http://3.238.81.12:8080`
}) 

export default api_msg;
