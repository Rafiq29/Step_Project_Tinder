package service;

import dao.LikesDAO;
import dao.UserDAO;
import libs.Like;
import libs.OutOfUserException;
import libs.User;

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
        userIds = users.getAllId();
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

    public boolean isLast() {
        return userIds.isEmpty();
    }

    public boolean isLiked() {
        return liked;
    }

    public User getNext(int user_liked) throws OutOfUserException {
        userIds.remove(Integer.valueOf(user_liked));
        if (!isLast())
            return users.get(userIds.get(0));
        else throw new OutOfUserException();
    }

    public void setLocalId(int id) {
        this.id = id;
    }
}

