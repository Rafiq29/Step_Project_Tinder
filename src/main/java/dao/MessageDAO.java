package dao;

import libs.DbConnection;
import libs.Message;
import libs.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MessageDAO implements DAO<Message> {
    private List<Message> messages;

    public MessageDAO() {
        this.messages = new ArrayList<>();
    }

    @Override
    public void read() throws SQLException {
        Connection conn = DbConnection.getConnection();
        final String SQLQ = "SELECT*FROM message";
        PreparedStatement preparedStatement = conn.prepareStatement(SQLQ);
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next())
        {
            //String message, String localId, String dateTime, int userTo, int userFrom
            String message = resultSet.getString("message");
            String localId = resultSet.getString("localId");
            String dateTime = resultSet.getString("dateTime");
            int userTo = resultSet.getInt("userTo");
            int userFrom = resultSet.getInt("userFrom");
            int id = resultSet.getInt("id");
            messages.add(new Message(message,localId,dateTime,userTo,userFrom));
        }
    }

    @Override
    public List getDatabase() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public Message getByValue(int id) {
        return null;
    }

    @Override
    public List<Integer> getAllId() {
        return null;
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }
}
