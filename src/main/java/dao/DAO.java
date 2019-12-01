package dao;

import java.util.List;

public interface DAO <T>{
    void add(T user);
    List<T> getAll();
    List<T> getLiked();
}
