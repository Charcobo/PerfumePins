package com.perfumepins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hibernate version is: " + org.hibernate.Version.getVersionString());
        SpringApplication.run(Application.class, args);
    }
}