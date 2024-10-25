package service;

import java.util.List;

public interface CommonService <T>{
    void update(T user);
    void create(T user);
    void delete(int id);
    T getById(int id);
    List<T> getAll();
}
