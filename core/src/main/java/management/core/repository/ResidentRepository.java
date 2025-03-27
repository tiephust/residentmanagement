package management.core.repository;

import management.core.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResidentRepository extends JpaRepository<Resident, String> {
    Resident findById(long id);

    // get Redident by phone from userdatabase

    List<Resident> findAll();

    // find resident by keyword in all fields
    @Query("SELECT r FROM Resident r WHERE " +
            "LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.Identifier) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.sex) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.phone) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.job) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.address) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.status) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Resident> search(@Param("keyword") String keyword);
}
