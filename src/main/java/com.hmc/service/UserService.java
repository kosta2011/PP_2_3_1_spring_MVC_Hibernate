package com.hmc.service;

import com.hmc.model.User;

import java.util.List;

public interface UserService {
    void saveUser(String name, String lastName);
    void saveUser(User user);
    void updateUser(User user);
    void updateUser(long id, String name, String lastName);
    void removeUserById(long id);
    User readUserById(long id);
    List<User> getAllUsers();
}
