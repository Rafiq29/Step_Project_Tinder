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
    public static boolean liked;
    private int id;
    int nextUser;

    public LikeService() {
        users = new UserDAO();
    }

    public void like(int user_liked) {
        liked = true;
        try {
            final String SQLQ = "INSERT INTO likes(user_likes, user_liked) VALUES (?,?)";
            Connection connection = DbConnection.getConnection();
            PreparedStatement insertLikes = connection.prepareStatement(SQLQ);
            insertLikes.setInt(1, id);
            insertLikes.setInt(2, user_liked);
            insertLikes.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isLast() {
        return nextUser == users.getAllId().size() - 1;
    }

    private int getNextUserId(Cookie[] cookies) {
        List<Integer> allId = users.getAllId();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("%USERLIKE%")) {
                    nextUser = Integer.parseInt(cookie.getValue());
                    if (!isLast())
                        return allId.get(allId.indexOf(nextUser) + 1);
                } else if (cookie.getName().equals("%ID%"))
                    id = Integer.parseInt(cookie.getValue());
            }
        }
        return nextUser = allId.get(0);
    }
    public Cookie getNext(HashMap<String, Object> data, Cookie[] cookie) {
        int nextUser = getNextUserId(cookie);
        Optional<User> byValue = users.getByValue(nextUser);
        byValue.ifPresent(user -> {
            data.put("id", user.getId());
            data.put("username", user.getUsername());
                    data.put("imgURL", user.getImgURL());
                }
        );
        return new Cookie("%USERLIKE%", String.valueOf(nextUser));
    }
}
