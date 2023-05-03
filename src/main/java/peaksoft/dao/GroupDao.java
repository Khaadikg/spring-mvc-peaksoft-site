package peaksoft.dao;

import peaksoft.entities.Course;
import peaksoft.entities.Group;

import java.util.List;

public interface GroupDao {
    List<Group> getAllGroups();
    void addGroup(Group group);
    Group getGroupById(long id);
    void update(long id, Group group);
    void delete(Group group);
}
