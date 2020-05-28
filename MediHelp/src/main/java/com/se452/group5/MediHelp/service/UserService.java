package com.se452.group5.MediHelp.service;

import com.se452.group5.MediHelp.model.AuthModel.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}