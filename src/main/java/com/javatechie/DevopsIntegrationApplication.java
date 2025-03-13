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

    public static void main(String[] args) {
        SpringApplication.run(DevopsIntegrationApplication.class, args);
    }
}
