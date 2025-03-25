package management.core.service;

import jakarta.annotation.PostConstruct;
import management.core.repository.DeclarationFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeclarationFormService {
    @Autowired
    private DeclarationFormRepository declarationFormRepository;

    @PostConstruct
    public void initDeclarationForms() {
        // Khởi tạo biểu mẫu khai báo nếu chưa tồn tại
    }

    public void createDeclarationForm() {

    }
}
