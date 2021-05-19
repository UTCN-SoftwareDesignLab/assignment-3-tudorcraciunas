package com.example.assig3.user;

import com.example.assig3.user.model.ERole;
import com.example.assig3.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
}
