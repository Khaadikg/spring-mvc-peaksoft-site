package peaksoft.dao;

import peaksoft.entities.Course;
import peaksoft.entities.Group;

import java.util.List;


public interface CourseDao {
    List<Course> getCoursesByCompany(Long courseId);
    List<Course> getAllCourses();
    void addCourse(long companyId, Course course);
    List<Course> getCoursesByName(String name);
    Course getCourseById(Long id);

    void updateCourse(Course course, Long id);

    void deleteCourse(Course course);
}
