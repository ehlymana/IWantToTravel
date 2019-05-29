package com.nwt.ums.Configuration;


import com.nwt.ums.CORSFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CORSFilter corsFilter() {
        CORSFilter filter = new CORSFilter();
        return filter;
    }


    @Override
//    @CrossOrigin(origins = "http://localhost:8080")
    protected void configure(HttpSecurity http) throws Exception {
         http.addFilterBefore(corsFilter(), SessionManagementFilter.class)
                 .authorizeRequests()
                .antMatchers("/", "/login", "/aboutus", "/contactus","/account/confirmation", "/registration", "/password/forgotten", "/password/reset").permitAll()
                .anyRequest().authenticated()
                .and()
//                 .csrf().disable()
//                 .cors().disable()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/default")
                .and()
                .logout()
                .logoutSuccessUrl("/?logout")
                 .and()
               //   .csrf().disable()

                .exceptionHandling().accessDeniedPage("/403");
    }


}
