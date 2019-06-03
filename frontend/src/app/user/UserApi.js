import { httpGet, httpPut } from "../http/httpClient";


export const getCurrentUser = () => {
    return httpGet("user");
};

export const updateUser = (user) => {
    return httpPut("user", user);
}
