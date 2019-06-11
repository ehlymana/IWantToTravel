package com.baeldung.security;


//import com.baeldung.MyCorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
@EnableResourceServer
//@EnableWebSecurity
@Order(value = 0)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private ResourceServerTokenServices resourceServerTokenServices;

    @Bean
    @Primary
    public OAuth2ClientContextFilter oauth2ClientContextFilterWithPath() {
        return new Oauth2ClientContextFilterWithPath();
    }

//    public MyCorsFilter corsFilter() {
//        MyCorsFilter filter = new MyCorsFilter();
//        return filter;
//    }
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/").setViewName("index");
//            }
//        };
//    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        DefaultHttpFirewall firewall = new DefaultHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
//    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        // @formatter:off
        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
//        web.ignoring()
//                .antMatchers("/user-management-service/account/registration");
//               // .antMatchers("/publics/**");
    }



    @Override
  //  @CrossOrigin(origins = "http://localhost:8080")
    public void configure(HttpSecurity http) throws Exception { // @formatter:off
        http
               // .addFilterBefore(corsFilter(), SessionManagementFilter.class)
                .csrf().disable()
                .anonymous().and()
            .authorizeRequests()
            .antMatchers("/authorization-server/**", "/login", "/user-management-service/home", "/user-management-service/account/registration" ).permitAll()
                .and().authorizeRequests()
           .antMatchers("/user-management-service/admin/**").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/user-management-service/account/user/dashboard/").access("hasAnyRole('ROLE_USER')")
            .anyRequest().authenticated()
            .and()
               .addFilterAfter(oAuth2AuthenticationProcessingFilter(), AbstractPreAuthenticatedProcessingFilter.class)
            .logout().permitAll().logoutSuccessUrl("/");
    } // @formatter:on

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedHeaders();
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }

//    @Bean
//    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
//        StrictHttpFirewall firewall = new StrictHttpFirewall();
//        firewall.setAllowUrlEncodedSlash(true);
//        firewall.setAllowSemicolon(true);
//        return firewall;
//    }

    private OAuth2AuthenticationProcessingFilter oAuth2AuthenticationProcessingFilter() {
        OAuth2AuthenticationProcessingFilter oAuth2AuthenticationProcessingFilter = new OAuth2AuthenticationProcessingFilter();
        oAuth2AuthenticationProcessingFilter.setAuthenticationManager(oauthAuthenticationManager());
        oAuth2AuthenticationProcessingFilter.setStateless(false);

        return oAuth2AuthenticationProcessingFilter;
    }

    private AuthenticationManager oauthAuthenticationManager() {
        OAuth2AuthenticationManager oAuth2AuthenticationManager = new OAuth2AuthenticationManager();
        oAuth2AuthenticationManager.setResourceId("apigateway");
        oAuth2AuthenticationManager.setTokenServices(resourceServerTokenServices);
        oAuth2AuthenticationManager.setClientDetailsService(null);

        return oAuth2AuthenticationManager;
    }
}
