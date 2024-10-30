package misiejuk.dymitr.stable.app.controller;

import misiejuk.dymitr.stable.app.entities.Pair;
import misiejuk.dymitr.stable.app.entities.PairBuilder;
import misiejuk.dymitr.stable.app.service.PairService;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/pairs")
public class PairController {
    private final PairService pairService;

    public PairController(PairService pairService) {
        this.pairService = pairService;
    }

    @GetMapping
    public List<Pair> findAll() {
        return pairService.findAll();
    }

    @PostMapping
    public ResponseEntity<Pair> createPair(@RequestBody Pair pair) {
        String cause;
        try {
            return ResponseEntity.ok(pairService.save(pair));
        } catch (NoSuchElementException e) {
            cause = e.getMessage();
        }
        return new ResponseEntity<>(new PairBuilder().setDescription(cause).build(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pair> findById(@PathVariable Long id) {
        Optional<Pair> pairOptional = pairService.findById(id);
        return pairOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePair(@PathVariable Long id) {
        pairService.deletePair(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Pair> update(@PathVariable Long id, @RequestBody Pair pair) {
        String cause;
        try {
            return ResponseEntity.ok(pairService.update(id, pair));
        } catch (NoSuchElementException e) {
            cause = e.getMessage();
        }
        return new ResponseEntity<>(new PairBuilder().setDescription(cause).build(), HttpStatus.BAD_REQUEST);
    }
}
