package management.residentmanagement.factory;

import management.residentmanagement.entity.Admin;
import management.residentmanagement.entity.Resident;
import management.residentmanagement.entity.User;
import management.residentmanagement.model.RegisterRequest;

public class UserFactory {
    public static User createUser(RegisterRequest registerRequest) {
        return switch (registerRequest.getRole()) {
            case USER -> new Resident();
            case ADMIN -> new Admin();
        };
    }
}
