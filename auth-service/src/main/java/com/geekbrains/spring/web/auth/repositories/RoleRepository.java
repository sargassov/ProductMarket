package com.geekbrains.spring.web.auth.repositories;

import com.geekbrains.spring.web.auth.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
