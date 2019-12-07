package service;

import dao.UserDAO;
import libs.LoginException;
import libs.User;

public class LoginService {
    private UserDAO users;
    public LoginService() {
        users = new UserDAO();
    }

    public int check(User user) throws LoginException {
        for (User us : users) {
            if (us.checkEqual(user))
                return us.getId();

        }
        throw new LoginException("Login failed");
    }
}
