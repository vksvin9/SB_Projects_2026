#DB
Option 1 — Remove old container (recommended)
docker rm oracle-db
#Then run again:
docker run -d --name oracle-db -p 1521:1521 -e ORACLE_PASSWORD=Oracle123 -e APP_USER=app_user -e APP_USER_PASSWORD=app_pass -e ORACLE_MEMORY=1024 gvenzl/oracle-free:23-slim

🔹 Option 2 — Start existing container
docker start oracle-db
👉 If it starts successfully → done
#Check status
docker ps -a
=====================================================================================================================

#Case 2 — Oracle running in another container (same network)
# create a private network(one-time) so containers can communicate
docker network create my-net
# attach oracle-db container to that network (so it’s reachable by name)
docker network connect my-net oracle-db
# build Spring Boot image 
docker build -t vin/sb-09-docker4-db .
# run Spring Boot container on same network, expose port, and load DB config from .env
docker run -d --name sb-09-docker4-db-wo-compose --network my-net -p 8080:8080 --env-file .env vin/sb-09-docker4-db
# start existing container (daily use, no long command needed)
docker start sb-09-docker4-db-wo-compose

#If fails
docker ps -a
docker logs oracle-db
docker logs sb-09-docker4-db-wo-compose

=====================================================================================================================

🧭 Scenario 1 — DB on same machine (host)
	App container → Host machine → Oracle DB
	Steps
	# 1. ensure Oracle is running locally (outside Docker)

	# 2. create .env
	DB_HOST=host.docker.internal
	DB_PORT=1521
	DB_SERVICE=FREEPDB1
	DB_USER=app_user
	DB_PASSWORD=app_pass

	# 3. run app
	docker run -d --name sb-09-docker4-db-wo-compose -p 8080:8080 --env-file .env vin/sb-09-docker4-db
	Key point
	host.docker.internal → bridge between container and host
🧭 Scenario 2 — DB in another Docker container
	App container → Docker network → Oracle container
	Steps
	# 1. create network (once)
	docker network create my-net

	# 2. run or connect oracle
	docker run -d --name oracle-db --network my-net -p 1521:1521 -e ORACLE_PASSWORD=Oracle123 gvenzl/oracle-free:23-slim
	# OR if already running
	docker network connect my-net oracle-db

	# 3. create .env
	DB_HOST=oracle-db
	DB_PORT=1521
	DB_SERVICE=FREEPDB1
	DB_USER=app_user
	DB_PASSWORD=app_pass

	# 4. run app
	docker run -d --name sb-09-docker4-db-wo-compose --network my-net -p 8080:8080 --env-file .env vin/sb-09-docker4-db
	Key point
	oracle-db → acts like DNS hostname
🧭 Scenario 3 — DB on remote server (real-world)
	App container → Internet/LAN → Remote Oracle server
	Steps
	# 1. get DB server details (IP/domain)

	# 2. create .env
	DB_HOST=192.168.1.100   # or db.company.com
	DB_PORT=1521
	DB_SERVICE=FREEPDB1
	DB_USER=app_user
	DB_PASSWORD=app_pass

	# 3. run app
	docker run -d --name sb-09-docker4-db-wo-compose -p 8080:8080 --env-file .env vin/sb-09-docker4-db
	Must verify
	# connectivity
	ping 192.168.1.100

	# port open
	telnet 192.168.1.100 1521
	
=====================================================================================================================
Goal:Store DB credentials securely (not hardcoded), while staying practical for your current Docker setup.
🧠 First principle
	Never hardcode secrets in:
	❌ application.yml
	❌ source code
	❌ Git repo
	🧭 Options (ordered by maturity)
🔹 Option 1 — .env file (what you’re using now) ✅
	Where
	Project root:
	sb-09-docker4-db-wo-compose/
	  └── .env
	Example
	DB_HOST=oracle-db
	DB_PORT=1521
	DB_SERVICE=FREEPDB1
	DB_USER=app_user
	DB_PASSWORD=app_pass
	Run
	docker run --env-file .env ...
	Security rules
	✔ Add .env to .gitignore
	✔ Keep locally only
	✔ Good for dev
🔹 Option 2 — Environment variables (no file)
	docker run -e DB_USER=app_user -e DB_PASSWORD=app_pass ...
	Pros
	✔ No file
	✔ CI/CD friendly
	Cons
	❌ visible in command history
	❌ messy for many variables
