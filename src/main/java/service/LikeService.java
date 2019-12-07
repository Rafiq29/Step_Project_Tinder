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
import java.util.stream.Collectors;

public class LikeService {
    private UserDAO users;
    public static boolean liked;
    private int id;
    private int currUser;
    private Cookie[] cookies;

    public LikeService(Cookie[] cookies) {
        setLocalUserId(cookies);
        this.cookies = cookies;
        users = new UserDAO();
    }

    private void setLocalUserId(Cookie[] cookies) {
        for (Cookie oneCookie : cookies) {
            if (oneCookie.getName().equals("%ID%")) {
                id = Integer.parseInt(oneCookie.getValue());
                break;
            }
        }
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
        List<Integer> allIds = users.getAllId();
        int size = allIds.size();
        Integer integer = allIds.get(size - 1);
        boolean b = currUser >= integer;
        return b;
    }

    private void getCurrentLikedId() {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("%USERLIKE%")) {
                currUser = Integer.parseInt(cookie.getValue());
                break;
            }
        }
    }

    private int getNextUserId() {
        List<Integer> allId = users.getAllId().stream().filter(oneId -> oneId != id).collect(Collectors.toList());
        getCurrentLikedId();
        if (!isLast())
            return allId.get(allId.indexOf(currUser) + 1);
        return currUser = allId.get(0);
    }

    public Cookie getNext(HashMap<String, Object> data) {
        int nextUser = getNextUserId();
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
