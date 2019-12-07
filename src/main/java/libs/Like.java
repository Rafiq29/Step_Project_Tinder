package libs;

import java.util.Objects;

public class Like {
    private int id;
    private int user_like;
    private int user_liked;

    public Like(int user_like, int user_liked) {
        this.user_like = user_like;
        this.user_liked = user_liked;
    }
    public Like(int id, int user_like, int user_liked) {
        this.id = id;
        this.user_like = user_like;
        this.user_liked = user_liked;
    }
    public int getUser_like() {
        return user_like;
    }

    public int getUser_liked() {
        return user_liked;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Like)) return false;
        Like like = (Like) o;
        return user_like == like.user_like &&
                user_liked == like.user_liked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_like, user_liked);
    }

    @Override
    public String toString() {
        return "Like{" +
                "user_like=" + user_like +
                ", user_liked=" + user_liked +
                '}';
    }
}
