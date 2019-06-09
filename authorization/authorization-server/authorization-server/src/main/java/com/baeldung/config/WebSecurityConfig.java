package com.baeldung.config;

import org.springframework.beans.factory.annotation.Autowired;
       // import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception { // @formatter:off
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll();
    }// @formatter:on

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception { // @formatter:off
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and().withUser("admin").password("admin").roles("ADMIN");
    }// @formatter:on

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}