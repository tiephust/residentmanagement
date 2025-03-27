package management.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    @GetMapping("")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("Fee home");
    }

    @GetMapping("/create")
    public ResponseEntity<String> create(){
        return ResponseEntity.ok("Report created");
    }

    @PostMapping("/create/{idReport}")
    public ResponseEntity<String> create(@PathVariable String id){
        return ResponseEntity.ok("Report created");
    }

    @GetMapping("/get/{idReport}")
    public ResponseEntity<String> get(){
        return ResponseEntity.ok("Report retrieved");
    }

    @GetMapping("/getAll")
    public ResponseEntity<String> getAll(){
        return ResponseEntity.ok("All reports retrieved");
    }

    @PutMapping("/update/{idReport}")
    public ResponseEntity<String> updatet(){
        return ResponseEntity.ok("Report updated");
    }

    @DeleteMapping("/delete/{idReport}")
    public ResponseEntity<String> delete(){
        return ResponseEntity.ok("Report deleted");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteLists(@RequestBody List<String> listReports){
        return ResponseEntity.ok("List of reports deleted");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll(){
        return ResponseEntity.ok("All reports deleted");
    }

    // Reply
    @GetMapping("/reply")
    public ResponseEntity<String> reply(){
        return ResponseEntity.ok("Reply created");
    }

    @PostMapping("/reply/{idRePort}")
    public ResponseEntity<String> reply(@PathVariable String id){
        return ResponseEntity.ok("Reply created");
    }
}
