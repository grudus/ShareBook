import { httpGet, httpPost } from "../http/httpClient";


export const getAllGroups = () => {
    return httpGet("groups");
};

export const addGroup = (name, photoUrl) => {
    return httpPost("groups", { name, photoUrl })
};
