package management.core.repository;

import management.core.entity.DeclarationForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeclarationFormRepository extends JpaRepository<DeclarationForm, String> {
}
