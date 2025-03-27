package management.core.service;

import management.apiauth.service.UserService;
import management.core.entity.Resident;
import management.core.model.ResidentDto;
import management.core.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResidentService {
    @Autowired
    private ResidentRepository residentRepository;

    public Resident getResidentById(long id) {
        return residentRepository.findById(id);
    }

    public void createResident(ResidentDto residentDto) {
        Resident resident = toResident(residentDto);
        LocalDateTime creatAt = LocalDateTime.now();
        resident.setStatus(Resident.Status.CREATED);
        resident.setCreatAt(creatAt);
        residentRepository.save(toResident(residentDto));
    }

    public void updateResident(Long id, ResidentDto residentDto) {
        Resident resident = residentRepository.findById(id);

        residentRepository.save(resident);
    }

    public void deleteResident(Long id) {
        Resident resident = residentRepository.findById(id);
        residentRepository.delete(resident);
    }

    public List<Resident> findAllResident() {
        return residentRepository.findAll();
    }

    public List<Resident> search(String keyword) {
        return residentRepository.search(keyword);
    }

    public List<ResidentDto> toListResidentDto(List<Resident> residents) {
        List<ResidentDto> residentDtos = new ArrayList<ResidentDto>();
        for (Resident resident : residents) {
            ResidentDto residentDto = toResidentDto(resident);
            residentDtos.add(residentDto);
        }
        return residentDtos;
    }

    public ResidentDto toResidentDto(Resident resident) {
        ResidentDto residentDto = new ResidentDto();
        residentDto.setName(resident.getName());
        residentDto.setIdentifier(resident.getIdentifier());
        residentDto.setSex(resident.getSex());
        residentDto.setDateOfBirth(resident.getDateOfBirth());
        residentDto.setJob(resident.getJob());
        residentDto.setEmail(resident.getEmail());
        residentDto.setPhone(resident.getPhone());
        residentDto.setAddress(resident.getAddress());
        return residentDto;
    }

    public Resident toResident(ResidentDto residentDto) {
        Resident resident = new Resident();
        resident.setName(residentDto.getName());
        resident.setIdentifier(residentDto.getIdentifier());
        resident.setSex(residentDto.getSex());
        resident.setDateOfBirth(residentDto.getDateOfBirth());
        resident.setJob(residentDto.getJob());
        resident.setEmail(residentDto.getEmail());
        resident.setPhone(residentDto.getPhone());
        resident.setAddress(residentDto.getAddress());
        return resident;
    }
}
