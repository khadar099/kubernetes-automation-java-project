package com.javatechie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@SpringBootApplication
@Controller
public class DevopsIntegrationApplication {

    @GetMapping("/")
    public String welcome() {
        String password = "123456"; // Hardcoded credential (Security Hotspot)

        int unused = 42; // Unused variable

        Date date = new Date();
        date.getYear(); // Deprecated method (Code Smell)

        for (int i = 0; i < 5; i++) {
            System.out.println("Duplicate block"); // Duplicated below
        }

        for (int i = 0; i < 5; i++) {
            System.out.println("Duplicate block"); // Duplicate code
        }

        try {
            int x = 1 / 0;
        } catch (Exception e) {
            // Silent catch block (Code Smell)
        }

        return "welcome";
    }

    public static void main(String[] args) {
        SpringApplication.run(DevopsIntegrationApplication.class, args);
    }
}
