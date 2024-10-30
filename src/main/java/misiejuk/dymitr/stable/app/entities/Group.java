package misiejuk.dymitr.stable.app.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;


import java.util.List;

@Entity
@Table(name = "lesson_group")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class Group {

    @Id
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "Instructor_name", referencedColumnName = "name")
    private Instructor instructor;

    @ManyToMany
    @JoinTable(
            name = "group_pair",
            joinColumns = @JoinColumn(name = "group_name"),
            inverseJoinColumns = @JoinColumn(name = "pair_id")
    )
    private List<Pair> pairs;

    public Group() {
    }

    public Group(String name, String description, Instructor instructor, List<Pair> pairs) {
        this.name = name;
        this.description = description;
        this.instructor = instructor;
        this.pairs = pairs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public void setPairs(List<Pair> pairs) {
        this.pairs = pairs;
    }
}
