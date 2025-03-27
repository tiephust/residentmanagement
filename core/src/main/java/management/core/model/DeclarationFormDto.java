package management.core.model;

import lombok.Getter;
import lombok.Setter;
import management.core.entity.DeclarationForm;
import management.core.entity.Resident;

import java.time.LocalDate;

@Getter
@Setter
public class DeclarationFormDto {
    private Long idResident;

    private String address;

    private String name;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private String reason;
}
