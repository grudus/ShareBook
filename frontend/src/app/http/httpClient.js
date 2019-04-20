import axios from "axios";
import * as querystring from 'querystring';

const BASE_URL = "http://localhost:8080";

export const httpPost = (path, args) => axios.post(`${BASE_URL}/${path}`, args, { withCredentials: true })
    .catch(e => {
        catchForbiddenError(e)
    })
    .then(({data}) => data);

export const httpPut = (path, args) => axios.put(`${BASE_URL}/${path}`, args, { withCredentials: true })
    .catch(e => {
        catchForbiddenError(e)
    })
    .then(({data}) => data);


export const httpPostForm = (path, args) => axios.post(`${BASE_URL}/${path}`, querystring.stringify(args), {
    withCredentials: true,
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
    }
}).catch(e => {
    catchForbiddenError(e)
});

function catchForbiddenError(e) {
    if (!e.response) {
        alert("Wystąpił nieoczekiwany błąd - brak internetu lub niedostępny serwer")
    }
    else if (e.response.status === 403)
        window.location.href = '/auth/login';
    throw e;
}

export const httpGet = (path, args) => axios.get(`${BASE_URL}/${path}`, { withCredentials: true })
    .catch(e => {
        catchForbiddenError(e);
    })
    .then(({data}) => data);

