package dao;

import libs.DbConnection;
import libs.User;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

public class UserDAO implements DAO<User> {
    private List<User> users;
    //TODO: Farid's note: Add gender, profession, imgUrl column to database please
    @Override
    public void read() {
        Connection conn = DbConnection.getConnection();

    }

    @Override
    public List<User> getDatabase() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public User getByValue(int id) {
        return null;
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }
}
