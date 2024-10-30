package misiejuk.dymitr.stable.app.controller;

import misiejuk.dymitr.stable.app.entities.Group;
import misiejuk.dymitr.stable.app.entities.GroupBuilder;
import misiejuk.dymitr.stable.app.service.GroupService;
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

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> findAll() {
        return groupService.findAll();
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        Group save = groupService.save(group);
        if (save.getInstructor().getName() == null)
            return new ResponseEntity<>(save, BAD_REQUEST);
        return ResponseEntity.ok(groupService.save(group));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Group> findById(@PathVariable String name) {
        String cause;
        try {
            Group group = groupService.findById(name);
            return ResponseEntity.ok(group);
        } catch (NoSuchElementException e) {
            cause = e.getMessage();
        }
        return new ResponseEntity<>(new GroupBuilder()
                .setName(name)
                .setDescription(cause)
                .build(), BAD_REQUEST);
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<Void> deleteGroup(@PathVariable String name) {
        groupService.deleteGroup(name);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/update/{name}")
    public ResponseEntity<Group> update(@PathVariable String name, @RequestBody Group group) {
        return ResponseEntity.ok(groupService.update(name, group));
    }
}
