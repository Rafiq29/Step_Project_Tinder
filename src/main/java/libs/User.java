package libs;

import java.util.Objects;

public class User {
    private int id;
    private String username;
    private String password;
    private String imgURL;

    public User(int id, String username, String password, String imgURL) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.imgURL = imgURL;
    }

    public User(String username, String password, String imgURL) {
        this.username = username;
        this.password = password;
        this.imgURL = imgURL;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getImgURL() {
        return imgURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                imgURL.equals(user.imgURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, imgURL);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", imgURL='" + imgURL + '\'' +
                '}';
    }
}
