package com.hmc.dao;

import com.hmc.model.User;
import com.hmc.util.Util;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoEntityManagerImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(String name, String lastName) {
        entityManager.persist(new User(name, lastName));
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.find(User.class, user.getId());
        entityManager.merge(new User(user.getId(), user.getName(), user.getLastName()));
    }

    @Override
    public void updateUser(long id, String name, String lastName) {
        User user = entityManager.find(User.class, id);
        user.setName(name);
        user.setLastName(lastName);
        entityManager.merge(user);
    }

    @Override
    public void removeUserById(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User readUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = entityManager.createQuery("select user from User user",
                User.class).getResultList();
        return userList;
    }
}
