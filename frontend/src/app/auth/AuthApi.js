import { httpPost, httpPostForm } from "../http/httpClient";


export const login = (email, password, rememberMe) => {
  return httpPostForm("auth/login", {email, password, rememberMe});
};
 export const register = (state) => {
  return httpPost("auth/register", state);
};

 export const logout = () => {
     return httpPost("auth/logout");
 };
