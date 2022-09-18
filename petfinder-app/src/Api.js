import axios from "axios";

const dns = "localhost"

const api = axios.create({
    baseURL:`https://${dns}:8080`
}) 

export default api;
