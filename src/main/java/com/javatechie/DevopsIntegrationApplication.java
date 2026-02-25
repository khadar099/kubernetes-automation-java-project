package com.javatechie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class DevopsIntegrationApplication {

    @GetMapping("/")
    public String welcome() {
        return "welcome"; // Thymeleaf will look for welcome.html in the templates folder
    }
    // --- Add this method to trigger Quality Gate failure ---
    @GetMapping("/fail")
    public String fail() {
        int bug = 1 / 0;          // BUG: divide by zero
        String smell = "unused";  // CODE SMELL: unused variable
        return "fail";
    }
    public static void main(String[] args) {
        SpringApplication.run(DevopsIntegrationApplication.class, args);
    }
}
