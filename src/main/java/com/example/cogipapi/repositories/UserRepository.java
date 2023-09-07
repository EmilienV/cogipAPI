package com.example.cogipapi.repositories;

import com.example.cogipapi.authorisation.UserRole;
import com.example.cogipapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query to find a user by username
    User findByUsername(String username);

    // Custom query to find users by role
    List<User> findByRolesIn(Set<UserRole> roles);

}