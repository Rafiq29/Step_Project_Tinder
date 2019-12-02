package dao;

import libs.Message;

import java.util.List;

public class MessageDAO implements DAO<Message> {

    @Override
    public void read(Message user) {


    }

    @Override
    public List getDatabase() {
        throw new RuntimeException("Not implemented yet");
    }
}
