package service;

import dao.LikesDAO;
import dao.UserDAO;
import libs.Like;
import libs.User;

import java.util.List;

public class LikeService {
    private UserDAO users;
    private LikesDAO likes;
    public static boolean liked;
    private int id;

    public LikeService() {
        likes = new LikesDAO();
        users = new UserDAO();
    }

    public void like(int user_liked) {
        liked = true;
        Like like = new Like(id, user_liked);
        if (!likes.getDatabase().contains(like))
            likes.add(like);
    }

    public User getFirst() {
        return users.getDatabase().get(0);
    }

    public User getNext(int currentUser) {
        return users.stream()
                .filter(oneUser -> oneUser.getId() == currentUser + 1 && oneUser.getId() != id)
                .findFirst()
                .orElse(users.getDatabase().get(0));
    }

    public void setLocalId(int id) {
        this.id = id;
    }

    public boolean isLast(int id) {
        List<Integer> allId = users.getAllId();
        return allId.get(allId.size() - 1) == id;
    }
}
