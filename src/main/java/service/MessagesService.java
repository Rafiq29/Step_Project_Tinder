package service;

import libs.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessagesService {
    public void writeMessage(String message,int user_to, int user_from) throws SQLException {
        Connection connection = DbConnection.getConnection();
        final String SQLQ = "INSERT INTO messages(message, user_from, user_to) VALUES (?,?,?)";
        PreparedStatement insertMessage = connection.prepareStatement(SQLQ);
        insertMessage.setString(1,message);
        insertMessage.setInt(2,user_from);
        insertMessage.setInt(3,user_to);
        insertMessage.execute();
    }
}
