package com.example.helloworldservice.Student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student shrestha = new Student(
                "Shrestha",
                "shrestha.shgd@gmail",
                LocalDate.of(2000, 01, 24)
                
            );

            Student sonali = new Student(
                "sonali",
                "saa.shgd@gmail",
                LocalDate.of(2000, 11, 24)
                
            );
            repository.saveAll(List.of(shrestha, sonali));
        };
    }
}
