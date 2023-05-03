package peaksoft.dao.impl;

import org.springframework.stereotype.Repository;
import peaksoft.dao.UserDao;
import peaksoft.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<User> getAllUsers() {
        List<User> users = entityManager.createQuery("FROM User", User.class).getResultList();
        return users;
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public void update(long id, User user) {
        User user1 = entityManager.find(User.class, id);
        user1.setName(user.getName());
        user1.setLastName(user.getLastName());
        entityManager.merge(user1);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(entityManager.contains(user)? user : entityManager.merge(user));
    }
}
