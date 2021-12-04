package ru.peter.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "{{maven.groupId}}")
public class {{project.applicationClassName}}Application {
    public static void main(String[] args) {
        SpringApplication.run({{project.applicationClassName}}Application.class);
    }
}
