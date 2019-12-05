package dao;

import libs.DbConnection;
import libs.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User> {
    private List<User> users;

    //TODO: Farid's note: Add gender, profession, imgUrl column to database please
    public UserDAO() {
        users = new LinkedList<>();
        read();
    }

    @Override
    public void read() {
        try {
            Connection conn = DbConnection.getConnection();
            final String SQLQ = "SELECT * FROM users";
            PreparedStatement preparedStatement = conn.prepareStatement(SQLQ);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getString("username"), resultSet.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getDatabase() {
        return users;
    }

    @Override
    public Optional<User> getByValue(int id) {
        for (User oneUser : users) {
            if (oneUser.getId() == id)
                return Optional.of(oneUser);
        }
        return Optional.empty();
    }

    @Override
    public List<Integer> getAllId() {
        List<Integer> result = new LinkedList<>();
        users.forEach(user -> {
            result.add(user.getId());
        });
        return result;
    }

    @Override
    public void clear() throws SQLException {
        Connection connection = DbConnection.getConnection();
        final String SQLQ = "DELETE FROM users";
        PreparedStatement preparedStatement = connection.prepareStatement(SQLQ);
        preparedStatement.executeUpdate();
        users = new LinkedList<>();
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }
}
