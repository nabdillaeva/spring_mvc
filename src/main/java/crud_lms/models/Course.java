package crud_lms.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@Getter@Setter
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String courseName;

    private String duration;

    @ManyToOne(cascade = CascadeType.ALL)
    private Company company;

    @ManyToMany(mappedBy = "courses")
    private List<Group> groups;

    @OneToOne(mappedBy = "course")
    private Teacher teacher;

}
