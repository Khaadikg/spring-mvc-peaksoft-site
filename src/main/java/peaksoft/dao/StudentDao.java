package peaksoft.dao;

import peaksoft.entities.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getAllStudents();
    void addStudent(Student student, long id);
    Student getStudentById(long id);
    void update(long id, Student student);
    void delete(Student student);

    List<Student> getStudentsByTeacherId(long id);

    List<Student> getStudentsByCompany(long companyId);
}
