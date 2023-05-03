package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.service.impl.CompanyServiceImpl;
import peaksoft.service.impl.CourseServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseServiceImpl courseService;
    private final CompanyServiceImpl companyService;
    @Autowired
    public CourseController(CourseServiceImpl courseService, CompanyServiceImpl companyService) {
        this.courseService = courseService;
        this.companyService = companyService;
    }
    @ModelAttribute("companyList")
    public List<Company> getCompanyList(){
        return companyService.getAllCompanies();
    }
    @GetMapping("/courses")
    public String getAllCourses(Model model){
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "course/courses";
    }
    @GetMapping("/addCourse")
    public String addCourse(Model model){
        model.addAttribute("course",new Course());
        return "course/saveCourse";
    }
    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course")Course course){
        courseService.addCourse(course.getCompanyId(), course);
        return "redirect:/course/courses";
    }
    @GetMapping("/courseUpdate/{id}")
    public String updateCourse(@PathVariable("id")Long id,Model model){
        Course course = courseService.getCourseById(id);
        model.addAttribute("updateCourse",course);
        return "course/coursesUpdate";
    }
    @PatchMapping("/{id}")
    public String saveCourseUpdate(@PathVariable("id")Long id,@ModelAttribute("updateCourse")Course course){
        courseService.updateCourse(course, id);
        return "redirect:/course/courses";
    }
    @PostMapping("/delete")
    public String deleteCourse(@RequestParam("id")Long id){
        Course course = courseService.getCourseById(id);
        courseService.deleteCourse(course);
        return "redirect:/course/courses";
    }
}
