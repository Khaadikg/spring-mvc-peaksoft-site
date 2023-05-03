package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CourseDao;
import peaksoft.dao.GroupDao;
import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.service.GroupService;

import java.util.ArrayList;
import java.util.List;
@Service
public class GroupServiceImpl implements GroupService {
    private final GroupDao groupDao;
    private final CourseDao courseDao;
    @Autowired
    public GroupServiceImpl(GroupDao groupDao, CourseDao courseDao) {
        this.groupDao = groupDao;
        this.courseDao = courseDao;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupDao.getAllGroups();
    }

    @Override
    public void addGroup(long courseId, Group group) {
        List<Course> courses = new ArrayList<>();
        Course course = courseDao.getCourseById(courseId);
        courses.add(course);
        group.setCourses(courses);
        List<Group> groups = new ArrayList<>();
        course.setGroups(groups);
        groupDao.addGroup(group);
    }

    @Override
    public Group getGroupById(long id) {
        return groupDao.getGroupById(id);
    }

    @Override
    public void update(long id, Group group) {
        groupDao.update(id, group);
    }

    @Override
    public void delete(Group group) {
        groupDao.delete(group);
    }

//    @Override
//    public List<Course> getCoursesByGroup(Long groupId) {
//        return null;
//    }
}
