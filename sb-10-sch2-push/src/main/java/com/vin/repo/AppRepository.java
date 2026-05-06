package com.vin.repo;

import com.vin.model.AppDataRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class AppRepository {

	private final JdbcTemplate jdbcTemplate;

	// =========================
	// PROC1 → Get Single
	// =========================
	public Map<String, Object> getSingle() {

		return jdbcTemplate.execute(new ConnectionCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInConnection(Connection con) throws SQLException {

				try (CallableStatement cs = con.prepareCall("{call GET_SINGLE_RECORD_PROC(?,?,?,?)}")) {

					cs.registerOutParameter(1, Types.NUMERIC);
					cs.registerOutParameter(2, Types.VARCHAR);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4, Types.CHAR);

					cs.execute();

					if (cs.getObject(1) == null)
						return null;

					Map<String, Object> m = new HashMap<>();
					m.put("id", cs.getLong(1));
					m.put("reqId", cs.getString(2));
					m.put("name", cs.getString(3));
					m.put("type", "single");

					return m;
				}
			}
		});
	}

	// =========================
	// PROC2 → Get Batch
	// =========================
	public List<Map<String, Object>> getBatch(int limit) {

		return jdbcTemplate.execute(new ConnectionCallback<List<Map<String, Object>>>() {
			@Override
			public List<Map<String, Object>> doInConnection(Connection con) throws SQLException {

				try (CallableStatement cs = con.prepareCall("{call GET_MULTI_RECORD_PROC(?,?,?)}")) {

					cs.setInt(1, limit);
					cs.setInt(2, 0);
					cs.registerOutParameter(3, Types.REF_CURSOR);

					cs.execute();

					try (ResultSet rs = (ResultSet) cs.getObject(3)) {

						List<Map<String, Object>> list = new ArrayList<>();

						while (rs.next()) {
							Map<String, Object> m = new HashMap<>();
							m.put("id", rs.getLong("ID"));
							m.put("reqId", rs.getString("REQ_ID"));
							m.put("name", rs.getString("NAME"));
							m.put("type", rs.getString("TYPE"));
							list.add(m);
						}

						return list;
					}
				}
			}
		});
	}

	// =========================
	// PROC3 → Update
	// =========================
	public void update(Long id, String status, String resp) {

		jdbcTemplate.update(con -> {
			CallableStatement cs = con.prepareCall("{call UPDATE_RECORD_PROC(?,?,?)}");
			cs.setLong(1, id);
			cs.setString(2, status);
			cs.setString(3, resp);
			return cs;
		});
	}

	// =========================
	// ETL → Single Insert (FIX)
	// =========================
	public void insert(String reqId, String name, String type) {

		jdbcTemplate.update("INSERT INTO APP_DATA (REQ_ID, NAME, TYPE) VALUES (?, ?, ?)", reqId, name, type);
	}

	// =========================
	// ETL → Batch Insert (FIXED)
	// =========================
	public int[] insertBatch(List<AppDataRequest> list) {

		String sql = "INSERT INTO APP_DATA (REQ_ID,NAME,TYPE) VALUES (?,?,?)";

		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				AppDataRequest r = list.get(i);
				ps.setString(1, r.getReqId());
				ps.setString(2, r.getName());
				ps.setString(3, r.getType());
			}

			@Override
			public int getBatchSize() {
				return list.size();
			}
		});
	}

	// =========================
	// ETL VERIFY → Latest Records (FIXED)
	// =========================
	public List<Map<String, Object>> fetchLatest(int limit) {

		return jdbcTemplate.query(
				"SELECT ID,REQ_ID,NAME,TYPE,STATUS_FLAG,CREATED_AT FROM APP_DATA ORDER BY CREATED_AT DESC FETCH FIRST ? ROWS ONLY",
				new Object[] { limit }, (rs, rowNum) -> {
					Map<String, Object> m = new HashMap<>();
					m.put("id", rs.getLong("ID"));
					m.put("reqId", rs.getString("REQ_ID"));
					m.put("name", rs.getString("NAME"));
					m.put("type", rs.getString("TYPE"));
					m.put("status", rs.getString("STATUS_FLAG"));
					m.put("createdAt", rs.getTimestamp("CREATED_AT"));
					return m;
				});
	}
}