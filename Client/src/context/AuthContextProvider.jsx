import React, {
  useEffect,
  useState,
  createContext,
  useCallback,
  useContext,
} from "react";

const userTokenContext = createContext(null);
const AuthContext = ({ children }) => {
  const [token, setToken] = useState(localStorage.getItem("token"));
  useEffect(() => {
    console.log({ currentUserToken: `Bearear - ${token}` });
  }, [token]);
  const login = useCallback(({ token: userToken, isAdmin }) => {
    localStorage.setItem("token", userToken);
    localStorage.setItem("role", isAdmin ? "admin" : "user");
    setToken(userToken);
  }, []);
  const logout = useCallback(() => {
    setToken(null);
    localStorage.removeItem("token");
  }, []);
  return (
    <userTokenContext.Provider
      value={{
        login,
        logout,
        userAccessToken: token,
      }}
    >
      {children}
    </userTokenContext.Provider>
  );
};

export default AuthContext;

export const useAuth = () => useContext(userTokenContext);
