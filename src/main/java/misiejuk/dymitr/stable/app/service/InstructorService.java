package misiejuk.dymitr.stable.app.service;

import misiejuk.dymitr.stable.app.entities.GroupBuilder;
import misiejuk.dymitr.stable.app.entities.Instructor;
import misiejuk.dymitr.stable.app.database.InstructorRepository;
import misiejuk.dymitr.stable.app.entities.InstructorBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;


@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    public Instructor save(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public Instructor update(String name, Instructor updatedInstructor) {

        Optional<Instructor> oldInstructor = instructorRepository.findById(name);

        if (oldInstructor.isPresent()) {
            Instructor newInstructor = new InstructorBuilder()
                    .setName(name)
                    .setDescription(updatedInstructor.getDescription())
                    .setGroups(updatedInstructor.getGroups())
                    .build();
            return instructorRepository.save(newInstructor);
        }

        return instructorRepository.save(updatedInstructor);
    }

    public Instructor findById(String name) throws NoSuchElementException {
        Optional<Instructor> optionalInstructor = instructorRepository.findById(name);
        if (optionalInstructor.isPresent()) {
            Instructor instructor = optionalInstructor.get();
            return new InstructorBuilder()
                    .setName(name)
                    .setDescription(instructor.getDescription())
                    .setGroups(instructor.getGroups().stream()
                            .map(group -> new GroupBuilder()
                                    .setName(group.getName())
                                    .build()
                            ).toList()
                    ).build();
        } else throw new NoSuchElementException("Instructor" + name + " do not exist!");

    }

    public void deleteInstructor(String name) {
        instructorRepository.deleteById(name);
    }
}
