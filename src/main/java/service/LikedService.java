package service;

import dao.LikesDAO;
import dao.UserDAO;
import libs.Like;
import libs.User;

import java.util.List;
import java.util.stream.Collectors;

public class LikedService {
    private LikesDAO likes;
    private UserDAO users;

    public LikedService() {
        users = new UserDAO();
        likes = new LikesDAO();
    }

    public List<User> getLikedUsers(int localId) {
        List<Like> likedUserIds = likes.stream()
                .filter(oneLike -> oneLike.getUser_likes() == localId)
                .collect(Collectors.toList());
        return users.stream()
                .filter(oneUser -> likedUserIds.contains(new Like(localId, oneUser.getId())))
                .collect(Collectors.toList());
    }
}
