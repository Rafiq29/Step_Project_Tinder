package service;

import dao.UserDAO;
import libs.LoginException;
import libs.User;

public class LoginService {
    private UserDAO users;
    public LoginService()
    {
        users = new UserDAO();
    }
    public boolean check(User user) throws LoginException {
        for (User us: users) {
            if (us.equals(user))
                return true;
        }
        throw new LoginException("Login failed");
    }
}
