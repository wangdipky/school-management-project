import axios from "axios";
import { Bounce, toast } from "react-toastify";

const AxiosBase = axios.create({
    baseURL: 'http://172.21.176.1:8000/api',
});


AxiosBase.interceptors.response.use(res => res, (err) => {
    toast.error(err?.response?.data?.data || 'Bad request', {
        position: 'top-right',
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: false,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
        theme: 'light',
        transition: Bounce,
    });
})

export default AxiosBase;