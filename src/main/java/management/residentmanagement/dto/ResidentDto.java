package management.residentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import management.residentmanagement.entity.Resident;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResidentDto {
    private Long residentId;

    private String Name;

    public ResidentDto residentToResidentDto(Resident resident) {
        ResidentDto residentDto = new ResidentDto();
        residentDto.setResidentId(resident.getId());
        residentDto.setName(resident.getName());
        return residentDto;
    }
}
