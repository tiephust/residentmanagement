package management.residentmanagement.controller;


import jakarta.validation.Valid;
import management.residentmanagement.entity.User;
import management.residentmanagement.exception.UserNotFoundException;
import management.residentmanagement.model.ForgotPasswordRequest;
import management.residentmanagement.model.LoginRequest;
import management.residentmanagement.model.RegisterRequest;
import management.residentmanagement.model.ResponseToken;
import management.residentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            ResponseToken responseToken = userService.login(loginRequest);
            User user = userService.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
            return ResponseEntity.ok(responseToken);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login failed: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            registerRequest.setRole(User.Role.USER);
            ResponseToken responseToken = userService.register(registerRequest);
            return ResponseEntity.ok(responseToken);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/admin/register")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            registerRequest.setRole(User.Role.ADMIN);
            ResponseToken responseToken = userService.register(registerRequest);
            return ResponseEntity.ok(responseToken);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        try {
            userService.logout();
            return ResponseEntity.ok("Logout successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Logout failed: " + e.getMessage());
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        try {
            User user = userService.forgotPassword(forgotPasswordRequest);
            return ResponseEntity.ok("Redirect to reset password page");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Authentication failed " + e.getMessage());
        }
    }

    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam Long id, @RequestParam String newPassword) {
        try {
            ResponseToken responseToken = userService.resetPassword(id, newPassword);
            return ResponseEntity.ok(responseToken);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Reset password failed: " + e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        try {
            User user = userService.findByUsername(principal.getName())
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            return ResponseEntity.ok(user.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Get current user failed: " + e.getMessage());
        }
    }
}