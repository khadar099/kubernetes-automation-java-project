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

        String unusedVariable = "DevOps";  // Code smell: unused variable

        if (true == true) {  // Code smell: always true condition
            System.out.println("Welcome endpoint called");
        }

        return "welcome";
    }

    public static void main(String[] args) {

        if (false == false) {   // Code smell: useless condition
            SpringApplication.run(DevopsIntegrationApplication.class, args
