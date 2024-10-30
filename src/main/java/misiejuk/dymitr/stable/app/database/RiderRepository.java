package misiejuk.dymitr.stable.app.database;

import misiejuk.dymitr.stable.app.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, String> {
}
