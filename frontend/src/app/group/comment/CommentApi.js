import { httpGet, httpPost } from "../../http/httpClient";


export const addComment = (groupId, postId, text) => {
    return httpPost(`groups/${groupId}/posts/${postId}/comments`, { text: "właściwy komentarz"});
};

export const getCommentsForPost = (groupId) => {
    return httpGet(`groups/${groupId}/posts?withComments=true`);
};
