import axios from "axios";

export const loadEmployer = (callback) => {
    const config = {
        url:'/employers',
    };
    axios.request(config).then(response => callback(response.data));
}