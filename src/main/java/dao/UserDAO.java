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
                users.add(new User(resultSet.getInt("id"), resultSet.getString("username"),
                        resultSet.getString("name"), resultSet.getString("surname"),
                        resultSet.getString("password"), resultSet.getString("gender"),
                        resultSet.getString("profession"), resultSet.getString("imgurl")));
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
        users.forEach(user -> result.add(user.getId()));
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
    public void add(User user) {
        //TODO: INSERT DATA to the database
        try {
            Connection conn = DbConnection.getConnection();
            final String SQLQ = "INSERT INTO users (username, password, gender, profession, imgurl, name, surname) values (?,?,?,?,?,?,?)";
            PreparedStatement insertUser = conn.prepareStatement(SQLQ);
            insertUser.setString(1, user.getUsername());
            insertUser.setString(2, user.getPassword());
            insertUser.setString(3, user.getGender());
            insertUser.setString(4, user.getProfession());
            insertUser.setString(5, user.getImgURL());
            insertUser.setString(6, user.getName());
            insertUser.setString(6, user.getSurname());

            insertUser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }
}
