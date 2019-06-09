package com.baeldung.web.model.controller;

import com.baeldung.web.model.User;
import com.baeldung.web.model.service.EmailService;
import com.baeldung.web.model.service.RoleService;
import com.baeldung.web.model.service.UserService;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private UserService userService;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private EmailService emailService;

    @Autowired
    private EurekaClient eurekaClient;

    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @LoadBalanced
    private RestTemplate loadBalanced;

    @Autowired
    public AccountController(UserService userService, RoleService roleService,
                          DiscoveryClient discoveryClient, BCryptPasswordEncoder bCryptPasswordEncoder,EmailService emailService) {

        this.userService = userService;
        this.roleService = roleService;
        this.discoveryClient = discoveryClient;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    ResponseEntity<Object> signIn(@RequestParam("username") String username, @RequestParam("password") String password) {
        User checkIfExists = userService.findByUsername(username);
        if (checkIfExists != null) {
            String userPassword = bCryptPasswordEncoder.encode(password);
            System.out.println(userPassword);
            System.out.println(checkIfExists.getPassword());
            System.out.println(bCryptPasswordEncoder.encode("password"));
            System.out.println(checkIfExists.getEnabled());
            if (checkIfExists.getPassword().equals(userPassword) && checkIfExists.getEnabled()) {
                return new ResponseEntity<>("Login successful!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Your username or password are not correct!", HttpStatus.BAD_REQUEST);

    }

    // Account
    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<Object> postAddNewUser(Model model, @Valid @ModelAttribute("user") User user,
                                                 BindingResult bindingResult,
                                                 RedirectAttributes redirect,
                                                 HttpServletRequest request) {


        User checkifExists = userService.findByUsername(user.getUsername());

        if (checkifExists != null){
            redirect.addFlashAttribute("failMessage","Username already taken.");
            bindingResult.reject("username");
            return new ResponseEntity<>("Registration failed (username exist)!", HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.hasErrors()){

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            String result = "";
            for (int i = 0; i <  fieldErrors.size() - 1; i++) {
                result += fieldErrors.get(i).getField();
                result += ", ";
            }
            result += fieldErrors.get(fieldErrors.size() - 1).getField();

            if(fieldErrors.size() > 1)
                redirect.addFlashAttribute("failMessage", "User registration failed. " + result + " are not valid.");
            else
                redirect.addFlashAttribute("failMessage", "User registration failed. " + result + " is not valid.");

            return new ResponseEntity<>("Registration failed! (Binding errors)", HttpStatus.BAD_REQUEST);

        } else{
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setEnabled(false);
            user.setPasswordToken(userService.getNewToken());
            user.setConfirmToken(userService.getNewToken());
            user.setReactivateToken(userService.getNewToken());
            user.setRole(roleService.findByRoleName("ROLE_USER"));
            userService.save(user);

            System.out.println("User added!");

            String appUrl = request.getScheme() + "://" + request.getServerName();
            SimpleMailMessage registrationEmail = new SimpleMailMessage();
            registrationEmail.setTo(user.getEmail());
            registrationEmail.setSubject("Confirm your email");
            registrationEmail.setText("Before we get started, please confirm your email address by clicking the link below:\n"
                    + appUrl + ":8765/user-management-service/account/confirmation?token=" + user.getConfirmToken());

            emailService.sendEmail(registrationEmail);
            System.out.println("Email sent!");

            return new ResponseEntity<>("Registration successful", HttpStatus.OK);
        }
    }

    // Account confirmation with email
    @RequestMapping(value = "/confirmation", method = RequestMethod.GET)
    public ResponseEntity<Object> confirmCreatedAccount(@RequestParam("token") String token, RedirectAttributes redirect){

        User user = userService.findByConfirmToken(token);

        if (user != null) {
            user.setEnabled(true);
            user.setRole(roleService.findByRoleName("ROLE_USER"));
            user.setConfirmToken(userService.getNewToken());
            userService.save(user);
            return new ResponseEntity<>("Registration completed!", HttpStatus.OK);
        }
        else {
            redirect.addFlashAttribute("failMessage", "An error occurred! Account not created.");
            return new ResponseEntity<>("Error during account confirmation!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public ResponseEntity<Object> redirectToUserPanel(HttpServletResponse response, Principal principal) throws
            IOException {
        if(principal != null) {
            // response.sendRedirect("some-url");

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("http://localhost:8080"));
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);

        } else {
            return new ResponseEntity<>("Login required!", HttpStatus.BAD_REQUEST);
        }
        //If your method always returns a redirect, use ResponseEntity<Void>, otherwise whatever is returned normally as generic type.
    }
}
