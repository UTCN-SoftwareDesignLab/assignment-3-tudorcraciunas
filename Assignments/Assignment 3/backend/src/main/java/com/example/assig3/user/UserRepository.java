package com.example.assig3.user;

import com.example.assig3.user.model.Role;
import com.example.assig3.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<User> findUsersByRoles(Role role);


}
