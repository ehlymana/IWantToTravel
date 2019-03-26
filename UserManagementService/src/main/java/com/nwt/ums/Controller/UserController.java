package com.nwt.ums.Controller;

import com.nwt.ums.Model.User;
import com.nwt.ums.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String addUser(){
        User user = new User("jdoe", "novipass", "Jane7", "Doe7", 10.0, 10.0, "ROLE_USER");
        userService.save(user);
        return "";
    }
}
