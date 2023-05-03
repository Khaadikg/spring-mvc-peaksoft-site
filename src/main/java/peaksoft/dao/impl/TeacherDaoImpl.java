package peaksoft.dao.impl;

import org.springframework.stereotype.Repository;
import peaksoft.dao.TeacherDao;
import peaksoft.entities.Course;
import peaksoft.entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class TeacherDaoImpl implements TeacherDao {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> list= manager.createQuery("FROM Teacher", Teacher.class).getResultList();
        return list;
    }

    @Override
    public void addTeacher(Teacher teacher, Long courseId) {
        Course course = manager.find(Course.class, courseId);
        teacher.setCourse(course);
//        manager.persist(teacher);
        manager.merge(teacher);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return manager.find(Teacher.class, id);
    }

    @Override
    public void updateTeacher(Teacher teacher, Long id) {
        Teacher teacher1 = getTeacherById(id);
        teacher1.setFirstName(teacher.getFirstName());
        teacher1.setLastName(teacher.getLastName());
        teacher1.setEmail(teacher.getEmail());
        teacher1.setCourse(teacher.getCourse());
//        manager.merge(teacher1);
        addTeacher(teacher1, teacher.getCourseId());
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        manager.remove(manager.contains(teacher)? teacher : manager.merge(teacher));
    }
}
