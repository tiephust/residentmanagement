package management.residentmanagement.until;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expirationTime = 86400000; // 1 ngày

    // Tạo JWT dựa trên nhiều giá trị (username, id, password)
    public String generateRefreshToken( String username, Long id, String password, LocalDateTime createdAt) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("id", id);
        claims.put("password", password); // Mã hóa hoặc băm mật khẩu
        claims.put("createdAt", createdAt);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    public String generateAccessToken(String username, Long id, String password) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("id", id%10);
        claims.put("password", password); // Mã hóa hoặc băm mật khẩu

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    // Trích xuất các giá trị từ JWT
    public Map<String, Object> extractClaimsRefreshToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        // ex : {username=abc, id=1, password=123, createdAt=2021-10-10T10:10:10}
    }

    public Map<String, Object> extractClaimsAccessToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        // ex : {username=abc, password=123, id=1}
    }

    // Kiểm tra tính hợp lệ của token
    public boolean isRefreshTokenValid(String token, String username, Long id, String password, LocalDateTime createdAt) {
        Map<String, Object> claims = extractClaimsRefreshToken(token);
        return (claims.get("username").equals(username)
                && claims.get("id").equals(id)
                && claims.get("password").equals(password)
                && claims.get("createdAt").equals(createdAt)
                && !isRefreshTokenExpired(token));
    }

    public boolean isAccessTokenValid(String token, String username, Long id, String password) {
        Map<String, Object> claims = extractClaimsAccessToken(token);
        return (claims.get("username").equals(username)
                && claims.get("id").equals(id%10)
                && claims.get("password").equals(password)
                && !isRefreshTokenExpired(token));
    }

    // Kiểm tra token đã hết hạn chưa
    private boolean isRefreshTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    private boolean isAccessTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
