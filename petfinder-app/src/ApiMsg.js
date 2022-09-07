import axios from "axios";

const dns = "localhost"

const api_msg = axios.create({
    baseURL:`http://${dns}:8081/api-msg`
}) 

export default api_msg;
