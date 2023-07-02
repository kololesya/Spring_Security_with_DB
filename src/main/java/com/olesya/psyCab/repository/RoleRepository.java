package com.olesya.psyCab.repository;

import com.olesya.psyCab.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByNameRole(String nameRole);
    //String existsRole(String nameRole);
}
