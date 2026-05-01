package com.vin.repository;

import com.vin.exception.DataLimitExceededException;
import com.vin.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Employee> fetchByDept(String dept) {

        return jdbcTemplate.execute((Connection conn) -> {

            CallableStatement cs =
                    conn.prepareCall("{call user_db.GET_EMP_BY_DEPT(?, ?)}");

            cs.setString(1, dept);
            cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);

            return cs;

        }, (CallableStatement cs) -> {

            cs.execute();

            List<Employee> result = new ArrayList<>(100);

            try (ResultSet rs = (ResultSet) cs.getObject(2)) {

                int count = 0;

                while (rs.next()) {

                    count++;

                    // THROW IMMEDIATELY when >100 detected
                    if (count > 100) {
                        throw new DataLimitExceededException("DATA > 100 NOT ALLOWED");
                    }

                    Employee e = new Employee();
                    e.setId(rs.getLong("ID"));
                    e.setName(rs.getString("NAME"));
                    e.setDept(rs.getString("DEPT"));
                    e.setSalary(rs.getDouble("SALARY"));

                    result.add(e);
                }
            }

            return result;
        });
    }
}