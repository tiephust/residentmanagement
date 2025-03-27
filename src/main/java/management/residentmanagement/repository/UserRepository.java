package management.residentmanagement.repository;

import management.residentmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(long id);

    Optional<User> findByUsername(String username);
}
