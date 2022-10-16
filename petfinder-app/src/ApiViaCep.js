import axios from "axios";

const api_via_cep = axios.create({
    baseURL:`https://viacep.com.br/ws`,
});

export default api_via_cep;