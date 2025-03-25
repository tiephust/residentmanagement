package management.core.controller;

import management.core.entity.Resident;
import management.core.model.DeclarationFormDto;
import management.core.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/declaration-form")
public class DeclarationFormController {
    @Autowired
    private ResidentRepository residentRepository;

    @GetMapping("")
    public ResponseEntity<?> getDeclarationForm(@RequestParam Long idResident, String typeForm) {
        Resident resident = residentRepository.findById(idResident);
        if (resident == null) {
            return ResponseEntity.status(404).body("Resident not found");
        }

        // 2. Tạo response object chứa cả resident và typeForm
        Map<String, Object> response = new HashMap<>();
        response.put("resident", resident);
        response.put("typeForm", typeForm);

        // 3. Trả về JSON
        return ResponseEntity.ok(response);
    }

    @PostMapping("submit")
    public String submitDeclarationForm(@RequestBody DeclarationFormDto dto) {
        return "submitted";
    }
}
