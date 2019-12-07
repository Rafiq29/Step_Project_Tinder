package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface DAO<T> extends Iterable<T> {
    void read() throws SQLException;

    List<T> getDatabase();

    Optional<T> getByValue(int id);

    List<Integer> getAllId();

    void clear() throws SQLException;

    void add(T data);

    T get(int id);

    Stream<T> stream();
}
