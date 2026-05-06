===============================================================================================================
AuthGatewayService
	PROC1(API1):RegisterUsersCredentials(Internal)=>UsersCredentialsTable(Sr|Users|Password|Timestamp)
		SB-IN(Users,Password-hash) >> PROCESS(Insert) >> OUT(Success/Error)
	PROC2(API2):RegisterUsersServiceApi(Sr|Users|Serviceapi|Timestamp)
		SB-IN(Users,Serviceapi) >> PROCESS(Insert) >> OUT(Success/Error)
	PROC3(API3):GetLoginToken=>UsersTokenTable(Sr|Users|Token|Timestamp)
		SB-IN(Users,Password) >> PROCESS(Check_Users&Password>TokenValidEOD(Return-Exist/Insert)) >> OUT(Token/Error)
	PROC4(API4):AuthUsersIn=>RateLimitLogsTable(Sr|Uuid|Users|Serviceapi|IP|Input|InTimestamp|SrcInU|SrcOutU|ProcessTimeU|OutTimestampU)
		SB-IN(Token,Uuid,Serviceapi,IP,Input)
	PROCESS
		Authenticate(GetUsersFromTokenE)
		Authorise(GetServiceapiFromUsersE>CheckRateLimitLogsTable1hrE)
		Insert(RateLimitLogsTable:Log(Uuid+Users+Serviceapi+IP+InputPayload+InTimestamp)
		OUT(Success/Error)
	PROC5(API5):AuthUsersOut(Update)
		SB-IN(SrcInPayloadU-enc,SrcOutPayloadU-enc,ProcessTimeU,OutTimestampU)
===============================================================================================================
## login via sqlplus (from host shell)
docker exec -it oracle-db sqlplus system/Oracle123@FREEPDB1
conn system/Oracle123@FREEPDB1;
docker exec -it oracle-db sqlplus user_db/password@FREEPDB1
conn user_db/password@FREEPDB1;
docker exec -it oracle-db sqlplus app_user/app_pass@FREEPDB1
conn app_user/app_pass@FREEPDB1;
===============================================================================================================
1) TABLES (IDENTITY instead of SEQUENCE)
CREATE TABLE UsersCredentialsTable (Sr NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, Users VARCHAR2(100), Password VARCHAR2(200), Timestamp DATE);
CREATE TABLE UsersServiceApiTable (Sr NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, Users VARCHAR2(100), Serviceapi VARCHAR2(100), Timestamp DATE);
CREATE TABLE UsersTokenTable (Sr NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, Users VARCHAR2(100), Token VARCHAR2(200), Timestamp DATE);
CREATE TABLE RateLimitLogsTable (Sr NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, Uuid VARCHAR2(50), Users VARCHAR2(100), Serviceapi VARCHAR2(100), IP VARCHAR2(50), Input CLOB, InTimestamp DATE, SrcInU CLOB, SrcOutU CLOB, ProcessTimeU NUMBER, OutTimestampU DATE);
2) INDEXES (unchanged)
CREATE INDEX IDX_UC_Users ON UsersCredentialsTable(Users);
CREATE INDEX IDX_USR_API ON UsersServiceApiTable(Users, Serviceapi);
CREATE INDEX IDX_TOKEN_Users ON UsersTokenTable(Users);
CREATE UNIQUE INDEX IDX_TOKEN_TOKEN ON UsersTokenTable(Token);
CREATE INDEX IDX_RL_Users_API_TIME ON RateLimitLogsTable(Users, Serviceapi, InTimestamp);
CREATE UNIQUE INDEX IDX_RL_UUID ON RateLimitLogsTable(Uuid);
3) PROC1 – RegisterUsersCredentials
CREATE OR REPLACE PROCEDURE PROC1_REGISTER_Users(p_Users IN VARCHAR2, p_pass IN VARCHAR2) AS BEGIN INSERT INTO UsersCredentialsTable(Users, Password, Timestamp) VALUES (p_Users, p_pass, SYSDATE); END;
/
4) PROC2 – RegisterUsersServiceApi
CREATE OR REPLACE PROCEDURE PROC2_REGISTER_API(p_Users IN VARCHAR2, p_api IN VARCHAR2) AS BEGIN INSERT INTO UsersServiceApiTable(Users, Serviceapi, Timestamp) VALUES (p_Users, p_api, SYSDATE); END;
/
5) PROC3 – GetLoginToken (MERGE optimized)
CREATE OR REPLACE PROCEDURE PROC3_GET_TOKEN(p_Users IN VARCHAR2,p_pass IN VARCHAR2,p_token OUT VARCHAR2) AS v_cnt NUMBER; BEGIN SELECT COUNT(*) INTO v_cnt FROM UsersCredentialsTable WHERE Users=p_Users AND Password=p_pass; IF v_cnt=0 THEN RAISE_APPLICATION_ERROR(-20003,'INVALID_CREDENTIALS'); END IF; BEGIN SELECT Token INTO p_token FROM UsersTokenTable WHERE Users=p_Users AND Timestamp>=TRUNC(SYSDATE) FETCH FIRST 1 ROW ONLY; EXCEPTION WHEN NO_DATA_FOUND THEN p_token:=SYS_GUID(); INSERT INTO UsersTokenTable(Users,Token,Timestamp) VALUES(p_Users,p_token,SYSDATE); END; END;
/
6) PROC4 – AuthUsersIn (optimized)
CREATE OR REPLACE PROCEDURE PROC4_AUTH_IN(p_token IN VARCHAR2,p_uuid IN VARCHAR2,p_api IN VARCHAR2,p_ip IN VARCHAR2,p_input IN CLOB,p_limit IN NUMBER) AS v_Users VARCHAR2(100); v_cnt NUMBER; v_auth NUMBER; BEGIN BEGIN SELECT Users INTO v_Users FROM UsersTokenTable WHERE Token=p_token FETCH FIRST 1 ROW ONLY; EXCEPTION WHEN NO_DATA_FOUND THEN RAISE_APPLICATION_ERROR(-20011,'INVALID_TOKEN'); END; SELECT COUNT(*) INTO v_cnt FROM UsersServiceApiTable WHERE Serviceapi=p_api; IF v_cnt=0 THEN RAISE_APPLICATION_ERROR(-20012,'INVALID_SERVICE'); END IF; SELECT COUNT(*) INTO v_auth FROM UsersServiceApiTable WHERE Users=v_Users AND Serviceapi=p_api; IF v_auth=0 THEN RAISE_APPLICATION_ERROR(-20013,'NOT_AUTHORISED'); END IF; SELECT COUNT(*) INTO v_cnt FROM (SELECT 1 FROM RateLimitLogsTable WHERE Users=v_Users AND Serviceapi=p_api AND InTimestamp>=SYSDATE-INTERVAL '1' HOUR FETCH FIRST p_limit ROWS ONLY); IF v_cnt>=p_limit THEN RAISE_APPLICATION_ERROR(-20002,'RATE_LIMIT'); END IF; INSERT INTO RateLimitLogsTable(Uuid,Users,Serviceapi,IP,Input,InTimestamp) VALUES(p_uuid,v_Users,p_api,p_ip,p_input,SYSDATE); END;
/
7) PROC5 – AuthUsersOut (update)
CREATE OR REPLACE PROCEDURE PROC5_AUTH_OUT(p_uuid IN VARCHAR2, p_src_in IN CLOB, p_src_out IN CLOB, p_time IN NUMBER) AS BEGIN UPDATE RateLimitLogsTable SET SrcInU=p_src_in, SrcOutU=p_src_out, ProcessTimeU=p_time, OutTimestampU=SYSDATE WHERE Uuid=p_uuid; END;
/
===============================================================================================================
GRANT EXECUTE ON PROC1_REGISTER_Users TO app_user;
GRANT EXECUTE ON PROC2_REGISTER_API TO app_user;
GRANT EXECUTE ON PROC3_GET_TOKEN TO app_user;
GRANT EXECUTE ON PROC4_AUTH_IN TO app_user;
GRANT EXECUTE ON PROC5_AUTH_OUT TO app_user;
===============================================================================================================
	✅ 1. UsersCredentialsTable
	SELECT * FROM USER_DB.UsersCredentialsTable ORDER BY Sr DESC;
	🔍 Check specific user
	SELECT * FROM USER_DB.UsersCredentialsTable WHERE Users = 'vin';
	✅ 2. UsersServiceApiTable
	SELECT * FROM USER_DB.UsersServiceApiTable ORDER BY Sr DESC;
	🔍 Check APIs for user
	SELECT * FROM USER_DB.UsersServiceApiTable WHERE Users = 'vin';
	✅ 3. UsersTokenTable
	SELECT * FROM USER_DB.UsersTokenTable ORDER BY Sr DESC;
	🔍 Active token (today)
	SELECT * FROM USER_DB.UsersTokenTable 
	WHERE Users = 'vin' 
	AND Timestamp >= TRUNC(SYSDATE);
	✅ 4. RateLimitLogsTable
	SELECT * FROM USER_DB.RateLimitLogsTable ORDER BY Sr DESC;
	🔍 Last 1 hour logs (rate limit check)
	SELECT * FROM USER_DB.RateLimitLogsTable
	WHERE InTimestamp >= SYSDATE - INTERVAL '1' HOUR
	ORDER BY InTimestamp DESC;
	🔍 Count per API (rate limiting)
	SELECT Users, Serviceapi, COUNT(*) AS CNT
	FROM USER_DB.RateLimitLogsTable
	WHERE InTimestamp >= SYSDATE - INTERVAL '1' HOUR
	GROUP BY Users, Serviceapi;
	🔍 Trace by UUID (full request lifecycle)
	SELECT *
	FROM USER_DB.RateLimitLogsTable
	WHERE Uuid = 'UUID_VALUE';
	🔥 Combined Debug Query (HIGH VALUE)

	Check full flow for a user:

	SELECT u.Users,
		   t.Token,
		   a.Serviceapi,
		   r.Uuid,
		   r.InTimestamp,
		   r.OutTimestampU
	FROM USER_DB.UsersCredentialsTable u
	LEFT JOIN USER_DB.UsersTokenTable t ON u.Users = t.Users
	LEFT JOIN USER_DB.UsersServiceApiTable a ON u.Users = a.Users
	LEFT JOIN USER_DB.RateLimitLogsTable r ON u.Users = r.Users
	WHERE u.Users = 'vin';
	⚡ Quick Health Check (Run This First)
	SELECT 
	 (SELECT COUNT(*) FROM USER_DB.UsersCredentialsTable) AS USERS,
	 (SELECT COUNT(*) FROM USER_DB.UsersServiceApiTable) AS API_MAP,
	 (SELECT COUNT(*) FROM USER_DB.UsersTokenTable) AS TOKENS,
	 (SELECT COUNT(*) FROM USER_DB.RateLimitLogsTable) AS LOGS
	FROM dual;
===============================================================================================================