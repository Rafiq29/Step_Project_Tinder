package service;

import dao.LikesDAO;
import dao.UserDAO;
import libs.Like;
import libs.User;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LikeService {
    private UserDAO users;
    private LikesDAO likes;
    private List<Integer> userIds;
    private boolean liked;
    private int id;

    public int getLocalId() {
        return id;
    }

    public LikeService() {
        liked = false;
        likes = new LikesDAO();
        users = new UserDAO();
        userIds = new LinkedList<>(users.getAllId());
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

    public boolean isLast(int id) {
        return userIds.isEmpty();
    }

    public User getNext(int user_liked) {
        User user = users.get(userIds.get(0));
        userIds.remove(Integer.valueOf(user_liked));
        return user;
    }

    public void setLocalId(int id) {
        this.id = id;
    }
}

