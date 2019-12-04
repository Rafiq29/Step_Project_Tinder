package dao;

import java.util.List;

public interface DAO <T> extends Iterable<T> {
    void read();
    List<T> getDatabase();
    T getByValue(int id);
}
