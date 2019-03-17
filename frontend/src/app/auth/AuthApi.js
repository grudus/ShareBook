import { httpPostForm } from "../http/httpClient";


export const login = (email, password, rememberMe) => {
  return httpPostForm("auth/login", {email, password, rememberMe});
};
 export const register = () => {
  return httpPostForm("auth/register", {});
};
