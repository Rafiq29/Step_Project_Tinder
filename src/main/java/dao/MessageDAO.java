package dao;

import libs.DbConnection;
import libs.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
        messages = new LinkedList<>();
        try {
            Connection conn = DbConnection.getConnection();
            final String SQLQ = "SELECT * FROM message";
            PreparedStatement preparedStatement = conn.prepareStatement(SQLQ);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                messages.add(new Message(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_to"),
                        resultSet.getInt("user_from"),
                        resultSet.getString("message"),
                        resultSet.getInt("localId"),
                        resultSet.getString("dateTime")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer size() {
        return messages.size();
    }

    @Override
    public List<Message> getDatabase() {
        return messages;
    }

    @Override
    public List<Integer> getAllId() {
        return messages.stream().map(Message::getId)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Message get(int id) {
        return stream()
                .filter(oneMessage -> oneMessage.getId() == id)
                .collect(Collectors.toList()).get(0);
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
        try {
            Connection conn = DbConnection.getConnection();
            final String SQLQ = "INSERT INTO message (user_from, user_to, message, localId, datetime) values (?,?,?,?,?)";
            PreparedStatement insertMessage = conn.prepareStatement(SQLQ);
            insertMessage.setInt(1, message.getUserFrom());
            insertMessage.setInt(2, message.getUserTo());
            insertMessage.setString(3, message.getMessage());
            insertMessage.setInt(4, message.getLocalId());
            insertMessage.setString(5, message.getDateTime());

            insertMessage.executeUpdate();
            messages.add(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stream<Message> stream() {
        return messages.stream();
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }


}
