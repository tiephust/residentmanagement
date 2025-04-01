package management.residentmanagement.service;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import management.residentmanagement.entity.Resident;
import management.residentmanagement.entity.User;
import management.residentmanagement.exception.IncorrectException;
import management.residentmanagement.exception.UserAlreadyExistsException;
import management.residentmanagement.exception.UserNotFoundException;
import management.residentmanagement.model.ForgotPasswordRequest;
import management.residentmanagement.model.LoginRequest;
import management.residentmanagement.model.RegisterRequest;
import management.residentmanagement.model.ResponseToken;
import management.residentmanagement.repository.UserRepository;
import management.residentmanagement.until.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private JwtUtil jwtUtil;

    @PostConstruct
    public void initUsers() {
        // find all user have role is admin
        List<User> listAdmin = userRepository.findByRole(User.Role.ADMIN);

        // Khởi tạo người dùng admin nếu chưa tồn tại
        if (listAdmin.isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(User.Role.ADMIN);
            admin.setCreateAt(LocalDateTime.now());

            userRepository.save(admin);
            System.out.println("Khởi tạo người dùng admin mặc định.");
        }
    }

    public ResponseToken getTokenFromUserNamePassword(String username, String password) {
        Optional<User> optionalUser = findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found with username or Identifier: " + username);
        }
        if(!optionalUser.get().getPassword().equals(passwordEncoder.encode(password))){
            throw new IncorrectException(("Password is incorrect"));
        }
        User user = optionalUser.get();
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername(), user.getId(), user.getPassword(), user.getCreateAt());
        String accessToken = jwtUtil.generateAccessToken(user.getUsername(), user.getId(), user.getPassword());
        user.setRefreshToken(refreshToken);
        return new ResponseToken(refreshToken, accessToken);
    }

    public ResponseToken getTokenFromUserNameAndPhone(String username, String phone) {
        Optional<User> optionalUser = findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found with username or Identifier: " + username);
        }
        if(!optionalUser.get().getPhone().equals(phone)){
            throw new IncorrectException(("Phone is incorrect"));
        }
        User user = optionalUser.get();
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername(), user.getId(), user.getPassword(), user.getCreateAt());
        String accessToken = jwtUtil.generateAccessToken(user.getUsername(), user.getId(), user.getPassword());
        user.setRefreshToken(refreshToken);
        return new ResponseToken(refreshToken, accessToken);
    }

    public ResponseToken login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String rawPassword = loginRequest.getPassword();
        ResponseToken responseToken = getTokenFromUserNamePassword(username, rawPassword);
        System.out.println("Login success");
        return responseToken;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public ResponseToken register(RegisterRequest registerRequest) throws UserAlreadyExistsException {
        String username = registerRequest.getUsername();
        String rawPassword = registerRequest.getPassword();

        Optional<User> existingUser = findByUsername(username);
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        Resident user = new Resident();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setEmail(registerRequest.getEmail());
        user.setPhone(registerRequest.getPhone());
        user.setRole(registerRequest.getRole());
        user.setCreateAt(LocalDateTime.now());
        ResponseToken responseToken = getTokenFromUserNamePassword(username, rawPassword);
        user.setRefreshToken(responseToken.getRefreshToken());
        userRepository.save(user);
        return responseToken;
    }

    public String getAccessToken(String refreshToken) {
        Optional<User> optionalUser = userRepository.findByRefreshToken(refreshToken);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found with refreshToken: " + refreshToken);
        }

        User user = optionalUser.get();
        return jwtUtil.generateAccessToken(user.getUsername(), user.getId(), user.getPassword());
    }

    public void logout() {
//        // Xóa cookie chứa JWT
//        ResponseCookie jwtCookie = ResponseCookie.from("jwt", "")
//                .httpOnly(true)
//                .secure(true) // Chỉ bật trong môi trường production
//                .path("/")
//                .maxAge(0) // Cookie có thời gian sống 0 giây
//                .build();
//
//        // Lưu cookie vào response để xóa cookie cũ
//        response.addHeader("Set-Cookie", jwtCookie.toString());
    }

    // forgot password
    public User forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        String username = forgotPasswordRequest.getUsername();
        String phone = forgotPasswordRequest.getPhone();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found with username or phone: " + username);
        }
        if(!optionalUser.get().getPhone().equals(phone)){
            throw new IncorrectException(("Phone is incorrect"));
        }
        // Gửi email chứa mật khẩu
         return optionalUser.get();
    }

    public ResponseToken resetPassword(Long id, String newPassword) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        User user = optionalUser.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return getTokenFromUserNamePassword(user.getUsername(), user.getPassword());
    }
}
