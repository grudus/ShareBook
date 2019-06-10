import { httpGet } from "../http/httpClient";

export const getUserActivity = (year) =>
    httpGet(`activity?year=${year}`);
