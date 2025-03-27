package management.residentmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bill")
public class BillController {
    @GetMapping("")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("Bill home");
    }

    @GetMapping("/create")
    public ResponseEntity<String> create(){
        return ResponseEntity.ok("Bill created");
    }

    @PostMapping("/create/{idBill}")
    public ResponseEntity<String> create(@PathVariable String id){
        return ResponseEntity.ok("Bill created");
    }

    @GetMapping("/get/{idBill}")
    public ResponseEntity<String> get(){
        return ResponseEntity.ok("Bill retrieved");
    }

    @GetMapping("/getAll")
    public ResponseEntity<String> getAll(){
        return ResponseEntity.ok("All Bills retrieved");
    }

    @PutMapping("/update/{idBill}")
    public ResponseEntity<String> updatet(){
        return ResponseEntity.ok("Bill updated");
    }

    @DeleteMapping("/delete/{idBill}")
    public ResponseEntity<String> delete(){
        return ResponseEntity.ok("Bill deleted");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteLists(@RequestBody List<String> listBills){
        return ResponseEntity.ok("List of Bills deleted");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll(){
        return ResponseEntity.ok("All Bills deleted");
    }
}
