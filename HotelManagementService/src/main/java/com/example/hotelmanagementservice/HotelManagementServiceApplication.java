package com.example.hotelmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelManagementServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(HotelManagementServiceApplication.class, args);

        String s = "INSERT INTO Hotel VALUES(1, 'Hollywood', 'neki desc', 'Sarajevo', 'Adresa', 45, 47)";
        String s1 = "INSERT INTO Room VALUES(1, 1, 4, 'has wifi')";
    }

}
