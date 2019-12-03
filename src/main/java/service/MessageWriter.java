package service;

import libs.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageWriter {
    public void writeMessage(String message,String dateTime , int localMessageId , int user_to, int user_from) throws SQLException {
        Connection connection = DbConnection.getConnection();
        final String SQLQ = "INSERT INTO messages(message, local_message_id, datetime, user_from, user_to) VALUES (?,?,?,?,?)";
        PreparedStatement insertMessage = connection.prepareStatement(SQLQ);
        insertMessage.setString(1,message);
        insertMessage.setInt(2, localMessageId);
        insertMessage.setString(3, dateTime);
        insertMessage.setInt(4,user_from);
        insertMessage.setInt(5,user_to);
        insertMessage.execute();
    }
}