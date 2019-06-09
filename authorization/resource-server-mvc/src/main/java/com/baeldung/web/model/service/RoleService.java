package com.baeldung.web.model.service;

import com.baeldung.web.model.Role;
import com.baeldung.web.model.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public void save (Role role) {
        roleRepository.save(role);
    }

    public void delete (Role role){
        roleRepository.delete(role);
    }

    public Role findByRoleID (Long id) {
        return roleRepository.findByRoleID(id);
    }

    public Role findByRoleName (String name) {
        return roleRepository.findByRoleName(name);
    }

    public Role findByUserId (Long id) {
        return  roleRepository.findByUserId(id);
    }

    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }
}