package management.residentmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fee")
public class FeeController {
    @GetMapping("")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("Fee home");
    }

    @GetMapping("/create")
    public ResponseEntity<String> create(){
        return ResponseEntity.ok("Fee created");
    }

    @PostMapping("/create/{idFee}")
    public ResponseEntity<String> create(@PathVariable String id){
        return ResponseEntity.ok("Fee created");
    }

    @GetMapping("/get/{idFee}")
    public ResponseEntity<String> get(){
        return ResponseEntity.ok("Fee retrieved");
    }

    @GetMapping("/getAll")
    public ResponseEntity<String> getAll(){
        return ResponseEntity.ok("All Fees retrieved");
    }

    @PutMapping("/update/{idFee}")
    public ResponseEntity<String> updatet(){
        return ResponseEntity.ok("Fee updated");
    }

    @DeleteMapping("/delete/{idFee}")
    public ResponseEntity<String> delete(){
        return ResponseEntity.ok("Fee deleted");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteLists(@RequestBody List<String> listFees){
        return ResponseEntity.ok("List of Fees deleted");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll(){
        return ResponseEntity.ok("All Fees deleted");
    }
}
