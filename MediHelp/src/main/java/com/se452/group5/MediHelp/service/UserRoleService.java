package com.se452.group5.MediHelp.service;

import com.se452.group5.MediHelp.entity.UserRole;
import com.se452.group5.MediHelp.repository.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userRoleService")
public class UserRoleService {

	private UserRoleRepository userRoleRepository;
	
    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository) { 
      this.userRoleRepository = userRoleRepository;
    }
	
	public void saveUserRole(UserRole ur) {
		userRoleRepository.save(ur);
	}

}