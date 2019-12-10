package service;

import dao.LikesDAO;
import dao.UserDAO;
import libs.Like;
import libs.User;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LikeService {
    private UserDAO users;
    private LikesDAO likes;
    public static boolean liked;
    public int count;
    private int id;

    public int getLocalId() {
        return id;
    }

    public LikeService() {
        count = 0;
        liked = false;
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
        return getUserNotMe().collect(Collectors.toList()).get(0);
    }

    public Stream<User> getUserNotMe() {
        return users.stream()
                .filter(oneUser -> oneUser.getId() != id);
    }

    public User getNext() {
        if (count == getUserNotMe().count() && !liked)
            count = 0;
        return getUserNotMe().collect(Collectors.toList()).get(count++);
    }

    public void setLocalId(int id) {
        this.id = id;
    }
}

