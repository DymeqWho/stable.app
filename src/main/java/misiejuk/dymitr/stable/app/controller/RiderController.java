package misiejuk.dymitr.stable.app.controller;

import misiejuk.dymitr.stable.app.entities.Rider;
import misiejuk.dymitr.stable.app.entities.RiderBuilder;
import misiejuk.dymitr.stable.app.service.RiderService;
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
@RequestMapping("/api/riders")
public class RiderController {
    private final RiderService riderService;

    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @GetMapping
    public List<Rider> findAll() {
        return riderService.findAll();
    }

    @PostMapping
    public ResponseEntity<Rider> createRider(@RequestBody Rider rider) {
        return ResponseEntity.ok(riderService.save(rider));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Rider> findById(@PathVariable String name) {
        String cause;
        try {
            return new ResponseEntity<>(riderService.findById(name), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            cause = e.getMessage();
        }
        return new ResponseEntity<>(new RiderBuilder()
                .setName(name)
                .setDescription(cause)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<Void> deleteRider(@PathVariable String name) {
        riderService.deleteRider(name);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/update/{name}")
    public ResponseEntity<Rider> update(@PathVariable String name, @RequestBody Rider rider) {
        return ResponseEntity.ok(riderService.update(name, rider));
    }
}
