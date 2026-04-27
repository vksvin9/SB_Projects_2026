package com.vin.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.vin.entity.UserEntity;
import com.vin.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final EntityManager em;

    // ---------------- INSERT ----------------
    @Transactional
    public void insert(String name, String email) {
        repo.insertUser(name, email);
    }

    // ---------------- UPDATE ----------------
    @Transactional
    public void update(Long id, String name, String email) {
        repo.updateUser(id, name, email);
    }

    // ---------------- NO CURSOR 1 ----------------
    public Map<String, Object> getNoCursor1(Long id) {

        StoredProcedureQuery q =
                em.createStoredProcedureQuery("user_db.get_user_no_cursor_1");

        q.registerStoredProcedureParameter("p_id", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_name", String.class, ParameterMode.OUT);
        q.registerStoredProcedureParameter("p_email", String.class, ParameterMode.OUT);

        q.setParameter("p_id", id);
        q.execute();

        Map<String, Object> res = new HashMap<>();
        res.put("id", id);
        res.put("name", q.getOutputParameterValue("p_name"));
        res.put("email", q.getOutputParameterValue("p_email"));

        return res;
    }

    // ---------------- NO CURSOR 2 ----------------
    public String getEmail(Long id) {

        StoredProcedureQuery q =
                em.createStoredProcedureQuery("user_db.get_user_no_cursor_2");

        q.registerStoredProcedureParameter("p_id", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_email", String.class, ParameterMode.OUT);

        q.setParameter("p_id", id);
        q.execute();

        return (String) q.getOutputParameterValue("p_email");
    }

    // ---------------- CURSOR 1 ----------------
    public List<UserEntity> getCursor1(Long id) {

        StoredProcedureQuery q =
                em.createStoredProcedureQuery("user_db.get_user_cursor_1", UserEntity.class);

        q.registerStoredProcedureParameter("p_id", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
        q.registerStoredProcedureParameter("p_error", String.class, ParameterMode.OUT);

        q.setParameter("p_id", id);
        q.execute();

        String error = (String) q.getOutputParameterValue("p_error");
        if (error != null) throw new RuntimeException(error);

        return q.getResultList();
    }

    // ---------------- CURSOR 2 ----------------
    public Map<String, Object> getCursor2(Long id) {

        StoredProcedureQuery q =
                em.createStoredProcedureQuery("user_db.get_user_cursor_2");

        q.registerStoredProcedureParameter("p_id", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("c1", void.class, ParameterMode.REF_CURSOR);
        q.registerStoredProcedureParameter("c2", void.class, ParameterMode.REF_CURSOR);
        q.registerStoredProcedureParameter("p_error", String.class, ParameterMode.OUT);

        q.setParameter("p_id", id);
        q.execute();

        String error = (String) q.getOutputParameterValue("p_error");
        if (error != null) throw new RuntimeException(error);

        List<Object[]> c1 = q.getResultList();

        List<Object[]> c2 = Collections.emptyList();
        if (q.hasMoreResults()) {
            c2 = q.getResultList();
        }

        Map<String, Object> res = new HashMap<>();
        res.put("c1", c1);
        res.put("c2", c2);

        return res;
    }
}