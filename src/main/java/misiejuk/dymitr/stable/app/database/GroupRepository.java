package misiejuk.dymitr.stable.app.database;

import misiejuk.dymitr.stable.app.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {
}
