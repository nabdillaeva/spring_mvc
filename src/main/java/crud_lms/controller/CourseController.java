package crud_lms.controller;

import crud_lms.models.Course;
import crud_lms.services.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/courses")
public class CourseController {

  private final CourseService courseService;

   public CourseController(CourseService courseService){
       this.courseService = courseService;
   }

    @ModelAttribute("courseList")
    public List<Course> findAllCourses(){
        return courseService.findAllCourses();

    }
    @GetMapping("/find/by/{companyId}")
    public String findAllCoursesByCompanyId(@PathVariable long companyId, Model model){

       List<Course> courses = courseService.findByCompanyId(companyId);

       model.addAttribute("courses",courses);
       model.addAttribute("companyId",companyId);

       return "course/allCourses";
    }

    @GetMapping("/save/{companyId}")
    public String saveCoursePage(@PathVariable long companyId, Model model){

        model.addAttribute("companyId",companyId);
        model.addAttribute("emptyCourse", new Course());

        return "course/saveCoursePage";
    }

    @PostMapping("/save/{companyId}")
    public String saveCourse(Course course,@PathVariable long companyId){

        courseService.saveCourse(course,companyId);

        return "redirect:/api/courses/find/by/" + companyId;
    }




    @GetMapping("/{id}/update")
    public String updateCourse(Model model, @PathVariable("id") long id){

        model.addAttribute("courseUpdate",courseService.show(id));

        return "course/update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("courseUpdate") Course course,
                         @PathVariable("id") long id) {

        courseService.update(course,id);
        return "redirect:/api/courses";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        courseService.deleteById(id);
        return "redirect:/api/courses";
    }
}
