package management.residentmanagement.service;

import jakarta.annotation.PostConstruct;
import management.residentmanagement.entity.Resident;
import management.residentmanagement.model.ResidentDto;
import management.residentmanagement.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResidentService {
    @Autowired
    private ResidentRepository residentRepository;

    @PostConstruct
    public void init() {

    }

    public Optional<Resident> getResidentById(long id) {
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
        Optional<Resident> resident = residentRepository.findById(id);
        if (resident.isPresent()) {
            Resident residentUpdate = resident.get();
            residentUpdate.setName(residentDto.getName());
            residentUpdate.setIdentifier(residentDto.getIdentifier());
            residentUpdate.setSex(residentDto.getSex());
            residentUpdate.setDateOfBirth(residentDto.getDateOfBirth());
            residentUpdate.setJob(residentDto.getJob());
            residentUpdate.setEmail(residentDto.getEmail());
            residentUpdate.setPhone(residentDto.getPhone());
            residentUpdate.setAddress(residentDto.getAddress());
            residentRepository.save(residentUpdate);
        }
    }

    public void deleteResident(Long id) {
        residentRepository.deleteById(id);
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
