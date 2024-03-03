package com.swiggy.user.repositories;

import com.swiggy.user.enums.Role;
import com.swiggy.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByRoles(Set<Role> roles);
}
