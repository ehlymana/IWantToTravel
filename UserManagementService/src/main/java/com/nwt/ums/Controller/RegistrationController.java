package com.nwt.ums.Controller;


import com.nwt.ums.Model.User;
import com.nwt.ums.Services.EmailService;
import com.nwt.ums.Services.RoleService;
import com.nwt.ums.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class RegistrationController {
    private UserService userService;
    private EmailService emailService;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegistrationController(UserService userService, EmailService emailService, RoleService roleService,  BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.emailService = emailService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
    }

    /*@RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registerUser(Model model, Users user) {

        model.addAttribute("user",user);
        return "auth/registration";
    }*/

    // registration
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<Object> postAddNewUser(Model model, @Valid @ModelAttribute("user") User user,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirect,
                                 HttpServletRequest request) {


        User checkifExists = userService.findByUsername(user.getUsername());

        if (checkifExists != null){
            redirect.addFlashAttribute("failMessage","Username already taken.");
            bindingResult.reject("username");
//            return "redirect:/login";
            return new ResponseEntity<>("Registration failed (username exist)!", HttpStatus.BAD_REQUEST);
        }

        //checkifExists = userService.findByEmail(user.getEmail());
//        if (checkifExists != null){
//            redirect.addFlashAttribute("failMessage","User already exists.");
//            bindingResult.reject("email");
//            return new ResponseEntity<>("Registration failed! (email exists)", HttpStatus.BAD_REQUEST);
//
//        }

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

            String appUrl = request.getScheme() + "://" + request.getServerName();
            SimpleMailMessage registrationEmail = new SimpleMailMessage();
            registrationEmail.setTo(user.getEmail());
            registrationEmail.setSubject("Confirm your email");
            registrationEmail.setText("Before we get started, please confirm your email address by clicking the link below:\n"
                    + appUrl + ":8080/account/confirmation?token=" + user.getConfirmToken());

            emailService.sendEmail(registrationEmail);
            System.out.println("Email sent!");

            //redirect.addFlashAttribute("successMessage", "Check your email to confirm the registration." + user.getEmail());

            return new ResponseEntity<>("Registration successful", HttpStatus.OK);
        }
    }

    // email confirmation
    @RequestMapping(value = "/account/confirmation", method = RequestMethod.GET)
    public ResponseEntity<Object> confirmCreatedAccount(@RequestParam("token") String token, RedirectAttributes redirect){

        User user = userService.findByConfirmToken(token);

        if (user != null) {
            user.setEnabled(true);
            user.setRole(roleService.findByRoleName("ROLE_USER"));
            user.setConfirmToken(userService.getNewToken());
            userService.save(user);
            //redirect.addFlashAttribute("successMessage", "Account created.");
            return new ResponseEntity<>("Registration completed!", HttpStatus.OK);
        }
        else {
            redirect.addFlashAttribute("failMessage", "An error occurred! Account not created.");
            return new ResponseEntity<>("Error during account confirmation!", HttpStatus.BAD_REQUEST);
        }
    }


    // forgotten password
    @RequestMapping(value = "/password/forgotten", method = RequestMethod.GET)
    public String showForgotPassword() {
        return "auth/passwordreset";
    }

    @RequestMapping(value = "/password/forgotten", method = RequestMethod.POST)
    public String postForgotPassword(Model model, @RequestParam Map requestParams, HttpServletRequest request, RedirectAttributes redirectAttributes){

        User user = userService.findByEmail(requestParams.get("email").toString());
        if (user == null){
            redirectAttributes.addFlashAttribute("failMessage", "User with this email doesn't exist.");
            return "redirect:/password/forgotten";
        }

        String appUrl = request.getScheme() + "://" + request.getServerName();
        SimpleMailMessage resetPasswordEmail = new SimpleMailMessage();
        resetPasswordEmail.setTo(user.getEmail());
        resetPasswordEmail.setSubject("Password reset");
        resetPasswordEmail.setText("We are almost there. Please click on the link below to set a new password.\n"
                + appUrl + ":8080/password/reset?token=" + user.getPasswordToken());

        emailService.sendEmail(resetPasswordEmail);
        redirectAttributes.addFlashAttribute("successMessage", "Check your email to reset your password.");

        return "redirect:/password/forgotten";
    }

    @RequestMapping(value = "/password/reset", method = RequestMethod.GET)
    public String resetPassword(Model model, @RequestParam("token") String token, RedirectAttributes redirectAttributes){

        User user = userService.findByPasswordToken(token);
        if (user == null){
            redirectAttributes.addFlashAttribute("failMessage", "User doesn't exist.");
            return "redirect:/password/forgotten";
        }

        model.addAttribute("token", token);

        return "auth/createNewPassword";
    }

    @RequestMapping(value = "/password/reset", method = RequestMethod.POST)
    public String postResetPassword(@RequestParam Map requestParams, RedirectAttributes redirectAttributes){

        User user = userService.findByPasswordToken(requestParams.get("token").toString());
        if (user == null){
            redirectAttributes.addFlashAttribute("failMessage", "User doesn't exist.");
            return "redirect:/password/forgotten";
        }

        user.setPassword(bCryptPasswordEncoder.encode((CharSequence) requestParams.get("password").toString()));
        user.setPasswordToken(userService.getNewToken());
        userService.save(user);

        redirectAttributes.addFlashAttribute("successMessage", "Password changed.");
        return "redirect:/login";
    }
}

