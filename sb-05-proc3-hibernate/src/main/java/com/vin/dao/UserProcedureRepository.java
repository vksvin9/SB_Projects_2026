package com.vin.dao;

import jakarta.persistence.ParameterMode;
import oracle.jdbc.OracleTypes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class UserProcedureRepository {

    private final SessionFactory sessionFactory;

    public UserProcedureRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // =========================
    // 3. INSERT USER
    // =========================
    public void insertUser(String name, String email) {

        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();

            ProcedureCall call =
                    session.createStoredProcedureCall("user_db.insert_user_proc");

            call.registerParameter(1, String.class, ParameterMode.IN);
            call.registerParameter(2, String.class, ParameterMode.IN);

            call.setParameter(1, name);
            call.setParameter(2, email);

            call.getOutputs();

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    // =========================
    // 4. UPDATE USER
    // =========================
    public void updateUser(Long id, String name, String email) {

        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();

            ProcedureCall call =
                    session.createStoredProcedureCall("user_db.update_user_proc");

            call.registerParameter(1, Long.class, ParameterMode.IN);
            call.registerParameter(2, String.class, ParameterMode.IN);
            call.registerParameter(3, String.class, ParameterMode.IN);

            call.setParameter(1, id);
            call.setParameter(2, name);
            call.setParameter(3, email);

            call.getOutputs();

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    // =========================
    // 5. NO CURSOR 1
    // =========================
    public Map<String, String> getUserNoCursor1(Long id) {

        Session session = sessionFactory.openSession();

        try {
            ProcedureCall call =
                    session.createStoredProcedureCall("user_db.get_user_no_cursor_1");

            call.registerParameter(1, Long.class, ParameterMode.IN);
            call.registerParameter(2, String.class, ParameterMode.OUT);
            call.registerParameter(3, String.class, ParameterMode.OUT);

            call.setParameter(1, id);

            ProcedureOutputs out = call.getOutputs();

            Map<String, String> res = new HashMap<>();
            res.put("name", (String) out.getOutputParameterValue(2));
            res.put("email", (String) out.getOutputParameterValue(3));

            return res;

        } finally {
            session.close();
        }
    }

    // =========================
    // 6. NO CURSOR 2
    // =========================
    public String getUserNoCursor2(Long id) {

        Session session = sessionFactory.openSession();

        try {
            ProcedureCall call =
                    session.createStoredProcedureCall("user_db.get_user_no_cursor_2");

            call.registerParameter(1, Long.class, ParameterMode.IN);
            call.registerParameter(2, String.class, ParameterMode.OUT);

            call.setParameter(1, id);

            return (String) call.getOutputs().getOutputParameterValue(2);

        } finally {
            session.close();
        }
    }

    // =========================
    // 7. CURSOR 1
    // =========================
    public Map<String, Object> getUserCursor1(Long id) {

        Session session = sessionFactory.openSession();

        try {
            return session.doReturningWork(conn -> {

                try (CallableStatement cs =
                             conn.prepareCall("{call user_db.get_user_cursor_1(?, ?, ?)}")) {

                    cs.setLong(1, id);
                    cs.registerOutParameter(2, OracleTypes.CURSOR);
                    cs.registerOutParameter(3, Types.VARCHAR);

                    cs.execute();

                    String err = cs.getString(3);
                    if (err != null) throw new RuntimeException(err);

                    try (ResultSet rs = (ResultSet) cs.getObject(2)) {

                        Map<String, Object> res = new HashMap<>();

                        if (rs.next()) {
                            res.put("id", rs.getLong("id"));
                            res.put("name", rs.getString("name"));
                            res.put("email", rs.getString("email"));
                        }

                        return res;
                    }
                }
            });

        } finally {
            session.close();
        }
    }

    // =========================
    // 8. CURSOR 2 (MULTI)
    // =========================
    public Map<String, Object> getUserCursor2(Long id) {

        Session session = sessionFactory.openSession();

        try {
            return session.doReturningWork(conn -> {

                try (CallableStatement cs =
                             conn.prepareCall("{call user_db.get_user_cursor_2(?, ?, ?, ?)}")) {

                    cs.setLong(1, id);
                    cs.registerOutParameter(2, OracleTypes.CURSOR);
                    cs.registerOutParameter(3, OracleTypes.CURSOR);
                    cs.registerOutParameter(4, Types.VARCHAR);

                    cs.execute();

                    String err = cs.getString(4);
                    if (err != null) throw new RuntimeException(err);

                    try (
                        ResultSet c1 = (ResultSet) cs.getObject(2);
                        ResultSet c2 = (ResultSet) cs.getObject(3)
                    ) {

                        List<Map<String, Object>> list = new ArrayList<>();

                        while (c1.next()) {
                            Map<String, Object> row = new HashMap<>();
                            row.put("id", c1.getLong("id"));
                            row.put("name", c1.getString("name"));
                            list.add(row);
                        }

                        Map<String, Object> detail = new HashMap<>();
                        if (c2.next()) {
                            detail.put("id", c2.getLong("id"));
                            detail.put("name", c2.getString("name"));
                            detail.put("email", c2.getString("email"));
                        }

                        Map<String, Object> res = new HashMap<>();
                        res.put("list", list);
                        res.put("detail", detail);

                        return res;
                    }
                }
            });

        } finally {
            session.close();
        }
    }
}