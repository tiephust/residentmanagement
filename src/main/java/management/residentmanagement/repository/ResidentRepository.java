package management.residentmanagement.repository;

import management.residentmanagement.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Long>{
    Optional<Resident> findById(long id);

    // find all residents return list
    List<Resident> findAll();

    // find resident by keyword in all fields
    @Query("SELECT r FROM Resident r WHERE " +
            "LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.Identifier) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.phone) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.job) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.address) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Resident> search(@Param("keyword") String keyword);
}
