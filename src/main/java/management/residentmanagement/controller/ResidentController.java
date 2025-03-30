package management.residentmanagement.controller;

import management.residentmanagement.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resident")
public class ResidentController {
    @Autowired
    private ResidentRepository residentRepository;

    @GetMapping("")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("Resident home");
    }

    @GetMapping("/profile")
    public ResponseEntity<String> profile(){
        return ResponseEntity.ok("Profile");
    }

    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(){
        return ResponseEntity.ok("Profile updated");
    }

}
