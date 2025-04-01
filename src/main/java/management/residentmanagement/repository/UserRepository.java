package management.residentmanagement.repository;

import management.residentmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(long id);

    List<User> findByRole(User.Role role);

    Optional<User> findByUsername(String username);

    Optional<User> findByRefreshToken(String refreshToken);
}
