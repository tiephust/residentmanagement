package management.residentmanagement.until;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expirationTime = 86400000; // 1 ngày

    // Tạo JWT dựa trên nhiều giá trị (username, id, password)
    public String generateToken( String username, Long id, String password) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("id", id);
        claims.put("password", password); // Mã hóa hoặc băm mật khẩu

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    // Trích xuất các giá trị từ JWT
    public Map<String, Object> extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        // ex : {username=abc, id=1, password=123}
    }

    // Kiểm tra tính hợp lệ của token
    public boolean isTokenValid(String token, String username, Long id, String password) {
        Map<String, Object> claims = extractClaims(token);
        return (claims.get("username").equals(username)
                && claims.get("id").equals(id)
                && claims.get("password").equals(password)
                && !isTokenExpired(token));
    }

    // Kiểm tra token đã hết hạn chưa
    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
