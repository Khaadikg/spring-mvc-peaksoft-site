package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.entities.Student;
import peaksoft.service.StudentService;
import peaksoft.service.impl.CompanyServiceImpl;
import peaksoft.service.impl.CourseServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private final CompanyServiceImpl companyService;
    private final CourseServiceImpl courseService;
    private final StudentService studentService;
    @Autowired
    public CompanyController(CompanyServiceImpl companyService, CourseServiceImpl courseService, StudentService studentService) {
        this.companyService = companyService;
        this.courseService = courseService;
        this.studentService = studentService;
    }
    @GetMapping("/companies")
    public String getAllCompanies(Model model){
        List<Company> companies = companyService.getAllCompanies();
        model.addAttribute("companies", companies);
        return "company/companies";
    }
    @GetMapping("/addCompany")
    public String addCompany(Model model){
        model.addAttribute("company",new Company());
        return "company/saveCompany";
    }
    @PostMapping("/saveCompany")
    public String saveCompany(@ModelAttribute("company")Company company){
        companyService.addCompany(company);
        return "redirect:/company/companies";
    }
    @GetMapping("/companyUpdate/{id}")
    public String updateCompany(@PathVariable("id")Long id,Model model){
        Company company = companyService.getCompanyById(id);
        model.addAttribute("updateCompany",company);
        return "company/companyUpdate";
    }
    @PatchMapping("/{id}")
    public String saveCompanyUpdate(@PathVariable("id")Long id,@ModelAttribute("updateCompany")Company company){
        companyService.updateCompany(id, company);
        return "redirect:/company/companies";
    }
    @PostMapping("/delete")
    public String deleteCompany(@RequestParam("id")Long id){
        Company company = companyService.getCompanyById(id);
        companyService.deleteCompany(company);
        return "redirect:/company/companies";
    }

    @GetMapping("/courses/{companyId}")
    public String getCoursesByCompanyId(@PathVariable("companyId")long companyId, Model model){
        List<Course> courseList = courseService.getCoursesByCompany(companyId);
        List<Student> studentsList = studentService.getStudentsByCompany(companyId);
        model.addAttribute("countStudent", studentsList.size());
        model.addAttribute("coursesList", courseList);
        model.addAttribute("count", courseList.size());
        return "company/courses";
    }
    @GetMapping("search")
    public String getCourseByName(Model model, String name) {
        List<Course> courseList = courseService.getCoursesByName(name);
        model.addAttribute("courses", courseList);
        model.addAttribute("count", courseList.size());
        return "company/getCourses";
    }
//    public String getStudentsByCompany(@PathVariable("companyId")long companyId, Model model) {
//        List<Student> studentsList = studentService.getStudentsByCompany(companyId);
//        model.addAttribute("count", studentsList.size());
//        return "company/getCourses";
//    }
//    @ModelAttribute("companyList")
//    public List<Company> getCompanyList(){
//        return companyService.getAllCompanies();
//    }
}
