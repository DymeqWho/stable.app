package misiejuk.dymitr.stable.app.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "instructor")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class Instructor {
    @Id
    private String name;
    private String description;
    @OneToMany(mappedBy = "instructor", cascade = ALL)
    private List<Group> groups;

    public Instructor() {
    }

    public Instructor(String name, String description, List<Group> groups) {
        this.name = name;
        this.description = description;
        this.groups = groups;
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

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
