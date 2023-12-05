package dao;

import java.util.Map;
import java.util.Optional;

import model.User;

public interface UserDao {
    User findByUsernameAndPassword(Map<String, Object> params);

    Optional<User> findByUsername(String username);

    void save(User user);

    void delete(User user);
}
