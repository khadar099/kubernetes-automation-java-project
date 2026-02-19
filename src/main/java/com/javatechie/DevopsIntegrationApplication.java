package com.javatechie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class DemoService {

    public void test() {
        String a = "hello";
        String b = "hello";   // duplicate literal (code smell)

        if(true == true) {    // bad practice
            System.out.println("Always true");
        }
    }
    ]
