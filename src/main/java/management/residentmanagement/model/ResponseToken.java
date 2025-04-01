package management.residentmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseToken {
    private String refreshToken;
    private String accessToken;
}
