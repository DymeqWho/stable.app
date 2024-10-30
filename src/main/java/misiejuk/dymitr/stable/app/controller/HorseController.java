package misiejuk.dymitr.stable.app.controller;

import misiejuk.dymitr.stable.app.entities.Horse;
import misiejuk.dymitr.stable.app.entities.HorseBuilder;
import misiejuk.dymitr.stable.app.service.HorseService;
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
@RequestMapping("/api/horses")
public class HorseController {

    private final HorseService horseService;

    public HorseController(HorseService horseService) {
        this.horseService = horseService;
    }

    @GetMapping
    public List<Horse> findAll() {
        return horseService.findAll();
    }

    @PostMapping
    public ResponseEntity<Horse> createHorse(@RequestBody Horse horse) {
        return ResponseEntity.ok(horseService.save(horse));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Horse> findById(@PathVariable String name) {
        String cause;
        try {
            return new ResponseEntity<>(horseService.findById(name), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            cause = e.getMessage();
        }
        return new ResponseEntity<>(new HorseBuilder()
                .setName(name)
                .setDescription(cause)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<Void> deleteHorse(@PathVariable String name) {
        horseService.deleteHorse(name);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/update/{name}")
    public ResponseEntity<Horse> update(@PathVariable String name, @RequestBody Horse horse) {
        return ResponseEntity.ok(horseService.update(name, horse));
    }
}
