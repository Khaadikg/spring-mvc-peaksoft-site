package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.entities.User;

import javax.transaction.Transactional;
import java.util.List;
public interface UserService {

    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(long id);
    void updateUser(long id, User user);
    void deleteUser(User user);
}
