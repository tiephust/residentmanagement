package management.residentmanagement.controller;


import jakarta.validation.Valid;
import management.residentmanagement.entity.User;
import management.residentmanagement.model.LoginRequest;
import management.residentmanagement.model.RegisterRequest;
import management.residentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Home");
    }

    @GetMapping("/user/dashboard")
    public ResponseEntity<String> dashboard(Principal principal) {
        return ResponseEntity.ok("Welcome to your dashboard, " + principal.getName());
    }

    @GetMapping("/admin/dashboard")
    public ResponseEntity<String> adminDashboard(Principal principal) {
        return ResponseEntity.ok("Welcome to admin dashboard, " + principal.getName());
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginPage() {
        return ResponseEntity.ok("Login page");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            ResponseCookie jwtCookie = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .body(new AuthResponse("Login successful", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse(e.getMessage(), false));
        }
    }

    @GetMapping("/user/register")
    public ResponseEntity<String> registerPage() {
        return ResponseEntity.ok("Register page");
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            registerRequest.setRole(User.Role.USER);
            userService.register(registerRequest);
            return ResponseEntity.ok(new AuthResponse("Registration successful", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse(e.getMessage(), false));
        }
    }

    @GetMapping("/admin/register")
    public ResponseEntity<String> registerAdminPage() {
        return ResponseEntity.ok("Admin register page");
    }

    @PostMapping("/admin/register")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            registerRequest.setRole(User.Role.ADMIN);
            userService.register(registerRequest);
            return ResponseEntity.ok(new AuthResponse("Admin registration successful", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse(e.getMessage(), false));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        try {
            userService.logout();
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, "")
                    .body(new AuthResponse("Logout successful", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse(e.getMessage(), false));
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String username, @RequestParam String phone) {
        try {
            userService.forgotPassword(username, phone);
            return ResponseEntity.ok(new AuthResponse("Password reset instructions sent", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse(e.getMessage(), false));
        }
    }

//    @PutMapping("/reset-password")
//    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
//        try {
//            userService.resetPassword(token, newPassword);
//            return ResponseEntity.ok(new AuthResponse("Password reset successful", true));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new AuthResponse(e.getMessage(), false));
//        }
//    }
//
//    @PutMapping("/change-password")
//    public ResponseEntity<?> changePassword(Principal principal,
//                                            @RequestParam String currentPassword,
//                                            @RequestParam String newPassword) {
//        try {
//            userService.changePassword(principal.getName(), currentPassword, newPassword);
//            return ResponseEntity.ok(new AuthResponse("Password changed successfully", true));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new AuthResponse(e.getMessage(), false));
//        }
//    }
}

// Thêm class AuthResponse để chuẩn hóa response
class AuthResponse {
    private String message;
    private boolean success;

    public AuthResponse(String loginSuccessful, boolean b) {
    }

    // constructor, getters, setters
}