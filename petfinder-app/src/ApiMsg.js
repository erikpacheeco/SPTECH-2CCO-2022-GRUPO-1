import axios from "axios";

const dns = "ec2-52-54-137-10.compute-1.amazonaws.com";

const api_msg = axios.create({
    baseURL:`http://${dns}:8081/api-msg`
}) 

export default api_msg;
