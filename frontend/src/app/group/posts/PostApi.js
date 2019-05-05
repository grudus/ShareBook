import { httpPost } from "../../http/httpClient";


export const addPost = (groupId, text) => {
    return httpPost(`groups/${groupId}/posts`, { text });
};

