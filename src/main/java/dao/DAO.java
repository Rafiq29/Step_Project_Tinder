package dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T> extends Iterable<T> {
    void read() throws SQLException;
    List<T> getDatabase();
    T getByValue(int id);
    List<Integer> getAllId();
}
