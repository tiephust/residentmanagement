package management.residentmanagement;

import management.residentmanagement.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ResidentmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResidentmanagementApplication.class, args);
//        ApplicationContext context = SpringApplication.run(ResidentmanagementApplication.class, args);
//        UserService userService = context.getBean(UserService.class);
//        userService.checkLogin("123456789", "admin123");
    }

}
