package crud_lms.services;

import crud_lms.models.Company;
import crud_lms.models.Course;
import crud_lms.repositories.CompanyRepository;
import crud_lms.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    public CourseService(CourseRepository courseRepository, CompanyRepository companyRepository){
        this.courseRepository = courseRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public void saveCourse(Course course,long companyId){

//        Company company = companyRepository.findById(companyId);
//        List<Company> companies = companyRepository.findAllCompanies();
//        Company company1 = companies.stream().filter(com -> com.getId() == companyId).findAny().orElse(null);
//        course.setCompany(company1);
//        company1.setCourse(course);

        Company company = companyRepository.findById(companyId);

        course.setCompany(company);
        company.setCourse(course);

        courseRepository.saveCourse(course);


    }

    public List<Course> findAllCourses(){
        return courseRepository.findAllCourses();
    }

    public Course show(long id){
        return courseRepository.show(id);
    }
    public void update (Course course, long courseId){
        courseRepository.updateCourse(course, courseId);
    }

    public void merge(Course course) {
        courseRepository.mergeCourse(course);
    }

    public Course findById(long id){
        return courseRepository.findById(id);
    }
    public void deleteById(long id){
        courseRepository.deleteById(id);
    }

    public List<Course> findByCompanyId(long companyId) {
        return courseRepository.findByCompanyId( companyId);
    }
}
