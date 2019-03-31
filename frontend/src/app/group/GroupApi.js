import { httpGet, httpPost } from "../http/httpClient";


export const getAllGroups = () => {
    return httpGet("groups");
};

export const addGroup = (name) => {
    return httpPost("groups", { name })
};
