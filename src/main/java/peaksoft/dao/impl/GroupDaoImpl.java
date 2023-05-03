package peaksoft.dao.impl;

import org.springframework.stereotype.Repository;
import peaksoft.dao.GroupDao;
import peaksoft.entities.Course;
import peaksoft.entities.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class GroupDaoImpl implements GroupDao {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Group> getAllGroups() {
        List<Group> list = manager.createQuery("FROM Group", Group.class).getResultList();
        return list;
    }

    @Override
    public void addGroup(Group group) {
        manager.persist(group);

    }

    @Override
    public Group getGroupById(long id) {
        return manager.find(Group.class, id);
    }

    @Override
    public void update(long id, Group group) {
        Group group1 = getGroupById(id);
        group1.setGroupName(group.getGroupName());
        group1.setDateStart(group.getDateStart());
        group1.setDateFinish(group.getDateFinish());
        manager.merge(group1);
    }

    @Override
    public void delete(Group group) {
        manager.remove(manager.contains(group)? group : manager.merge(group));
    }

}
