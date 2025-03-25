package management.apiauth.exception;

import java.io.IOException;

public class UserAlreadyExistsException extends IOException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}