package com.vin.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;

@Repository
public class UserProcedureRepository {

	private final ProcedureExecutor executor;

	public UserProcedureRepository(ProcedureExecutor executor) {
		this.executor = executor;
	}

	// 1. INSERT USER → insert_user_proc
	public void insertUser(String name, String email) {
		executor.execute(conn -> {
			try (CallableStatement cs = conn.prepareCall("{call user_db.insert_user_proc(?, ?)}")) {

				cs.setString(1, name);
				cs.setString(2, email);

				cs.execute();
			}
			return null;
		});
	}

	// 2. UPDATE USER → update_user_proc
	public void updateUser(Long id, String name, String email) {
		executor.execute(conn -> {
			try (CallableStatement cs = conn.prepareCall("{call user_db.update_user_proc(?, ?, ?)}")) {

				cs.setLong(1, id);
				cs.setString(2, name);
				cs.setString(3, email);

				cs.execute();
			}
			return null;
		});
	}

	// 3. GET USER (NO CURSOR 1)
	public Map<String, String> getUserNoCursor1(Long id) {
		return executor.execute(conn -> {
			try (CallableStatement cs = conn.prepareCall("{call user_db.get_user_no_cursor_1(?, ?, ?)}")) {

				cs.setLong(1, id);
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);

				cs.execute();

				Map<String, String> result = new HashMap<>();
				result.put("name", cs.getString(2));
				result.put("email", cs.getString(3));
				return result;
			}
		});
	}

	// 4. GET USER (NO CURSOR 2)
	public String getUserEmail(Long id) {
		return executor.execute(conn -> {
			try (CallableStatement cs = conn.prepareCall("{call user_db.get_user_no_cursor_2(?, ?)}")) {

				cs.setLong(1, id);
				cs.registerOutParameter(2, Types.VARCHAR);

				cs.execute();

				return cs.getString(2);
			}
		});
	}

	// 5. GET USER (CURSOR 1)
	public Map<String, Object> getUserCursor1(Long id) {
		return executor.execute(conn -> {
			try (CallableStatement cs = conn.prepareCall("{call user_db.get_user_cursor_1(?, ?, ?)}")) {

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
			}
		});
	}

	// 6. GET USER (CURSOR 2 - MULTIPLE CURSORS)
	public Map<String, Object> getUserCursor2(Long id) {
		return executor.execute(conn -> {
			try (CallableStatement cs = conn.prepareCall("{call user_db.get_user_cursor_2(?, ?, ?, ?)}")) {

				cs.setLong(1, id);
				cs.registerOutParameter(2, OracleTypes.CURSOR);
				cs.registerOutParameter(3, OracleTypes.CURSOR);
				cs.registerOutParameter(4, Types.VARCHAR);

				cs.execute();

				String error = cs.getString(4);
				if (error != null) {
					throw new RuntimeException(error);
				}

				try (ResultSet c1 = (ResultSet) cs.getObject(2); ResultSet c2 = (ResultSet) cs.getObject(3)) {

					List<Map<String, Object>> list1 = new ArrayList<>();
					while (c1.next()) {
						Map<String, Object> row = new HashMap<>();
						row.put("id", c1.getLong("id"));
						row.put("name", c1.getString("name"));
						list1.add(row);
					}

					Map<String, Object> detail = new HashMap<>();
					if (c2.next()) {
						detail.put("id", c2.getLong("id"));
						detail.put("name", c2.getString("name"));
						detail.put("email", c2.getString("email"));
					}

					Map<String, Object> result = new HashMap<>();
					result.put("list", list1);
					result.put("detail", detail);

					return result;
				}
			}
		});
	}
}
