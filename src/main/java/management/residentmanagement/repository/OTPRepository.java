package management.residentmanagement.repository;

import management.residentmanagement.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTP, Long> {
    Optional<OTP> findByPhoneAndCode(String phoneNumber, String code);
}