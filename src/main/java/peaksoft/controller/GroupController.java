package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.service.impl.CourseServiceImpl;
import peaksoft.service.impl.GroupServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupController {
    private final GroupServiceImpl groupService;
    private final CourseServiceImpl courseService;
    @Autowired
    public GroupController(GroupServiceImpl groupService, CourseServiceImpl courseService) {
        this.groupService = groupService;
        this.courseService = courseService;
    }
    @ModelAttribute("courseList")
    public List<Course> getCompanyList(){
        return courseService.getAllCourses();
    }
    @GetMapping("/groups")
    public String getAllGroups(Model model){
        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "group/groups";
    }
    @GetMapping("/addGroup")
    public String addGroup(Model model){
        model.addAttribute("group", new Group());
        return "group/saveGroup";
    }
    @PostMapping("/saveGroup")
    public String saveGroup(@ModelAttribute("group")Group group){
        groupService.addGroup(group.getCourseId(), group);
        return "redirect:/group/groups";
    }
    @GetMapping("/groupUpdate/{id}")
    public String updateGroup(@PathVariable("id")Long id, Model model){
        Group group = groupService.getGroupById(id);
        model.addAttribute("updateGroup", group);
        return "group/groupUpdate";
    }
    @PatchMapping("/{id}")
    public String saveGroupUpdate(@PathVariable("id")Long id,@ModelAttribute("updateGroup")Group group){
        groupService.update(id, group);
        return "redirect:/group/groups";
    }
    @PostMapping("/delete")
    public String deleteGroup(@RequestParam("id")Long id){
        Group group = groupService.getGroupById(id);
        groupService.delete(group);
        return "redirect:/group/groups";
    }
}
