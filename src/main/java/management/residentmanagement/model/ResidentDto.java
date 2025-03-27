package management.residentmanagement.model;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
