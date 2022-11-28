import axios from "axios";
import host from "./Host.js";

const api_msg = axios.create({
    baseURL:`http://52.203.70.101:8081/api-msg`
}) 

export default api_msg;
