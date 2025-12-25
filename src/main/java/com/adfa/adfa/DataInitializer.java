package com.adfa.adfa;

import com.adfa.adfa.data.repository.UserRepository;
import com.adfa.adfa.model.entity.Administrator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        String username = "administrator";
        String password = "password";

        if(!userRepository.existsByUsername(username)) {
            Administrator administrator = new Administrator();

            administrator.setUsername(username);
            administrator.setPassword(passwordEncoder.encode(password));
            userRepository.save(administrator);
            System.out.println("Administrator user created: \n" + username + "Administrator password: " + password);
        }
    }
}
