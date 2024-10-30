package misiejuk.dymitr.stable.app.entities;

import java.util.List;

import static java.util.Collections.emptyList;

public final class GroupBuilder {
    private String name;
    private String description;
    private Instructor instructor;
    private List<Pair> pairs = emptyList();


    public GroupBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public GroupBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public GroupBuilder setInstructor(Instructor instructor) {
        this.instructor = instructor;
        return this;
    }

    public GroupBuilder setPairs(List<Pair> pairs) {
        this.pairs = pairs;
        return this;
    }

    public Group build() {
        return new Group(name, description, instructor, pairs);
    }
}
