package dao;

import libs.User;

import java.util.List;

public class UserDAO implements DAO<User> {
    @Override
    public void read(User user) {

    }

    @Override
    public List<User> getDatabase() {
        throw new RuntimeException("Not implemented yet");
    }
}
