package management.residentmanagement.service;

import management.residentmanagement.entity.DeclarationForm;
import management.residentmanagement.model.DeclarationFormDto;
import org.springframework.stereotype.Service;

@Service
public class DeclarationFormService {
    public DeclarationForm toDeclarationForm(DeclarationFormDto declarationFormDto) {
        DeclarationForm declarationForm = new DeclarationForm();
        declarationForm.setAddress(declarationFormDto.getAddress());
        declarationForm.setName(declarationFormDto.getName());
        declarationForm.setDateFrom(declarationFormDto.getDateFrom());
        declarationForm.setDateTo(declarationFormDto.getDateTo());
        declarationForm.setReason(declarationFormDto.getReason());
        Long residentId = declarationFormDto.getResidentId();
        return declarationForm;
    }
}
