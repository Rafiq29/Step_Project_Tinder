package service;

import libs.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LikedService {
    public void like(int user_like, int user_liked) throws SQLException {
        final String SQLQ = "INSERT INTO likes(user_like, user_liked) VALUES (?,?)";
        Connection connection = DbConnection.getConnection();
        PreparedStatement insertLikes = connection.prepareStatement(SQLQ);
        insertLikes.setInt(1,user_like);
        insertLikes.setInt(2,user_liked);
        insertLikes.execute();
    }
}
