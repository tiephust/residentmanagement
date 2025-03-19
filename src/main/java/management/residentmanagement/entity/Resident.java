package management.residentmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Resident {
    @Id
    private Long id; //CCCD

    private String name;

    private String sex;

    private LocalDate dateOfBirth;

    private String job;

    private String email;

    private String phone;

    private String address;

    private String status;

    private LocalDateTime creatAt;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeclarationForm> declarationForms;
}
