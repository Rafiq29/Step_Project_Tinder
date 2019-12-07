package dao;

import libs.DbConnection;
import libs.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MessageDAO implements DAO<Message> {
    private List<Message> messages;

    public MessageDAO() {
        this.messages = new ArrayList<>();
        read();
    }

    @Override
    public void read() {
        try {
            Connection conn = DbConnection.getConnection();
            final String SQLQ = "SELECT * FROM message";
            PreparedStatement preparedStatement = conn.prepareStatement(SQLQ);
            ResultSet resultSet = preparedStatement.executeQuery();
            messages.add(new Message(
                    resultSet.getInt("id"),
                    resultSet.getInt("user_from"),
                    resultSet.getInt("user_from"),
                    resultSet.getString("messages"),
                    resultSet.getString("localId"),
                    resultSet.getString("dateTime")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List getDatabase() {
        return messages;
    }

    @Override
    public Optional<Message> getByValue(int id) {
        for (Message message : messages) {
            if (message.getId() == id) {
                return Optional.of(message);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Integer> getAllId() {
        return messages.stream().map(message -> message.getId())
                .collect(Collectors.toCollection(() -> new LinkedList<Integer>()));
    }

    @Override
    public void clear() throws SQLException {
        Connection conn = DbConnection.getConnection();
        final String SQLQ = "DELETE FROM message";
        PreparedStatement preparedStatement = conn.prepareStatement(SQLQ);
        preparedStatement.executeUpdate();
        messages = new LinkedList<>();
    }

    @Override
    public void add(Message message) {
        //TODO: INSERT DATA to the database
        try {
            Connection conn = DbConnection.getConnection();
            final String SQLQ = "INSERT INTO message (user_from, user_to, message, localId, datetime) values (?,?,?,?,?)" ;
            PreparedStatement insertMessage = conn.prepareStatement(SQLQ);
            insertMessage.setInt(1, message.getUserFrom());
            insertMessage.setInt(2, message.getUserTo());
            insertMessage.setString(3, message.getLocalId());
            insertMessage.setString(4, message.getDateTime());

            insertMessage.executeUpdate();
            messages.add(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stream<Message> stream_convert() {
        return messages.stream();
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }
}
