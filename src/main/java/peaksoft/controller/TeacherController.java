package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Course;
import peaksoft.entities.Student;
import peaksoft.entities.Teacher;
import peaksoft.service.impl.CourseServiceImpl;
import peaksoft.service.impl.StudentServiceImpl;
import peaksoft.service.impl.TeacherServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherServiceImpl teacherService;
    private final CourseServiceImpl courseService;

    private final StudentServiceImpl studentService;
    @Autowired
    public TeacherController(TeacherServiceImpl teacherService, CourseServiceImpl courseService, StudentServiceImpl studentService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @ModelAttribute("courseList")
    public List<Course> getCourseList(){
        return courseService.getAllCourses();
    }
    @GetMapping("/teachers")
    public String getAllTeachers(Model model){
        List<Teacher> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teacher/teachers";
    }
    @GetMapping("/addTeacher")
    public String addTeacher(Model model){
        model.addAttribute("teacher",new Teacher());
        return "teacher/saveTeacher";
    }
    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("teacher")Teacher teacher){
        teacherService.addTeacher(teacher, teacher.getCourseId());
        return "redirect:/teacher/teachers";
    }
    @GetMapping("/teacherUpdate/{id}")
    public String updateTeacher(@PathVariable("id")Long id,Model model){
        Teacher teacher = teacherService.getTeacherById(id);
        model.addAttribute("updateTeacher", teacher);
        return "teacher/teacherUpdate";
    }
    @PatchMapping("/{id}")
    public String saveTeacherUpdate(@PathVariable("id")Long id,@ModelAttribute("updateTeacher")Teacher teacher){
        teacherService.updateTeacher(teacher, id);
        return "redirect:/teacher/teachers";
    }
    @PostMapping("/delete")
    public String deleteTeacher(@RequestParam("id")Long id){
        Teacher teacher = teacherService.getTeacherById(id);
        teacherService.deleteTeacher(teacher);
        return "redirect:/teacher/teachers";
    }
    @GetMapping("/students/{teacherId}")
    public String getStudentsByTeacher(Model model, @PathVariable("teacherId") Long teacherId) {
        List<Student> students = studentService.getStudentsByTeacher(teacherId);
        model.addAttribute("size", students.size());
        model.addAttribute("students", students);
        return "teacher/students";
    }
}
