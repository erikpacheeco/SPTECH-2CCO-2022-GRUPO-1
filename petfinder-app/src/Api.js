  import host from "./Host.js";

const api = axios.create({
    baseURL:`${host}:8080`,
});

export default api;