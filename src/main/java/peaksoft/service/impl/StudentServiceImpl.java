package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.StudentDao;
import peaksoft.entities.Student;
import peaksoft.service.StudentService;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;
    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public void addStudent(Student student, long groupId) {
        studentDao.addStudent(student, groupId);
    }

    @Override
    public Student getStudentById(long id) {
        return studentDao.getStudentById(id);
    }

    @Override
    public void update(long id, Student student) {
        studentDao.update(id, student);
    }

    @Override
    public void delete(Student student) {
        studentDao.delete(student);
    }

    @Override
    public List<Student> getStudentsByCompany(long companyId) {
        return studentDao.getStudentsByCompany(companyId);
    }

    public List<Student> getStudentsByTeacher(long teacherId){
        return studentDao.getStudentsByTeacherId(teacherId);
    }
}
