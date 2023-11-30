package dao;

import java.util.Map;

import model.User;

public interface UserDao {
    User findByUsernameAndPassword(Map<String, Object> params);

    User findByUsername(String username);

    void save(User user);

    void delete(User user);
}
