import { httpGet, httpPost, httpPut } from "../http/httpClient";


export const getAllGroups = () => {
    return httpGet("groups");
};

export const addGroup = (name, photoUrl) => {
    return httpPost("groups", { name, photoUrl })
};

export const addUserToGroup = (groupId, email) => {
    return httpPut(`groups/add-user`, { email, groupId })
};
