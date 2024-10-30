package misiejuk.dymitr.stable.app.database;

import misiejuk.dymitr.stable.app.entities.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PairRepository extends JpaRepository<Pair, Long> {
}
