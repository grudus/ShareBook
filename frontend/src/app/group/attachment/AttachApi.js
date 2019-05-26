import { httpGet, httpPost } from "../../http/httpClient";


export const addAttachment = (groupId, postId, file) => {
    return httpPost(`groups/${groupId}/posts/${postId}/attachments`, { file });
};

export const getAttachment = (groupId, postId, attachmentId) => {
    return httpGet(`groups/${groupId}/posts/${postId}/attachments/${attachmentId}`);
};

