package peaksoft.service;

import peaksoft.entities.Course;
import peaksoft.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    void addStudent(Student student, long courseId);
    Student getStudentById(long id);
    void update(long id, Student student);
    void delete(Student student);
    public List<Student> getStudentsByCompany(long companyId);
}
