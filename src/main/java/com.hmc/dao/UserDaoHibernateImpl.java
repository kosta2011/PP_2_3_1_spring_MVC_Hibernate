package com.hmc.dao;

import com.hmc.model.User;
import com.hmc.util.Util;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    @Override
    public void saveUser(String name, String lastName) {
        try (Session session = Util.getHibernateSession().openSession()) {
            session.save(new User(name, lastName));
        }
    }

    @Override
    public void saveUser(User user) {
        try (Session session = Util.getHibernateSession().openSession()) {
            session.save(user);
        }
    }

    @Override
    public void updateUser(User user) {
        try (Session session = Util.getHibernateSession().openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateUser(long id, String name, String lastName) {
        try (Session session = Util.getHibernateSession().openSession()) {
            session.update(new User(id, name, lastName));
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getHibernateSession().openSession()) {
            session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public User readUserById(long id) {
        //сделать не Lazy initialization
        try (Session session = Util.getHibernateSession().openSession()) {
            User user = session.load(User.class, id);
            Hibernate.initialize(user);
            return user;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return Util.getHibernateSession()
                .openSession().createQuery("From User").list();

    }
}
