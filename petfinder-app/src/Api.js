import axios from "axios";

const dns = "localhost"

const api = axios.create({
    baseURL:`http://${dns}:8080`
}) 

export default api;