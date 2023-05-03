package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.TeacherDao;
import peaksoft.entities.Teacher;
import peaksoft.service.TeacherService;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherDao teacherDao;
    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherDao.getAllTeachers();
    }

    @Override
    public void addTeacher(Teacher teacher, Long courseId) {
        teacherDao.addTeacher(teacher, courseId);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherDao.getTeacherById(id);
    }

    @Override
    public void updateTeacher(Teacher teacher, Long id) {
        teacherDao.updateTeacher(teacher, id);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        teacherDao.deleteTeacher(teacher);
    }
}
