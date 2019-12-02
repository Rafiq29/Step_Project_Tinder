package dao;

import java.util.List;

public interface DAO <T>{
    void read(T user);
    List<T> getDatabase();
}
