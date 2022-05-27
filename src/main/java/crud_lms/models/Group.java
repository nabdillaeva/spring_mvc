package crud_lms.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@Getter@Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String groupName;

    private LocalDate  dateOfStart;

    private LocalDate dateOfFinish;

    @ManyToMany
    private List<Course> courses;

    @OneToMany(mappedBy = "group")
    private List<Student> students;


    public Group(String groupName, LocalDate dateOfStart, LocalDate dateOfFinish) {
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
    }
}
