package com.se452.group5.MediHelp.repository.AuthRepository;

import com.se452.group5.MediHelp.model.AuthModel.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
}