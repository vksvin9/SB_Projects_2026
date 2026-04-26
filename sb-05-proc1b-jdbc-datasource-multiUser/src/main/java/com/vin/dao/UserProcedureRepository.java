package com.vin.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;

@Repository
public class UserProcedureRepository {

    private final DataSource ds;

    public UserProcedureRepository(@Qualifier("userDataSource") DataSource ds) {
        this.ds = ds;
    }

    // INSERT
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

    // UPDATE
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

    // GET (CURSOR)
    public Map<String, Object> getUser(Long id) {
        try (Connection con = ds.getConnection();
             CallableStatement cs =
                     con.prepareCall("{call user_db.get_user_cursor_1(?, ?, ?)}")) {

            cs.setLong(1, id);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.registerOutParameter(3, Types.VARCHAR);

            cs.execute();

            String error = cs.getString(3);
            if (error != null) {
                throw new RuntimeException(error);
            }

            try (ResultSet rs = (ResultSet) cs.getObject(2)) {

                Map<String, Object> result = new HashMap<>();

                if (rs.next()) {
                    result.put("id", rs.getLong("id"));
                    result.put("name", rs.getString("name"));
                    result.put("email", rs.getString("email"));
                }

                return result;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}