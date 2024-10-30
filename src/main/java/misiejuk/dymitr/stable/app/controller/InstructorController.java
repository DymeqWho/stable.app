package misiejuk.dymitr.stable.app.controller;

import misiejuk.dymitr.stable.app.entities.Instructor;
import misiejuk.dymitr.stable.app.entities.InstructorBuilder;
import misiejuk.dymitr.stable.app.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public List<Instructor> findAll() {
        return instructorService.findAll();
    }

    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor) {
        return ResponseEntity.ok(instructorService.save(instructor));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Instructor> findById(@PathVariable String name) {
        String cause;
        try {
            return new ResponseEntity<>(instructorService.findById(name), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            cause = e.getMessage();
        }
        return new ResponseEntity<>(new InstructorBuilder()
                .setName(name)
                .setDescription(cause)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable String name) {
        instructorService.deleteInstructor(name);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/update/{name}")
    public ResponseEntity<Instructor> update(@PathVariable String name, @RequestBody Instructor instructor
    ) {
        return ResponseEntity.ok(instructorService.update(name, instructor));
    }
}


