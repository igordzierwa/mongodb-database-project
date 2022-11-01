package com.agh.northwindproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NorthwindProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(NorthwindProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
