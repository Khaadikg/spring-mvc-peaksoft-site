package peaksoft.dao.impl;

import org.springframework.stereotype.Repository;
import peaksoft.dao.CourseDao;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.entities.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseDaoImpl implements CourseDao {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = manager.createQuery("FROM Course ", Course.class).getResultList();
        return courses;
    }
    @Override
    public void addCourse(long companyId, Course course) {
        Company company = manager.find(Company.class, companyId);
        course.setCompany(company);
        manager.persist(course);
    }
    @Override
    public List<Course> getCoursesByCompany(Long courseId) {
        List<Course> courses = manager.createQuery("select c from  Course c join c.company com where com.id=:key", Course.class)
                .setParameter("key",courseId).getResultList();
        return courses;
    }
    @Override

    public List<Course> getCoursesByName(String name) {

        List<Course> courses = manager.createQuery("select c from  Course c where lower(c.courseName) like lower(:key)", Course.class)
                .setParameter( "key", "%" + name + "%").getResultList();
        return courses;
    }
    @Override
    public Course getCourseById(Long id) {
        Course course = manager.find(Course.class, id);
        return course;
    }

    @Override
    public void updateCourse(Course course, Long id) {
        Course course1 = getCourseById(id);
        course1.setCourseName(course.getCourseName());
        course1.setDurationMonth(course.getDurationMonth());
        manager.merge(course1);
    }

    @Override
    public void deleteCourse(Course course) {
        manager.remove(manager.contains(course)? course : manager.merge(course));
    }
}
