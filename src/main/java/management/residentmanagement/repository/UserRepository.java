package management.residentmanagement.repository;

import management.residentmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);

    User findByUserName(String username);

    User findBy
}
