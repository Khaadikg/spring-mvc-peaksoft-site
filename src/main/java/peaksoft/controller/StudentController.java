package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Group;
import peaksoft.entities.Student;
import peaksoft.service.impl.GroupServiceImpl;
import peaksoft.service.impl.StudentServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentServiceImpl studentService;
    private final GroupServiceImpl groupService;
    @Autowired
    public StudentController(StudentServiceImpl studentService, GroupServiceImpl groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }
    @ModelAttribute("groupList")
    public List<Group> getGroupList(){
        return groupService.getAllGroups();
    }
    @GetMapping("/students")
    public String getAllStudents(Model model){
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "student/students";
    }
    @GetMapping("/addStudent")
    public String addStudent(Model model){
        model.addAttribute("student", new Student());
        return "student/saveStudent";
    }
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student")Student student){
        studentService.addStudent(student, student.getGroupId());
        return "redirect:/student/students";
    }
    @GetMapping("/studentUpdate/{id}")
    public String updateStudent(@PathVariable("id")Long id,Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("updateStudent", student);
        return "student/studentUpdate";
    }
    @PatchMapping("/{id}")
    public String saveStudentUpdate(@PathVariable("id")Long id,@ModelAttribute("updateStudent")Student student){
        studentService.update(id, student);
        return "redirect:/student/students";
    }
    @PostMapping("/delete")
    public String deleteStudent(@RequestParam("id")Long id){
        Student student = studentService.getStudentById(id);
        studentService.delete(student);
        return "redirect:/student/students";
    }
}
