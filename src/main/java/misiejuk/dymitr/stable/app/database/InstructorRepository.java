package misiejuk.dymitr.stable.app.database;

import misiejuk.dymitr.stable.app.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, String> {
}
