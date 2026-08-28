package com.example.kap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KapApplication {


    public static void main(String[] args) {      // Run ettiğinde JVM ilk olarak metodunu çalıştırıyor.
        SpringApplication.run(KapApplication.class, args);  //Spring Boot uygulaması ayağa kalkıyor.
    }

}