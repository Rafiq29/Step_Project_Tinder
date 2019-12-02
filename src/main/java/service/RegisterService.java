package service;

import libs.DbConnection;
import libs.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterService {
    public void register(User user) throws SQLException {
        final String SQLQ = "INSERT INTO users (username, password) VALUES (?,?)";
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQLQ);
        preparedStatement.setString(1,user.getUsername());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.execute();
    }
}
