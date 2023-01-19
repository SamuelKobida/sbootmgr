package com.example.blog.User;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            User SK = new User(
                    "Samo",
                    "Kobida",
                    "samo@gmail.com",
                    111

            );

            User PK = new User(
                    "Palo",
                    "Kobida",
                    "palo@gmail.com",
                    222

            );
        repository.saveAll(
                List.of(SK,PK)
        );
        };
    }
}
