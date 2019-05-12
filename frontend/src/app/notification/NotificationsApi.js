import React from 'react';
import { httpGet, httpPut } from "../http/httpClient";

export function seeNotification(id) {
    return httpPut(`notifications/visit/${id}`)
}


export const getNotifications = () => {
    return httpGet("notifications");
};
