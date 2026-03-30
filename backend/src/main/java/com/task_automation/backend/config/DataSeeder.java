package com.task_automation.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.task_automation.backend.entity.User;
import com.task_automation.backend.enums.AuthProvider;
import com.task_automation.backend.enums.Role;
import com.task_automation.backend.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataSeeder implements CommandLineRunner{
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String adminEmail = "admin@gmail.com";
        
        if (!userRepository.existsByEmail(adminEmail)) {
            User admin = User.builder()
            .email(adminEmail)
            .passwordHash(passwordEncoder.encode("admin12345"))
            .fullName("System Administrator")
            .role(Role.ADMIN)
            .authProvider(AuthProvider.LOCAL)
            .isActive(true)
            .build();

            userRepository.save(admin);
            log.info("Default ADMIN account has been initialized: {} / {}", adminEmail, "admin12345");
        } else {
            log.info("The ADMIN account already exists in the database, skip the creation step.");
        }
        
    }

    
}
