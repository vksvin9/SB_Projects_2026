package com.vin.repo;

import com.vin.model.AppDataRequest;
import com.vin.model.AppDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AppRepository {

    private final JdbcTemplate jdbcTemplate;

    // Insert single
    public int insert(AppDataRequest req) {
        return jdbcTemplate.update(
                "INSERT INTO APP_DATA (REQ_ID, NAME, TYPE) VALUES (?, ?, ?)",
                req.getReqId(),
                req.getName(),
                req.getType()
        );
    }

    // Batch insert
    public void insertBatch(List<AppDataRequest> list) {
        jdbcTemplate.batchUpdate(
                "INSERT INTO APP_DATA (REQ_ID, NAME, TYPE) VALUES (?, ?, ?)",
                list,
                list.size(),
                (ps, req) -> {
                    ps.setString(1, req.getReqId());
                    ps.setString(2, req.getName());
                    ps.setString(3, req.getType());
                }
        );
    }

    // Fetch
    public List<AppDataResponse> fetch(int limit, int offset) {

        String sql = """
            SELECT * FROM (
                SELECT A.*, ROW_NUMBER() OVER (ORDER BY CREATED_AT DESC) RN
                FROM APP_DATA A
            )
            WHERE RN > ? AND RN <= ?
        """;

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> map(rs),
                offset,
                offset + limit
        );
    }

    private AppDataResponse map(ResultSet rs) throws java.sql.SQLException {
        return AppDataResponse.builder()
                .id(rs.getLong("ID"))
                .reqId(rs.getString("REQ_ID"))
                .name(rs.getString("NAME"))
                .type(rs.getString("TYPE"))
                .status(rs.getString("STATUS_FLAG"))
                .retryCount(rs.getInt("RETRY_COUNT"))
                .createdAt(rs.getTimestamp("CREATED_AT").toLocalDateTime())
                .build();
    }
}