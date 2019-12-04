package dao;

import libs.DbConnection;
import libs.Likes;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

public class LikesDAO implements DAO<Likes> {
    List<Likes> likes;
    @Override
    public void read() {
        Connection conn = DbConnection.getConnection();
    }

    @Override
    public List<Likes> getDatabase() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public Iterator<Likes> iterator() {
        return likes.iterator();
    }
}
