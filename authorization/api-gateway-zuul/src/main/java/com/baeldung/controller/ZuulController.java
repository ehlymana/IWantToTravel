package com.baeldung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ZuulController {

    @Autowired
    public ZuulController() {

    }

    @RequestMapping(value = "/home")
    public String homePage() {
        return "views/index";
    }
}
