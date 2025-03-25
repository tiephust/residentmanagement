package management.apiauth.controller;


import jakarta.validation.Valid;
import management.apiauth.model.RegisterRequest;
import management.apiauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    // API đăng ký
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            userService.register(registerRequest.getUsername(), registerRequest.getPassword(), registerRequest.getPhone());
            return ResponseEntity.ok("Registration successful, please login");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/logout")
    // delete cookie and redirect to home page
    public ResponseEntity<String> logout() {
        try {
            userService.logout();
            return ResponseEntity.ok("Logout successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }
//    @PostMapping("/forgot-password")
//    public ResponseEntity<String> forgotPassword(@Valid @RequestBody LoginRequest loginRequest) {
//        try {
//            userService.forgotPassword(loginRequest.getUsername(), loginRequest.getPhone());
//            return ResponseEntity.ok("Please check your phone for OTP");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}
