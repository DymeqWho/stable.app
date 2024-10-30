package misiejuk.dymitr.stable.app.service;

import misiejuk.dymitr.stable.app.entities.Group;
import misiejuk.dymitr.stable.app.entities.GroupBuilder;
import misiejuk.dymitr.stable.app.entities.InstructorBuilder;
import misiejuk.dymitr.stable.app.database.GroupRepository;
import misiejuk.dymitr.stable.app.database.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final InstructorRepository instructorRepository;

    public GroupService(GroupRepository groupRepository, InstructorRepository instructorRepository) {
        this.groupRepository = groupRepository;
        this.instructorRepository = instructorRepository;
    }

    public List<Group> findAll() {
        return groupRepository.findAll().stream()
                .map(group -> new GroupBuilder()
                        .setName(group.getName())
                        .setDescription(group.getDescription())
                        .setInstructor(new InstructorBuilder()
                                .setName(group.getInstructor().getName())
                                .build())
                        .setPairs(group.getPairs())
                        .build())
                .toList();
    }

    public Group save(Group group) {
        if (instructorRepository.findById(group.getInstructor().getName()).isPresent())
            return groupRepository.save(new GroupBuilder()
                    .setName(group.getName())
                    .setDescription(group.getDescription())
                    .setInstructor(new InstructorBuilder()
                            .setName(group.getInstructor()
                                    .getName())
                            .build())
                    .setPairs(group.getPairs())
                    .build());
        else
            return new GroupBuilder()
                    .setName(group.getName())
                    .setDescription("Instructor " + group.getInstructor().getName() +
                            " do not exist! You must link the group to an existing instructor!")
                    .setInstructor(new InstructorBuilder()
                            .build())
                    .build();
    }

    public Group update(String name, Group updatedGroup) {

        Optional<Group> oldGroup = groupRepository.findById(name);

        if (oldGroup.isPresent()) {
            Group newGroup = new GroupBuilder()
                    .setName(name)
                    .setDescription(updatedGroup.getDescription())
                    .setInstructor(new InstructorBuilder()
                            .setName(updatedGroup.getInstructor()
                                    .getName())
                            .build())
                    .setPairs(updatedGroup.getPairs())
                    .build();
            return groupRepository.save(newGroup);
        }

        return groupRepository.save(updatedGroup);
    }

    public Group findById(String name) throws NoSuchElementException {
        Optional<Group> optionalGroup = groupRepository.findById(name);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            return new GroupBuilder()
                    .setName(name)
                    .setDescription(group.getDescription())
                    .setInstructor(new InstructorBuilder()
                            .setName(group.getInstructor()
                                    .getName())
                            .build())
                    .setPairs(group.getPairs())
                    .build();
        } else {
            throw new NoSuchElementException("Group " + name + " do not exist!");
        }
    }

    public void deleteGroup(String name) {
        groupRepository.deleteById(name);
    }
}
