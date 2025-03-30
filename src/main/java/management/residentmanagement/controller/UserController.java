package management.residentmanagement.controller;


import jakarta.validation.Valid;
import management.residentmanagement.entity.User;
import management.residentmanagement.model.LoginRequest;
import management.residentmanagement.model.RegisterRequest;
import management.residentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Home");
    }

//    @GetMapping("/home")
//    public ResponseEntity<String> home() {
//        return ResponseEntity.ok("Home");
//    }

    @GetMapping("/user/dashboard")
    public ResponseEntity<String> dashboard() {
        return ResponseEntity.ok("Dashboard");
    }

    @GetMapping("/admin/dashboard")
    public ResponseEntity<String> adminDashboard() {
        return ResponseEntity.ok("Admin Dashboard");
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            boolean isSuccess = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
            if (isSuccess) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.badRequest().body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/register")
    public ResponseEntity<String> register() {
        return ResponseEntity.ok("Register");
    }

    // API đăng ký
    @PostMapping("/user/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            registerRequest.setRole(User.Role.USER);
            userService.register(registerRequest);
            return ResponseEntity.ok("Registration successful, please login");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/admin/register")
    public ResponseEntity<String> registerAdmin() {
        return ResponseEntity.ok("Register Admin");
    }

    @PostMapping("/admin/register")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            userService.register(registerRequest);
            return ResponseEntity.ok("Admin registration successful, please login");
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

    @GetMapping("forgot-password")
    public ResponseEntity<String> forgotPassword(){
        return ResponseEntity.ok("Forgot password");
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(){
        return ResponseEntity.ok("Reset password");
    }

    @GetMapping("/change-password")
    public ResponseEntity<String> changePassword(){
        return ResponseEntity.ok("Change password");
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> updatePassword(){
        return ResponseEntity.ok("Password updated");
    }
}
