package com.vin.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Types;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AuthRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String SCHEMA = "USER_DB"; // 🔴 change if different

    // =========================
    // PROC1 - Register User
    // =========================
    public void registerUser(String user, String password) {
        try {
            jdbcTemplate.execute(
                    (CallableStatementCreator) con -> {
                        log.info("Calling PROC1_REGISTER_Users for user={}", user);
                        CallableStatement cs = con.prepareCall(
                                "{call " + SCHEMA + ".PROC1_REGISTER_Users(?,?)}"
                        );
                        cs.setString(1, user);
                        cs.setString(2, password);
                        return cs;
                    },
                    (CallableStatementCallback<Void>) cs -> {
                        cs.execute();
                        return null;
                    }
            );
        } catch (Exception ex) {
            log.error("Error in registerUser user={}", user, ex);
            throw ex;
        }
    }

    // =========================
    // PROC2 - Register API
    // =========================
    public void registerApi(String user, String api) {
        try {
            jdbcTemplate.execute(
                    (CallableStatementCreator) con -> {
                        log.info("Calling PROC2_REGISTER_API user={}, api={}", user, api);
                        CallableStatement cs = con.prepareCall(
                                "{call " + SCHEMA + ".PROC2_REGISTER_API(?,?)}"
                        );
                        cs.setString(1, user);
                        cs.setString(2, api);
                        return cs;
                    },
                    (CallableStatementCallback<Void>) cs -> {
                        cs.execute();
                        return null;
                    }
            );
        } catch (Exception ex) {
            log.error("Error in registerApi user={}, api={}", user, api, ex);
            throw ex;
        }
    }

    // =========================
    // PROC3 - Get Token
    // =========================
    public String getToken(String user, String password) {

        return jdbcTemplate.execute(
                (CallableStatementCreator) con -> {
                    CallableStatement cs = con.prepareCall(
                            "{call USER_DB.PROC3_GET_TOKEN(?,?,?)}"
                    );
                    cs.setString(1, user);
                    cs.setString(2, password);
                    cs.registerOutParameter(3, Types.VARCHAR);
                    return cs;
                },
                (CallableStatementCallback<String>) cs -> {
                    cs.execute();
                    return cs.getString(3);
                }
        );
    }

    // =========================
    // PROC4 - Auth In
    // =========================
    public void authIn(String token, String uuid, String api, String ip, String input, int limit) {
        try {
            jdbcTemplate.execute(
                    (CallableStatementCreator) con -> {
                        log.info("Calling PROC4_AUTH_IN uuid={}, api={}", uuid, api);
                        CallableStatement cs = con.prepareCall(
                                "{call " + SCHEMA + ".PROC4_AUTH_IN(?,?,?,?,?,?)}"
                        );
                        cs.setString(1, token);
                        cs.setString(2, uuid);
                        cs.setString(3, api);
                        cs.setString(4, ip);
                        cs.setString(5, input);
                        cs.setInt(6, limit);
                        return cs;
                    },
                    (CallableStatementCallback<Void>) cs -> {
                        cs.execute();
                        return null;
                    }
            );
        } catch (Exception ex) {
            log.error("Error in authIn uuid={}, api={}", uuid, api, ex);
            throw ex;
        }
    }

    // =========================
    // PROC5 - Auth Out
    // =========================
    public void authOut(String uuid, String srcIn, String srcOut, long processTime) {
        try {
            jdbcTemplate.execute(
                    (CallableStatementCreator) con -> {
                        log.info("Calling PROC5_AUTH_OUT uuid={}", uuid);
                        CallableStatement cs = con.prepareCall(
                                "{call " + SCHEMA + ".PROC5_AUTH_OUT(?,?,?,?)}"
                        );
                        cs.setString(1, uuid);
                        cs.setString(2, srcIn);
                        cs.setString(3, srcOut);
                        cs.setLong(4, processTime);
                        return cs;
                    },
                    (CallableStatementCallback<Void>) cs -> {
                        cs.execute();
                        return null;
                    }
            );
        } catch (Exception ex) {
            log.error("Error in authOut uuid={}", uuid, ex);
            throw ex;
        }
    }
}