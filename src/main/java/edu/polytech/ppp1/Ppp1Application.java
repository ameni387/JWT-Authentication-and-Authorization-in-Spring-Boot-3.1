package edu.polytech.ppp1;

import edu.polytech.ppp1.entities.Role;
import edu.polytech.ppp1.entities.User;
import edu.polytech.ppp1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Ppp1Application implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(Ppp1Application.class, args);
    }

    public void run(String... args){
        User adminAccount = userRepository.findByRole(Role.ADMIN);
        if(null==adminAccount){
            User user=new User();
            user.setEmail("admin@gamil.com");
            user.setName("admin");
            user.setRole(Role.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("12345"));
            userRepository.save(user);
        }
    }
}
