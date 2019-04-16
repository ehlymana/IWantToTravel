package com.nwt.ums.Configuration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceConfiguration {
    @LoadBalanced
    @Bean
    RestTemplate loadBalanced () {
        return new RestTemplate();
    }

    @Primary
    @Bean
    RestTemplate restTemplate () {
        return new RestTemplate();
    }


}
