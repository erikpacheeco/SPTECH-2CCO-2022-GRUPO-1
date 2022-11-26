import axios from "axios";
import host from "./Host.js";

const api_msg = axios.create({
    baseURL:`http://54.147.103.31:8080`
}) 

export default api_msg;
