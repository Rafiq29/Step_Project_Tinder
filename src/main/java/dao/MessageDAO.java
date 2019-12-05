package dao;

import libs.DbConnection;
import libs.Message;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class MessageDAO implements DAO<Message> {
    private List<Message> messages;

    public MessageDAO() {
        this.messages = new ArrayList<>();
    }

    @Override
    public void read() {
        Connection connection = DbConnection.getConnection();
    }

    @Override
    public List getDatabase() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public Optional<Message> getByValue(int id) {
        return Optional.empty();
    }

    @Override
    public List<Integer> getAllId() {
        return null;
    }

    @Override
    public void clear() throws SQLException {

    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }
}
