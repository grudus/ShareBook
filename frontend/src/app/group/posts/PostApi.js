import { httpGet, httpPost } from "../../http/httpClient";


export const addPost = (groupId, text) => {
    return httpPost(`groups/${groupId}/posts`, { text });
};

export const getPostsForGroup = (groupId) => {
    return httpGet(`groups/${groupId}/posts`);
};

