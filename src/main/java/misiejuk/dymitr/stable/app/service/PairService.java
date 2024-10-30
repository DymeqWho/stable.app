package misiejuk.dymitr.stable.app.service;

import misiejuk.dymitr.stable.app.entities.Horse;
import misiejuk.dymitr.stable.app.entities.Pair;
import misiejuk.dymitr.stable.app.entities.PairBuilder;
import misiejuk.dymitr.stable.app.entities.Rider;
import misiejuk.dymitr.stable.app.database.HorseRepository;
import misiejuk.dymitr.stable.app.database.PairRepository;
import misiejuk.dymitr.stable.app.database.RiderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PairService {

    private final PairRepository pairRepository;
    private final HorseRepository horseRepository;

    private final RiderRepository riderRepository;

    public PairService(PairRepository pairRepository, HorseRepository horseRepository, RiderRepository riderRepository) {
        this.pairRepository = pairRepository;
        this.horseRepository = horseRepository;
        this.riderRepository = riderRepository;
    }

    public List<Pair> findAll() {
        return pairRepository.findAll();
    }

    public Pair save(Pair pair) throws NoSuchElementException {
        Pair veryficatedPair = pairVerificator(pair);
        return pairRepository.save(new PairBuilder()
                .setDescription(pair.getDescription())
                .setHorse(pair.getHorse())
                .setRider(pair.getRider())
                .build());
    }

    public Pair update(Long id, Pair updatedPair) throws NoSuchElementException {

        Optional<Pair> oldPairOptional = pairRepository.findById(id);
        pairVerificator(updatedPair);

        if (oldPairOptional.isPresent()) {
            Pair newPair = new PairBuilder()
                    .setId(id)
                    .setDescription(updatedPair.getDescription())
                    .setHorse(updatedPair.getHorse())
                    .setRider(updatedPair.getRider())
                    .build();
            return pairRepository.save(newPair);
        }

        return pairRepository.save(updatedPair);
    }

    public Optional<Pair> findById(Long id) throws NoSuchElementException {
        return pairRepository.findById(id);
    }

    public void deletePair(Long id) {
        pairRepository.deleteById(id);
    }

    private Pair pairVerificator(Pair pair) throws NoSuchElementException {

        Pair verificatedPair = new Pair();
        Rider rider = pair.getRider();
        Horse horse = pair.getHorse();

        boolean isHorsePresent = isHorsePresent(pair);
        boolean isRiderPresent = isRiderPresent(pair);

        if (isHorsePresent) {
            verificatedPair.setHorse(horse);
        } else throw new NoSuchElementException("Horse: " + horse.getName() + " not found!");

        if (isRiderPresent) {
            verificatedPair.setRider(rider);
        } else throw new NoSuchElementException("Rider: " + rider.getName() + " not found!");

        return verificatedPair;
    }

    private boolean isRiderPresent(Pair updatedPair) {
        return riderRepository.findById(updatedPair.getRider().getName()).isPresent();
    }

    private boolean isHorsePresent(Pair updatedPair) {
        return horseRepository.findById(updatedPair.getHorse().getName()).isPresent();
    }
}
