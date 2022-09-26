package com.trthinh.demo.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student thinh = new Student(
                    "Thinh",
                    "thinh@gmail.com",
                    LocalDate.now()
            );

            Student my = new Student(
                    "My",
                    "my@gmail.com",
                    LocalDate.now()
            );
            repository.saveAll(
                    List.of(thinh, my)
            );
        };
    }
}
