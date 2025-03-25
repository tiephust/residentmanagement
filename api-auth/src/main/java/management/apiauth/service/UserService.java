package management.apiauth.service;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import management.apiauth.entity.User;
import management.apiauth.exception.UserAlreadyExistsException;
import management.apiauth.exception.UserNotFoundException;
import management.apiauth.repository.UserRepository;
import management.apiauth.until.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
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
        // Khởi tạo người dùng admin nếu chưa tồn tại
        if (userRepository.count() == 0) {
//            User admin = new User();
//            admin.setUsername("admin");
//            admin.setPassword(passwordEncoder.encode("admin123"));
//            admin.setIdResident("123456789");
//            admin.setRole("ADMIN");
//            admin.setCreateAt(LocalDateTime.now());
//
//            userRepository.save(admin);
//            System.out.println("Khởi tạo người dùng admin mặc định.");
        }
    }

    public boolean login(String username , String rawPassword) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found with username or Identifier: " + username);
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new UserNotFoundException("Invalid password");
        }

        // Tạo JWT sử dụng nhiều giá trị (id, username, CCCD, password)
        String token = jwtUtil.generateToken(user.getUsername(), user.getId(), user.getPassword());

        // Tạo cookie chứa JWT
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(true) // Chỉ bật trong môi trường production
                .path("/")
                .maxAge(24 * 60 * 60) // Cookie có thời gian sống 1 ngày
                .build();

        // Lưu cookie vào response
        response.addHeader("Set-Cookie", jwtCookie.toString());

        System.out.println("Login success");
        return true;
    }

    public void register(String username, String rawPassword, String phone) throws IOException {
        // Kiểm tra xem người dùng đã tồn tại chưa
        Optional<User> existingUserName = userRepository.findByUsername(username);
        Optional<User> existingPhone = userRepository.findByPhone(phone);
        if (existingUserName.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with username: " + username);
        } else if (existingPhone.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with phone: " + phone);
        }

        // Tạo người dùng mới
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole("USER");
        user.setCreateAt(LocalDateTime.now());
        userRepository.save(user);

        // Chuyển hướng đến trang đăng nhập
        response.sendRedirect("/login");
    }

    public void logout() {
        // Xóa cookie chứa JWT
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .secure(true) // Chỉ bật trong môi trường production
                .path("/")
                .maxAge(0) // Cookie có thời gian sống 0 giây
                .build();

        // Lưu cookie vào response
        response.addHeader("Set-Cookie", jwtCookie.toString());
    }

    // forgot password
    public void forgotPassword(String username, String phone) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found with username or phone: " + username);
        }

        User user = optionalUser.get();
        // Gửi email chứa mật khẩu
        System.out.println("Password: " + user.getPassword());
    }
}
