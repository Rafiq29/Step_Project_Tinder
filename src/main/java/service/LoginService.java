package service;

import dao.UserDAO;
import libs.LoginException;
import libs.User;

public class LoginService {
    private UserDAO users;
    private boolean isLogged;
    public LoginService() {
        isLogged = false;
        users = new UserDAO();
    }

    public int check(User user) throws LoginException {
        for (User us : users) {
            if (us.checkEqual(user)) {
                isLogged = true;
                return us.getId();
            }

        }
        throw new LoginException("Login failed");
    }

    public boolean isLogged() {
        return isLogged;
    }
}
