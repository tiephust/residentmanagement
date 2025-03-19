package management.residentmanagement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ForgotPasswordRequest {
    @NotBlank(message = "Username is mandatory")
    @Size(min = 5, message = "Username must be at least 5 characters")
    @Size(max = 20, message = "Username must be less than 20 characters")
    @Pattern(regexp = "^(?!\\d+$)[a-zA-Z0-9]+$", message = "Username must contain letters and cannot be all digits")
    private String username;

    @NotBlank(message = "Phone is mandatory")
    @Pattern(regexp = "^(0|\\+84)\\d{9,10}$", message = "Phone must be a valid phone number")
    private String phone;
}
