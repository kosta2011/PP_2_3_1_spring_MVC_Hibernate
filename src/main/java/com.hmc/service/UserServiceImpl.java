package com.hmc.service;

import com.hmc.dao.UserDao;
import com.hmc.dao.UserDaoEntityManagerImpl;
import com.hmc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserServiceImpl implements UserService {
    private final UserDao db;

    @Autowired
    public UserServiceImpl(UserDao db) {
        this.db = db;
    }


    @Override
    @Transactional
    public void saveUser(String name, String lastName) {
        db.saveUser(name, lastName);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        db.saveUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        db.updateUser(user);
    }

    @Override
    @Transactional
    public void updateUser(long id, String name, String lastName) {
        db.updateUser(id, name, lastName);
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        db.removeUserById(id);
    }

    @Override
    @Transactional
    public User readUserById(long id) {
        return db.readUserById(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return db.getAllUsers();
    }
}
