package com.nwt.ums.Services;

import com.nwt.ums.Model.Role;
import com.nwt.ums.Repository.RoleRepository;
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
