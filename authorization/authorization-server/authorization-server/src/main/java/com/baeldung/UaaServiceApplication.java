package com.baeldung;

//import com.baeldung.config.SimpleCORSFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class UaaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UaaServiceApplication.class, args);
    }

//    @Bean
//    public FilterRegistrationBean corsFilterRegistration() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new SimpleCORSFilter());
//        registrationBean.setName("CORS Filter");
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }

}
