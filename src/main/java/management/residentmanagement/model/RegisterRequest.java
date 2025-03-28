package management.residentmanagement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import management.residentmanagement.validation.ValidPassword;

@Setter
@Getter
public class RegisterRequest {
    // Getter và Setter
    @NotBlank(message = "Username is mandatory")
    @Size(min = 5, message = "Username must be at least 5 characters")
    @Size(max = 20, message = "Username must be less than 20 characters")
    @Pattern(regexp = "^(?!\\d+$)[a-zA-Z0-9]+$", message = "Username must contain letters and cannot be all digits")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @ValidPassword
    private String password;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Email is not correct")
    private String email;

    @NotBlank(message = "Phone is mandatory")
    @Pattern(regexp = "^(\\+84|0)\\d{9,10}$", message = "Phone is not correct")
    private String phone;
}
