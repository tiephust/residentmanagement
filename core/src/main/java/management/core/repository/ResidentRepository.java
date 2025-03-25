package management.core.repository;

import management.core.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, String> {
    Resident findById(long id);
}
