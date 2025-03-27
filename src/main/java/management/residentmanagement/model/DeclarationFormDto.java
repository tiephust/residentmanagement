package management.residentmanagement.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DeclarationFormDto {
    private Long residentId;

    private String address;

    private String name;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private String reason;
}
