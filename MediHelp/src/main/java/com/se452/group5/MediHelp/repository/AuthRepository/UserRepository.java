package com.se452.group5.MediHelp.repository.AuthRepository;

import com.se452.group5.MediHelp.model.AuthModel.User;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}