import { httpGet } from "../http/httpClient";


export const getCurrentUser = () => {
    return httpGet("user");
};
