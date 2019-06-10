package com.baeldung.web;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class HelloController {

//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    public String helloWorld(Principal principal) {
//
//        return principal == null ? "Hello anonymous" : "Hello " + principal.getName();
//    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(){
        return "views/index";
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> redirectToUserPanel(HttpServletResponse response, Principal principal) throws
            IOException {
        if(principal != null) {
            HttpHeaders headers = new HttpHeaders();
            if(principal.getName().equalsIgnoreCase("admin")) {
                headers.setLocation(URI.create("http://localhost:8080/admin/dashboard"));
            } else {
                headers.setLocation(URI.create("http://localhost:8080/user/dashboard"));
            }
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);

        } else {
            return new ResponseEntity<>("Login required!", HttpStatus.BAD_REQUEST);
        }
        //If your method always returns a redirect, use ResponseEntity<Void>, otherwise whatever is returned normally as generic type.
    }


    @PreAuthorize("#oauth2.hasScope('fooScope') and hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "secret", method = RequestMethod.GET)
    @ResponseBody
    public String helloSecret(Principal principal) {
        return principal == null ? "Hello anonymous" : "S3CR3T  - Hello " + principal.getName();
    }
}