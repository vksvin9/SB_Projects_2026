package com.vin.dao;

import oracle.jdbc.OracleTypes;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
public class UserProcedureRepository {

    private final DataSource ds;

    public UserProcedureRepository(DataSource ds) {
        this.ds = ds;
    }

    // 3. INSERT USER
    public void insertUser(String name, String email) {
        try (Connection con = ds.getConnection();
             CallableStatement cs =
                     con.prepareCall("{call user_db.insert_user_proc(?, ?)}")) {

            cs.setString(1, name);
            cs.setString(2, email);
            cs.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    // 4. UPDATE USER
    public void updateUser(Long id, String name, String email) {
        try (Connection con = ds.getConnection();
             CallableStatement cs =
                     con.prepareCall("{call user_db.update_user_proc(?, ?, ?)}")) {

            cs.setLong(1, id);
            cs.setString(2, name);
            cs.setString(3, email);
            cs.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    // 5. NO CURSOR 1
    public Map<String, String> getUserNoCursor1(Long id) {
        try (Connection con = ds.getConnection();
             CallableStatement cs =
                     con.prepareCall("{call user_db.get_user_no_cursor_1(?, ?, ?)}")) {

            cs.setLong(1, id);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.VARCHAR);

            cs.execute();

            Map<String, String> res = new HashMap<>();
            res.put("name", cs.getString(2));
            res.put("email", cs.getString(3));
            return res;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    // 6. NO CURSOR 2
    public String getUserNoCursor2(Long id) {
        try (Connection con = ds.getConnection();
             CallableStatement cs =
                     con.prepareCall("{call user_db.get_user_no_cursor_2(?, ?)}")) {

            cs.setLong(1, id);
            cs.registerOutParameter(2, Types.VARCHAR);

            cs.execute();

            return cs.getString(2);

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    // 7. CURSOR 1
    public Map<String, Object> getUserCursor1(Long id) {
        try (Connection con = ds.getConnection();
             CallableStatement cs =
                     con.prepareCall("{call user_db.get_user_cursor_1(?, ?, ?)}")) {

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

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    // 8. CURSOR 2
    public Map<String, Object> getUserCursor2(Long id) {
        try (Connection con = ds.getConnection();
             CallableStatement cs =
                     con.prepareCall("{call user_db.get_user_cursor_2(?, ?, ?, ?)}")) {

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

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}