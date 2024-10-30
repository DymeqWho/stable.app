package misiejuk.dymitr.stable.app.entities;

import java.util.List;

import static java.util.Collections.emptyList;

public final class InstructorBuilder {
    private String name;
    private String description;
    private List<Group> groups = emptyList();

    public InstructorBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public InstructorBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public InstructorBuilder setGroups(List<Group> groups) {
        this.groups = groups;
        return this;
    }

    public Instructor build() {
        return new Instructor(name, description, groups);
    }


}
