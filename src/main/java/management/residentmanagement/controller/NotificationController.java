package management.residentmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @GetMapping("")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("Notification home");
    }

    @GetMapping("/create")
    public ResponseEntity<String> create(){
        return ResponseEntity.ok("Notification created");
    }

    @PostMapping("/create/{idNotification}")
    public ResponseEntity<String> create(@PathVariable String id){
        return ResponseEntity.ok("Notification created");
    }

    @GetMapping("/get/{idNotification}")
    public ResponseEntity<String> get(){
        return ResponseEntity.ok("Notification retrieved");
    }

    @GetMapping("/getAll")
    public ResponseEntity<String> getAll(){
        return ResponseEntity.ok("All Notifications retrieved");
    }

    @PutMapping("/update/{idNotification}")
    public ResponseEntity<String> updatet(){
        return ResponseEntity.ok("Notification updated");
    }

    @DeleteMapping("/delete/{idNotification}")
    public ResponseEntity<String> delete(){
        return ResponseEntity.ok("Notification deleted");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteLists(@RequestBody List<String> listNotifications){
        return ResponseEntity.ok("List of Notifications deleted");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll(){
        return ResponseEntity.ok("All Notifications deleted");
    }
}
