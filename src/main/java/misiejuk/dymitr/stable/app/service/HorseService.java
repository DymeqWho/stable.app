package misiejuk.dymitr.stable.app.service;

import misiejuk.dymitr.stable.app.entities.*;
import misiejuk.dymitr.stable.app.database.HorseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class HorseService {

    private final HorseRepository horseRepository;

    public HorseService(HorseRepository horseRepository) {
        this.horseRepository = horseRepository;
    }

    public List<Horse> findAll() {
        return horseRepository.findAll();
    }

    public Horse save(Horse horse) {
        return horseRepository.save(horse);
    }

    public Horse update(String name, Horse updatedHorse) {

        Optional<Horse> oldHorse = horseRepository.findById(name);

        if (oldHorse.isPresent()) {
            Horse newHorse = new HorseBuilder()
                    .setName(name)
                    .setDescription(updatedHorse.getDescription())
                    .build();
            return horseRepository.save(newHorse);
        }

        return horseRepository.save(updatedHorse);
    }

    public Horse findById(String name) throws NoSuchElementException {
        Optional<Horse> optionalInstructor = horseRepository.findById(name);
        if (optionalInstructor.isPresent()) {
            Horse horse = optionalInstructor.get();
            return new HorseBuilder()
                    .setName(name)
                    .setDescription(horse.getDescription())
                    .build();
        } else throw new NoSuchElementException("Horse" + name + " do not exist!");
    }

    public void deleteHorse(String name) {
        horseRepository.deleteById(name);
    }
}
