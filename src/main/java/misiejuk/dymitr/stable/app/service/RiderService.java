package misiejuk.dymitr.stable.app.service;

import misiejuk.dymitr.stable.app.entities.Rider;
import misiejuk.dymitr.stable.app.entities.RiderBuilder;
import misiejuk.dymitr.stable.app.database.RiderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RiderService {

    private final RiderRepository riderRepository;

    public RiderService(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    public List<Rider> findAll() {
        return riderRepository.findAll();
    }

    public Rider save(Rider rider) {
        return riderRepository.save(rider);
    }

    public Rider update(String name, Rider updatedRider) {

        Optional<Rider> oldRider = riderRepository.findById(name);

        if (oldRider.isPresent()) {
            Rider newRider = new RiderBuilder()
                    .setName(name)
                    .setDescription(updatedRider.getDescription())
                    .build();
            return riderRepository.save(newRider);
        }

        return riderRepository.save(updatedRider);
    }

    public Rider findById(String name) throws NoSuchElementException {
        Optional<Rider> optionalInstructor = riderRepository.findById(name);
        if (optionalInstructor.isPresent()) {
            Rider rider = optionalInstructor.get();
            return new RiderBuilder()
                    .setName(name)
                    .setDescription(rider.getDescription())
                    .build();
        } else throw new NoSuchElementException("Rider" + name + " do not exist!");
    }

    public void deleteRider(String name) {
        riderRepository.deleteById(name);
    }
}
