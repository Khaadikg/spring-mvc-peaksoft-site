package peaksoft.service;

import peaksoft.entities.Course;
import peaksoft.entities.Group;

import java.util.List;
public interface GroupService {
    List<Group> getAllGroups();
    void addGroup(long companyId,Group group);
    Group getGroupById(long id);
    void update(long id, Group group);
    void delete(Group group);

//    List<Course> getCoursesByGroup(Long groupId);
}
