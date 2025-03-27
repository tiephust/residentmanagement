package management.core.service;

import jakarta.annotation.PostConstruct;
import management.core.entity.DeclarationForm;
import management.core.entity.Resident;
import management.core.model.DeclarationFormDto;
import management.core.repository.DeclarationFormRepository;
import management.core.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeclarationFormService {
    @Autowired
    private DeclarationFormRepository declarationFormRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @PostConstruct
    public void initDeclarationForms() {
    }

    public void createDeclarationForm(DeclarationFormDto declarationFormDto) {

    }

    public DeclarationForm toDeclarationForm(DeclarationFormDto declarationFormDto) {
        DeclarationForm declarationForm = new DeclarationForm();
        declarationForm.setAddress(declarationFormDto.getAddress());
        declarationForm.setName(declarationFormDto.getName());
        declarationForm.setDateFrom(declarationFormDto.getDateFrom());
        declarationForm.setDateTo(declarationFormDto.getDateTo());
        declarationForm.setReason(declarationFormDto.getReason());
        declarationForm.setResident(residentRepository.findById(declarationFormDto.getIdResident()));
        return declarationForm;
    }
}
