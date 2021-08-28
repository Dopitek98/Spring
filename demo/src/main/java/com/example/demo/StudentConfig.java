package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student Adam = new Student(1, "ADAM", "Kowalski", "ak@wp.pl", LocalDate.of(2010,5,5),"USER");
            Student Ada = new Student(2, "ADA", "Nowak", "an@wp.pl",LocalDate.of(2000,4,5),"USER");
            //studentRepository.saveAll(List.of(Adam,Ada));
            studentRepository.save(Adam);
            studentRepository.save(Ada);
        };
    }
}
