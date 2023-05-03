package peaksoft.dao;

import peaksoft.entities.Course;
import peaksoft.entities.Teacher;

import java.util.List;

public interface TeacherDao {

    List<Teacher> getAllTeachers();

    void addTeacher(Teacher teacher,Long courseId);

    Teacher getTeacherById(Long id);

    void updateTeacher(Teacher teacher, Long id);

    void deleteTeacher(Teacher teacher);
}
