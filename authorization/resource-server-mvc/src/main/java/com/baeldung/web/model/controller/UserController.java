package com.baeldung.web.model.controller;

import com.baeldung.web.model.Role;
import com.baeldung.web.model.User;
import com.baeldung.web.model.service.EmailService;
import com.baeldung.web.model.service.RoleService;
import com.baeldung.web.model.service.UserService;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {

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
    public UserController(UserService userService, RoleService roleService,
                          DiscoveryClient discoveryClient, BCryptPasswordEncoder bCryptPasswordEncoder,EmailService emailService) {

        this.userService = userService;
        this.roleService = roleService;
        this.discoveryClient = discoveryClient;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }



    @PreAuthorize("#oauth2.hasScope('fooScope') and hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "adduser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> addUser(@Valid User user, @RequestParam(value = "roleName", defaultValue = "ROLE_USER") String roleName, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws NotImplementedException {

        if(bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            String result = "";

            for (int i = 0; i <  fieldErrors.size() - 1; i++) {
                result += fieldErrors.get(i).getField();
                result += ", ";
            }
            result += fieldErrors.get(fieldErrors.size() - 1).getField();
            System.out.println(result);
            //throw new UserException("Binding error! Add user failed!");
        } else {
            Role role = roleService.findByRoleName(roleName);
            user.setRole(role);
            //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userService.save(user);
            // code for showig messages on frontend
            //redirectAttributes.addFlashAttribute("successMessage", "User added!");
            System.out.println("User with role " + roleName + " added!");

        }
        return new ResponseEntity<>("User with role " + roleName + " added!", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "addrole", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addRole(@Valid Role role, BindingResult bindingResult)  {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>("Binding error! Add role failed!", HttpStatus.BAD_REQUEST);
        } else {
            roleService.save(role);
        }

        return new ResponseEntity<>("Role " + role.getRoleName() + " added!", HttpStatus.OK);
    }
}
