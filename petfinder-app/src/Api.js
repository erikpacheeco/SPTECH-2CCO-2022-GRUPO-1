import axios from "axios";

const dns = "192.168.0.71"

const api = axios.create({
    baseURL:`http://${dns}:8080`
}) 

export default api;