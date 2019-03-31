package com.nwt.ums.Controller;

import com.nwt.ums.Model.Role;
import com.nwt.ums.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/addrole", method = RequestMethod.GET)
    public String addRole()  {
       // Role role = new Role("ROLE_USER", Long.parseLong("1"));
        //roleService.save(role);
        return "";
    }
}
