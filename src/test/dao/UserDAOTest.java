package dao;

import java.sql.SQLException;

class UserDAOTest {

    @org.junit.jupiter.api.Test
    void getDatabase() throws SQLException {
        UserDAO users = new UserDAO();
        System.out.println(users.getDatabase());
        users.clear();
        //System.out.println(users.getDatabase());
    }
}