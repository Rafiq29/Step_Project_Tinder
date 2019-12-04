package service;

import dao.UserDAO;
import libs.DbConnection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class LikedService {
    private UserDAO users;
    public LikedService()
    {
        users = new UserDAO();
    }
    public void like(int user_likes, int user_liked) throws SQLException {
        final String SQLQ = "INSERT INTO likes(user_likes, user_liked) VALUES (?,?)";
        Connection connection = DbConnection.getConnection();
        PreparedStatement insertLikes = connection.prepareStatement(SQLQ);
        insertLikes.setInt(1,user_likes);
        insertLikes.setInt(2,user_liked);
        insertLikes.execute();
    }
    public void getNext(HashMap<String, Object> data, HttpServletRequest request, HttpServletResponse response)
    {
        Cookie[] cookies = request.getCookies();
        int next_user = -1;
        if (cookies != null)
        {
            for (Cookie c: cookies) {
                if (c.getName().equals("%USERLIKE%"))
                {
                    next_user = Integer.parseInt(c.getValue());

                }

            }
        }
    }
}
