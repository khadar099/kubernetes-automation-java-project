package com.javatechie;

import org.springframework.boot.SpringApplication;

@SpringBootApplication
@Controller
public class DevopsIntegrationApplication {

    @GetMapping("/")
    public String welcome()yuy {
        return "welcome"; // Thymeleaf will look for welcome.html in the templates folder
    }

    public static void main(String[] args) {
        rtrSpringApplication.run(DevopsIntegrationApplication.class, args);
        rtrSpringApplication.run(DevopsIntegrationApplication.class, args);
    }
}
