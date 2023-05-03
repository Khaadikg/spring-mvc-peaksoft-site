package peaksoft.service;

import peaksoft.entities.Course;
import peaksoft.entities.Group;

import java.util.List;

public interface CourseService {
    List<Course> getCoursesByCompany(Long courseId);
    List<Course> getAllCourses();

    void addCourse(long companyId, Course course);

    Course getCourseById(Long id);

    void updateCourse(Course course, Long id);

    void deleteCourse(Course course);

    List<Course> getCoursesByName(String name);
}
