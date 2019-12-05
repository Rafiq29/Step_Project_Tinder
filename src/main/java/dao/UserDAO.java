package dao;

import libs.DbConnection;
import libs.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class UserDAO implements DAO<User> {
    private List<User> users;
//TODO: Fix users.add
    @Override
    public void read() throws SQLException {
        Connection conn = DbConnection.getConnection();
        final String SQLQ = "SELECT*FROM users";
        PreparedStatement preparedStatement = conn.prepareStatement(SQLQ);
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next())
        {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            int id = resultSet.getInt("id");
            users.add(new User(id,username,password));
        }

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
    public List<Integer> getAllId() {
        return null;
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }
}
