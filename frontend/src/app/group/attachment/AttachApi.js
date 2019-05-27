import { downloadFile, httpPost } from "../../http/httpClient";

export function download(attachmentId) {
    return downloadFile(`attachments/${attachmentId}`);
}


export const addAttachment = (groupId, postId, file) => {
    return httpPost(`groups/${groupId}/posts/${postId}/attachments`, { file });
};
