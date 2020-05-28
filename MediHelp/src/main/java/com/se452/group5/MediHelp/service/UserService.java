package com.se452.group5.MediHelp.service;

import com.se452.group5.MediHelp.entity.AppUser;
import com.se452.group5.MediHelp.repository.AppUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserService {

	private AppUserRepository userRepository;
	
    @Autowired
    public UserService(AppUserRepository userRepository) { 
      this.userRepository = userRepository;
    }
    
	public AppUser findByUsername(String username) {
		return userRepository.findByUserName(username);
	}

	
	public void saveUser(AppUser user) {
		userRepository.save(user);
	}

}