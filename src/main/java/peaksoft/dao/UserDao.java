package peaksoft.dao;

import peaksoft.entities.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(long id);
    void update(long id, User user);
    void delete(User user);
}
