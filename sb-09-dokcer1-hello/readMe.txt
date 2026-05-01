STEP 0 — What you have before Docker
	Your Code (Java 21)
	        ↓
	Maven Build
	        ↓
	JAR file (target/*.jar)
	
	👉 This JAR is the input to Docker
		
1️⃣ Dockerfile — Word-by-Word (Execution Order)
	
	Docker reads top → bottom and creates layers
	
	FROM eclipse-temurin:21-jdk-jammy
	WORKDIR /app
	COPY target/sb-09-docker1-hello-0.0.1-SNAPSHOT.jar sb-09-docker1-hello-0.0.1-SNAPSHOT.jar
	EXPOSE 8080
	ENTRYPOINT ["java","-jar","sb-09-docker1-hello-0.0.1-SNAPSHOT.jar"]
	
	1) BASE IMAGE
	FROM eclipse-temurin:21-jdk-jammy
		FROM → starting point of image
		eclipse-temurin:21-jdk-jammy →
		Java 21 runtime
		Ubuntu Jammy OS
		
		👉 This gives:
		
		Linux OS + Java 21 already installed
	2) WORKING DIRECTORY
	WORKDIR /app
		Sets default folder inside container
		If not exists → Docker creates it
		
		👉 Equivalent to:
		
		mkdir /app
		cd /app
	3) COPY JAR
	COPY target/sb-09-docker1-hello-0.0.1-SNAPSHOT.jar sb-09-docker1-hello-0.0.1-SNAPSHOT.jar
		COPY → moves file from local → container
		Source: target/...jar
		Destination: /app/...jar
		
		👉 After this:
		
		Container file system:
		 /app/sb-09-docker1-hello-0.0.1-SNAPSHOT.jar
	4) EXPOSE PORT
	EXPOSE 8080
		Tells Docker: app uses port 8080
		Only documentation, not actual exposure
	5) ENTRYPOINT (Startup Command)
	ENTRYPOINT ["java","-jar","sb-09-docker1-hello-0.0.1-SNAPSHOT.jar"]
		Runs when container starts
		Equivalent to:
		java -jar sb-09-docker1-hello-0.0.1-SNAPSHOT.jar
		🔁 Dockerfile Outcome
		Dockerfile → Docker Image (Blueprint)

2️⃣ Command 1 — Build Image
	docker build -t vin/sb-09-docker1-hello .
		docker → CLI tool
		build → create image
		-t → tag (name the image)
		vin/sb-09-docker1-hello
		vin → namespace
		sb-09-docker1-hello → project/image name
		. → current folder (build context)
		What happens internally
			1. Read Dockerfile
			2. Pull base image (Java 21)
			3. Create /app
			4. Copy JAR
			5. Save ENTRYPOINT
			6. Build layers
		Result
			docker images
			vin/sb-09-docker1-hello   latest
	👉 This is your portable app package
	
3️⃣ Command 2 — Run Container
	docker run -p 8080:8080 vin/sb-09-docker1-hello
		docker → CLI
		run → create + start container
		-p → port mapping
		8080:8080
		left → your system
		right → container
		vin/sb-09-docker1-hello → image to run
		What happens internally
			1. Create container from image
			2. Execute ENTRYPOINT:
			   java -jar sb-09-docker1-hello-0.0.1-SNAPSHOT.jar
			3. Spring Boot starts
			4. Listens on port 8080
			5. Docker maps port → localhost:8080
		🔌 Port Mapping Deep Understanding
			-p 8080:8080
			Layer	Port
			Your Laptop	8080
			Container	8080
		👉 Request flow:
		Browser → localhost:8080 → Docker → Container → Spring Boot
🔁 Full Lifecycle (Important)
	Code → JAR → Dockerfile → Image → Container → Running App
	🧠 Mental Model (Must Remember)
	Dockerfile = Recipe
	Image      = Packed Food
	Container  = Running Meal
🔍 Verify Everything
	docker ps	
	→ running containers
	docker logs <id>	
	→ Spring Boot logs