🔹 Option 3 — Docker secrets (better, semi-prod)
	Create secret
	echo "app_pass" | docker secret create db_password -
	Use in container
	Mounted inside container as file:
	/run/secrets/db_password
	In Spring Boot
	spring:
	  datasource:
		password: ${DB_PASSWORD_FILE:/run/secrets/db_password}
	Pros
	✔ Not exposed in env
	✔ Better security
	Cons
	❌ Needs Docker Swarm
🔹 Option 4 — External Secret Manager (production)
	Examples:
	AWS Secrets Manager
	HashiCorp Vault
	Azure Key Vault
	Flow
	App → Secret Manager → fetch credentials at runtime
	Pros
	✔ Highly secure
	✔ Rotatable secrets
	✔ Enterprise standard
🔹 Option 5 — Spring profiles + encrypted config
	spring:
	  profiles: prod
	  datasource:
		password: ENC(xxx)
	Use:
	Jasypt (encryption library)
	🎯 What YOU should use now
	Current stage → Best choice
	✔ Use .env (you are correct)
	✔ Add .gitignore
	✔ Keep secrets out of repo
	⚠️ Must do (important)
	Add .gitignore
	.env
	🧠 Upgrade path (important for career)
	Now       → .env ✔
	Next      → Docker secrets
	Later     → Vault / cloud secret manager
	🔐 Extra protection tips
	✔ Use different creds for dev/prod
	✔ Don’t reuse root/system passwords
	✔ Rotate passwords regularly
	✔ Restrict DB user privileges
	🚀 If you want next
	I can show:
	👉 .env + Spring profiles (dev vs prod)
	👉 or Vault integration (real-world level)

=====================================================================================================================
Below are targeted upgrades you can apply to your current setup (Spring Boot + Oracle + Docker, no compose) to improve performance, scalability, and stability. Prioritize top to bottom.
🔧 1) Database & Connection Pool (highest impact)
	Tune Hikari (in application.yml)
	spring:
	  datasource:
	    hikari:
	      maximum-pool-size: 10        # start with 10 (adjust based on CPU/DB)
	      minimum-idle: 2
	      connection-timeout: 30000
	      idle-timeout: 600000
	      max-lifetime: 1800000
	
	Rule of thumb
	
	pool size ≈ (CPU cores * 2) + disk factor
	Use Oracle sequence (avoid IDENTITY)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	Add indexes (critical)
	CREATE INDEX idx_users_name ON users(name);
⚙️ 2) JPA / Hibernate tuning
	spring:
	  jpa:
	    open-in-view: false            # avoid long transactions
	    properties:
	      hibernate:
	        jdbc:
	          batch_size: 50           # bulk inserts/updates
	        order_inserts: true
	        order_updates: true
	Avoid
	❌ N+1 queries (use fetch join)
	❌ returning entities directly for large responses
🚀 3) API layer optimizations
	Add pagination (must for scale)
	@GetMapping
	public ApiResponse<Page<User>> getAll(Pageable pageable) {
	    return ApiResponse.success(repo.findAll(pageable));
	}
	DTO instead of entity (lighter payload)
	Controller → DTO → Service → Entity
⚡ 4) Caching (quick win)
	Enable simple cache
	@EnableCaching
	@Cacheable("users")
	public List<User> findAll() { ... }
	Upgrade later → Redis
🧠 5) Logging (reduce overhead)
	Current
	com.vin: DEBUG ❌ (heavy)
	Change
	logging:
	  level:
	    root: INFO
	    com.vin: INFO
👉 Keep DEBUG only during debugging
🧱 6) Docker optimizations
	Use lighter runtime image
	FROM eclipse-temurin:21-jre-jammy
	Limit resources
	ENTRYPOINT ["java","-Xms256m","-Xmx512m","-jar","app.jar"]
	Run multiple instances (horizontal scaling)
	docker run ... --name app-1 ...
	docker run ... --name app-2 ...
🌐 7) Add load balancing (basic scaling)
	Use Nginx:
	Client → Nginx → multiple app containers
🔄 8) Retry + resilience
	Add retry for DB/API failures:
	spring:
	  datasource:
	    hikari:
	      initialization-fail-timeout: 60000	
	Or use Resilience4j later.
🧪 9) Health & readiness
	Add Actuator:
	spring-boot-starter-actuator
	management:
	  endpoints:
	    web:
	      exposure:
	        include: health,info
🧠 10) JVM tuning
	ENTRYPOINT ["java",
	"-XX:+UseG1GC",
	"-Xms256m",
	"-Xmx512m",
	"-jar","app.jar"]
📊 11) Observability (next level)
	Add:
	Micrometer
	Prometheus
	Grafana