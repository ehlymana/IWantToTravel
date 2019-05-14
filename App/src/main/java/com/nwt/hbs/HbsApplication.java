package com.nwt.hbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class HbsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HbsApplication.class, args);
    }

}
