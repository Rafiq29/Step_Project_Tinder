package service;

import dao.UserDAO;
import libs.DbConnection;
import libs.User;

import javax.servlet.http.Cookie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class LikeService {
    private UserDAO users;
    public static boolean isLiked;
    public LikeService() {
        users = new UserDAO();
    }

    public void like(int user_likes, int user_liked) throws SQLException {
        isLiked = true;
        final String SQLQ = "INSERT INTO likes(user_likes, user_liked) VALUES (?,?)";
        Connection connection = DbConnection.getConnection();
        PreparedStatement insertLikes = connection.prepareStatement(SQLQ);
        insertLikes.setInt(1, user_likes);
        insertLikes.setInt(2, user_liked);
        insertLikes.execute();
    }

    private int getNextUserId(Cookie[] cookies) {
        int nextUser;
        List<Integer> allId = users.getAllId();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                nextUser = Integer.parseInt(cookie.getValue());
                int userIdIndex = allId.indexOf(nextUser);
                if (userIdIndex + 1 < allId.size())
                    return allId.get(userIdIndex + 1);
            }
        }
        return allId.get(0);
    }
    public Cookie getNext(HashMap<String, Object> data, Cookie[] cookie) {
        int nextUser = getNextUserId(cookie);
        Optional<User> byValue = users.getByValue(nextUser);
        byValue.ifPresent(user -> {
                    data.put("username", user.getUsername());
                    data.put("imgURL", user.getImgURL());
                }
        );
        return new Cookie("%USERLIKE%", String.valueOf(nextUser));
    }
}
