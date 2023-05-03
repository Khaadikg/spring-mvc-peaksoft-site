package peaksoft.dao.impl;

import org.springframework.stereotype.Repository;
import peaksoft.dao.StudentDao;
import peaksoft.entities.Group;
import peaksoft.entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Student> getAllStudents() {
        List<Student> list = manager.createQuery("From Student", Student.class).getResultList();
        return list;
    }

    @Override
    public void addStudent(Student student, long groupId) {
        Group group = manager.find(Group.class, groupId);
        student.setGroup(group);
//        manager.persist(student);
        manager.merge(student);
    }

    @Override
    public Student getStudentById(long id) {
        return manager.find(Student.class, id);
    }

    @Override
    public void update(long id, Student student) {
        Student student1 = getStudentById(id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
//        manager.merge(student1);
        addStudent(student1, student.getGroupId());
    }

    @Override
    public void delete(Student student) {
        manager.remove(manager.contains(student)? student : manager.merge(student));
    }

    @Override
    public List<Student> getStudentsByTeacherId(long teacherId) {
        List<Student> students = manager.createQuery(
                        "select s from Student s join Group g on s.group.id = g.id join  g.courses c  join Teacher t on t.id = c.teacher.id where t.id = :key", Student.class)
                .setParameter("key",teacherId).getResultList();
        return students;
    }
    @Override
    public List<Student> getStudentsByCompany(long companyId){
        List<Student> students = manager.createQuery(
                "select s from Student s join Group g on s.group.id = g.id join  g.courses c  join Company com on com.id = c.teacher.id where com.id = :key", Student.class)
                .setParameter("key", companyId).getResultList();
        return students;
    }

}
