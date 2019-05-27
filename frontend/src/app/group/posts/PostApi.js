import { httpGet, httpPost } from "../../http/httpClient";


export const addPost = (groupId, text, attachmentId) => {
    return httpPost(`groups/${groupId}/posts`, { text, attachmentId });
};

export const getPostsForGroup = (groupId) => {
    return httpGet(`groups/${groupId}/posts?withComments=true`);
};

