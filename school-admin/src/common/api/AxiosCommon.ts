import axios from "axios";

const AxiosBase = axios.create({
    baseURL: 'http://172.21.176.1:8000/api',
});

export default AxiosBase;