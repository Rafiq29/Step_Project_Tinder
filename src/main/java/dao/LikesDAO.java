package dao;

import libs.DbConnection;
import libs.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LikesDAO implements DAO<Like> {
    List<Like> likes;

    public LikesDAO() {
        likes = new LinkedList<>();
        read();
    }

    @Override
    public void read() {

        try {
            Connection conn = DbConnection.getConnection();
            final String SQLQ = "SELECT * FROM likes";
            PreparedStatement preparedStatement = conn.prepareStatement(SQLQ);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                likes.add(new Like(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_likes"),
                        resultSet.getInt("user_liked")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Like> getDatabase() {
        return likes;
    }

    @Override
    public Optional<Like> getByValue(int id) {
        for (Like like : likes) {
            if (like.getId() == id) {
                return Optional.of(like);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Integer> getAllId() {
        return likes.stream().map(Like::getId).collect(Collectors.toCollection(() -> new LinkedList<Integer>()));
    }

    @Override
    public void clear() throws SQLException {
        Connection conn = DbConnection.getConnection();
        final String SQLQ = "DELETE  FROM likes";
        PreparedStatement preparedStatement = conn.prepareStatement(SQLQ);
        preparedStatement.executeUpdate();
        likes = new LinkedList<>();
    }

    @Override
    public void add(Like like) {
        //TODO: INSERT DATA to the database
        try {
            Connection conn = DbConnection.getConnection();
            final String SQLQ = "INSERT INTO likes (user_likes, user_liked) values (?,?)";
            PreparedStatement insertLikes = conn.prepareStatement(SQLQ);
            insertLikes.setInt(1, like.getUser_likes());
            insertLikes.setInt(2, like.getUser_liked());

            insertLikes.executeUpdate();
            likes.add(like);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stream<Like> stream_convert() {
        return likes.stream();
    }

    @Override
    public Iterator<Like> iterator() {
        return likes.iterator();
    }
}
