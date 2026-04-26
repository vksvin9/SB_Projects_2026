package com.vin.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.vin.entity.User;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDAO {

    private final EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public User save(User user) {
        getSession().persist(user);
        return user;
    }

    public User findById(Long id) {
        return getSession().get(User.class, id);
    }

    public List<User> findAll(int page, int size, String sortBy) {
        return getSession()
                .createQuery("FROM User ORDER BY " + sortBy, User.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .list();
    }

    public User update(User user) {
        return (User) getSession().merge(user);
    }

    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            getSession().remove(user);
        }
    }
}