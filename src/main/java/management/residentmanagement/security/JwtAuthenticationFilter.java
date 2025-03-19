package management.residentmanagement.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import management.residentmanagement.entity.User;
import management.residentmanagement.repository.UserRepository;
import management.residentmanagement.until.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.util.Map;

@EnableWebSecurity
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        // Lấy JWT từ cookie
        Cookie cookie = WebUtils.getCookie(request, "jwt");
        if (cookie != null) {
            String jwt = cookie.getValue();
            Map<String, Object> claims = jwtUtil.extractClaims(jwt);
            Long id = Long.parseLong(claims.get("id").toString());
            String username = claims.get("username").toString();
            String password = claims.get("password").toString();

            // Kiểm tra tính hợp lệ của token
            if (jwtUtil.isTokenValid(jwt, username, id, password)) {
                User user = userRepository.findById(id).orElse(null);
                if (user != null) {
                    // Xác thực thành công, thực hiện các thao tác cần thiết
                    System.out.println("Xác thực thành công cho user với id: " + id);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
