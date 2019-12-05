package dao;

import libs.DbConnection;
import libs.Like;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

public class LikesDAO implements DAO<Like> {
    List<Like> likes;
    @Override
    public void read() {
        Connection conn = DbConnection.getConnection();

    }

    @Override
    public List<Like> getDatabase() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public Like getByValue(int id) {
        return null;
    }

    @Override
    public Iterator<Like> iterator() {
        return likes.iterator();
    }
}
