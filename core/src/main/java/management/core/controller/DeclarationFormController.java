package management.core.controller;

import management.core.entity.Resident;
import management.core.model.DeclarationFormDto;
import management.core.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/declaration-form")
public class DeclarationFormController {
    @Autowired
    private ResidentRepository residentRepository;

    @GetMapping("")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("Notification home");
    }

    @GetMapping("/create")
    public ResponseEntity<String> create(){
        return ResponseEntity.ok("DeclarationForm created");
    }

    @PostMapping("/create/{idDeclarationForm}")
    public ResponseEntity<String> create(@PathVariable String id){
        return ResponseEntity.ok("DeclarationForm created");
    }

    @GetMapping("/get/{idDeclarationForm}")
    public ResponseEntity<String> get(){
        return ResponseEntity.ok("DeclarationForm retrieved");
    }

    @GetMapping("/getAll")
    public ResponseEntity<String> getAll(){
        return ResponseEntity.ok("All DeclarationForms retrieved");
    }

    @PutMapping("/update/{idDeclarationForm}")
    public ResponseEntity<String> updatet(){
        return ResponseEntity.ok("DeclarationForm updated");
    }

    @DeleteMapping("/delete/{idDeclarationForm}")
    public ResponseEntity<String> delete(){
        return ResponseEntity.ok("DeclarationForm deleted");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteLists(@RequestBody List<String> listDeclarationForms){
        return ResponseEntity.ok("List of DeclarationForms deleted");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll(){
        return ResponseEntity.ok("All DeclarationForms deleted");
    }

    // Reply
    @GetMapping("/reply")
    public ResponseEntity<String> reply(){
        return ResponseEntity.ok("Reply created");
    }

    @PostMapping("/reply/{idDeclarationForm}")
    public ResponseEntity<String> reply(@PathVariable String id){
        return ResponseEntity.ok("Reply created");
    }
}
