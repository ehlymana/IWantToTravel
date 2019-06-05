package com.nwt.ums.Controller;

import com.nwt.ums.Model.Role;
import com.nwt.ums.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/addrole", method = RequestMethod.POST)
    public ResponseEntity<?> addRole(@Valid Role role, BindingResult bindingResult)  {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>("Binding error! Add role failed!", HttpStatus.BAD_REQUEST);
        } else {
            roleService.save(role);
        }

        return new ResponseEntity<>("Role " + role.getRoleName() + " added!", HttpStatus.OK);
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findRoleById(@PathVariable Long id) {
        Role role = roleService.findByRoleID(id);

        if(role != null) {
            return new ResponseEntity<>(role, HttpStatus.FOUND);
        }

        return new ResponseEntity<>("Role not found!", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/delete/role/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteRoleById(@PathVariable Long id) {
        try {
            roleService.delete(roleService.findByRoleID(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Role deleted!", HttpStatus.OK);
    }

    @RequestMapping(value = "/listRoles", method = RequestMethod.GET)
    public Iterable<Role> getAllRoles()  {

        try {
            Iterable<Role> roles = roleService.findAll();
            return roles;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }


    }
}
