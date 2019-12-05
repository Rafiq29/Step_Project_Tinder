package dao;

import libs.DbConnection;
import libs.Like;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
    public Optional<Like> getByValue(int id) {
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
    public Iterator<Like> iterator() {
        return likes.iterator();
    }
}
