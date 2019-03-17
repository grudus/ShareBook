import { httpPostForm } from "../http/httpClient";


export const login = (email, password) => {
  return httpPostForm("auth/login", {email, password});
};
 export const register = () => {
  return httpPostForm("auth/register", {});
};
