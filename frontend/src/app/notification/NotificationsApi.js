import React from 'react';
import { httpGet } from "../http/httpClient";


export const getNotifications = () => {
    return httpGet("notifications");
};
