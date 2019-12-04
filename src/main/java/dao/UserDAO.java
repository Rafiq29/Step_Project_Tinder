package dao;

import libs.User;

import java.util.Iterator;
import java.util.List;

public class UserDAO implements DAO<User> {
    private List<User> users;

    @Override
    public void read() {

    }

    @Override
    public List<User> getDatabase() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }
}
