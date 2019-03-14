import axios from "axios";
import * as querystring from 'querystring';

const BASE_URL = "http://localhost:8080";

export const httpPost = (path, args) => axios.post(`${BASE_URL}/${path}`, args, {withCredentials: true});

export const httpPostForm = (path, args) => axios.post(`${BASE_URL}/${path}`, querystring.stringify(args), {
    withCredentials: true,
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
    }});
