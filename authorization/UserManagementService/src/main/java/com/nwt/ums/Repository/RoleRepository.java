package com.nwt.ums.Repository;

import com.nwt.ums.Model.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRoleID(Long id);
    Role findByRoleName(String name);
    Role findByUserId(Long id);

    Iterable<Role> findAll();
}
