package crud_lms.repositories;

import crud_lms.models.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;


@Repository
public class CourseRepository {

    private EntityManager entityManager;

    public CourseRepository(EntityManagerFactory entityManagerFactory){
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void saveCourse(Course course){

        entityManager.getTransaction().begin();

        entityManager.persist(course);

        entityManager.getTransaction().commit();
    }

    List<Course> courses =new ArrayList<>();

    public List<Course> findAllCourses(){

        entityManager.getTransaction().begin();

        courses = entityManager.createQuery("select c from Course c", Course.class)
                .getResultList();

        entityManager.getTransaction().commit();

        return courses;

    }

    public Course show(long id) {
        return courses.stream().filter(course -> course.getId() == id).findAny().orElse(null);

    }

    public void updateCourse(Course course,long courseId){

        entityManager.getTransaction().begin();

        // Company company1 = entityManager.find(Company.class, companyId);
        Course course1 = show(courseId);

        course1.setCourseName(course.getCourseName());

        course1.setDuration(course.getDuration());

        entityManager.getTransaction().commit();

    }

    public void mergeCourse(Course company){

        entityManager.getTransaction().begin();

        entityManager.merge(company);

        entityManager.getTransaction().commit();
    }

    public Course findById(long id){

        entityManager.getTransaction().begin();

        Course course = entityManager.find(Course.class, id);

        entityManager.getTransaction().commit();

        return course;
    }

    public void deleteById(long courseId){

        entityManager.getTransaction().begin();

        entityManager.remove(entityManager.find(Course.class,courseId));

        entityManager.getTransaction().commit();
    }

    public List<Course> findByCompanyId(long companyId) {
        return entityManager.createQuery("select cc from Course cc where cc.company = (select c from Company c where c.id=?1)",Course.class)
                .setParameter(1,companyId)
                .getResultList();
    }
}
