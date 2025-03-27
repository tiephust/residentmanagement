package management.apiauth.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ForgotPasswordRequest {
    @NotBlank(message = "Username is mandatory")
    @Size(min = 5, message = "Username must be at least 5 characters")
    @Size(max = 20, message = "Username must be less than 20 characters")
    @Pattern(regexp = "^(?!\\d+$)[a-zA-Z0-9]+$", message = "Username must contain letters and cannot be all digits")
    private String username;
}
