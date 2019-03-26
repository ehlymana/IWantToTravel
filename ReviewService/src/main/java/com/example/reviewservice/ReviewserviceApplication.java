package com.example.reviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ReviewserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewserviceApplication.class, args);
    }
    String s = "INSERT INTO Review VALUES (1, 'best ever', "+new Date().toString() +")";
    //long id, String reviewComment, Date reviewDate
}
