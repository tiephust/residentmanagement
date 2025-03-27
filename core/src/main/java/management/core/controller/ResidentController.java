package management.core.controller;

import management.core.entity.Resident;
import management.core.model.ResidentDto;
import management.core.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resident")
public class ResidentController {
    @Autowired
    private ResidentService residentService;

    @GetMapping("")
    public ResponseEntity<List<ResidentDto>> getListResident() {
        return ResponseEntity.ok(residentService.toListResidentDto(residentService.findAllResident()));
    }

    // get resident by id from parameter
    @GetMapping("/{id}")
    public ResponseEntity<ResidentDto> getResidentById(@PathVariable Long id) {
        Resident resident = residentService.getResidentById(id);
        return ResponseEntity.ok(residentService.toResidentDto(resident));
    }

    // create resident
    @PostMapping("/create")
    public ResponseEntity<ResidentDto> createResident(@RequestBody ResidentDto residentDto) {
        residentService.createResident(residentDto);
        return ResponseEntity.ok(residentDto);
    }

    // update resident
    @PutMapping("/update")
    public ResponseEntity<ResidentDto> updateResident(@PathVariable Long id ,@RequestBody ResidentDto residentDto) {
        residentService.updateResident(id, residentDto);
        return ResponseEntity.ok(residentDto);
    }

    // delete resident
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteResident(@PathVariable Long id) {
        residentService.deleteResident(id);
        return ResponseEntity.ok("Delete resident success");
    }

    @GetMapping("")
    public ResponseEntity<List<ResidentDto>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(residentService.toListResidentDto(residentService.search(keyword)));
    }
}
