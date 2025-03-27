package management.core.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import management.core.entity.Resident;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResidentDto {
    private String name;

    private String Identifier;

    private String sex;

    private LocalDate dateOfBirth;

    private String job;

    @Email
    private String email;

    private String phone;

    private String address;
}
