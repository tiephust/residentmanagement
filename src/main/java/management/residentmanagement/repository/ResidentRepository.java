package management.residentmanagement.repository;

import management.residentmanagement.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, String> {

}
