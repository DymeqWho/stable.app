package misiejuk.dymitr.stable.app.database;

import misiejuk.dymitr.stable.app.entities.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorseRepository extends JpaRepository<Horse, String> {
}
