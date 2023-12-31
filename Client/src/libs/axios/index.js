import axiosClient from "axios";

const requestInterceptor = async request => {
  const userAccessToken = localStorage.getItem("token");
  if (userAccessToken) {
    request.headers.set("Authorization", userAccessToken);
  }
  request.headers.Accept = "application/json";
  return request;
};

const axios = axiosClient.create({
  baseURL: "http://localhost:8080",
});

axios.interceptors.request.use(requestInterceptor);

export default axios;
