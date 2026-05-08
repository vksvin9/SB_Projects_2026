INSERT INTO users
(id, username, password, email, role, enabled, created_at, updated_at)
VALUES
(
  1,
  'admin',
  '$2a$10$7EqJtq98hPqEX7fNZaFWoOHi5M1sKj8u6R9GqQ8K9sW6xG1YfJ8y',
  'admin@test.com',
  'ADMIN',
  true,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP
);

INSERT INTO users
(id, username, password, email, role, enabled, created_at, updated_at)
VALUES
(
  2,
  'student',
  '$2a$10$7EqJtq98hPqEX7fNZaFWoOHi5M1sKj8u6R9GqQ8K9sW6xG1YfJ8y',
  'student@test.com',
  'STUDENT',
  true,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP
);

INSERT INTO students
(id, full_name, mobile, address,
 user_id, created_at, updated_at)
VALUES
(
  1,
  'Vin Student',
  '9999999999',
  'Nagpur',
  2,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP
);