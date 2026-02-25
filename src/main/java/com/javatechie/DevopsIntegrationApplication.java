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

    // --- BUG 1: divide by zero ---
    @GetMapping("/bug1")
    public String bug1() {
        int a = 10 / 0;  // BUG: divide by zero
        return "bug1";
    }

    // --- BUG 2: NullPointerException ---
    @GetMapping("/bug2")
    public String bug2() {
        String s = null;
        if (s.equals("test")) {  // BUG: null pointer
            return "bug2";
        }
        return "bug2";
    }

    // --- CODE SMELL 1: unused variable ---
    @GetMapping("/smell1")
    public String smell1() {
        String unused = "smell"; // CODE SMELL: unused variable
        return "smell1";
    }

    // --- CODE SMELL 2: redundant variable ---
    @GetMapping("/smell2")
    public String smell2() {
        int redundant = 100; // CODE SMELL: unused variable
        return "smell2";
    }

    // --- DUPLICATE LINES EXAMPLE ---
    @GetMapping("/duplicate1")
    public String duplicate1() {
        int x = 5;
        int y = x + 10;
        System.out.println("Duplicate example: " + y);
        return "duplicate1";
    }

    @GetMapping("/duplicate2")
    public String duplicate2() {
        int x = 5;                   // DUPLICATE with duplicate1
        int y = x + 10;               // DUPLICATE
        System.out.println("Duplicate example: " + y); // DUPLICATE
        return "duplicate2";
    }

    public static void main(String[] args) {
        SpringApplication.run(DevopsIntegrationApplication.class, args);
    }
}
