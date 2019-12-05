package dao;

import libs.DbConnection;
import libs.Message;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public Message getByValue(int id) {
        return null;
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }
}
