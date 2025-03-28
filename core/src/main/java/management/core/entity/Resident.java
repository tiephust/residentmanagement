package management.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import management.apiauth.entity.User;
import management.core.model.ResidentDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String Identifier; // CMND/CCCD

    private String sex;

    private LocalDate dateOfBirth;

    private String email;

    private String phone;

    private String job;

    private String address;

    private LocalDateTime creatAt;

    private LocalDateTime updateAt;

    private LocalDateTime deleteAt;

    private Long userId;

    private Status status;


    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeclarationForm> declarationForms;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public enum Status {
        CREATED,
        UPDATED,
//        ACTIVE,
//        INACTIVE,
        DELETED
    }
}
