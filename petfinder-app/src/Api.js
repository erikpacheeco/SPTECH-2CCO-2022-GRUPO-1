import axios from "axios";

const dns = "ec2-52-54-137-10.compute-1.amazonaws.com"

const api = axios.create({
    baseURL:`http://${dns}:8080`
}) 

export default api;
