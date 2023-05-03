package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CourseDao;
import peaksoft.entities.Course;
import peaksoft.service.CourseService;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseDao courseDao;
    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }


    @Override
    public List<Course> getCoursesByCompany(Long courseId) {
        return courseDao.getCoursesByCompany(courseId);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    @Override
    public void addCourse(long companyId, Course course) {
        courseDao.addCourse(companyId, course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseDao.getCourseById(id);
    }

    @Override
    public void updateCourse(Course course, Long id) {
        courseDao.updateCourse(course, id);
    }

    @Override
    public void deleteCourse(Course course) {
        courseDao.deleteCourse(course);
    }
    @Override
    public List<Course> getCoursesByName(String name) {
        return courseDao.getCoursesByName(name);
    }
}