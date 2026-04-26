1. Create BOM Project (Independent Repo)
Step 1: Create folder
mkdir sb-02-parent-bom
cd sb-02-parent-bom
touch pom.xml
Step 2: BOM pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.sb02.platform</groupId>
    <artifactId>sb-02-parent-bom</artifactId>
    <version>1.0.0</version>
    <packaging>pom</packaging>

    <dependencyManagement>
        <dependencies>

            <!-- Import Spring Boot BOM -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>3.2.5</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!-- Custom managed dependency -->
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>1.18.30</version>
            </dependency>

        </dependencies>
    </dependencyManagement>

</project>
Step 3: Install BOM to local repo
mvn clean install

Now BOM is available for any project.

2. Final BOM Structure
sb-02-parent-bom
└── pom.xml

This is your central dependency control system.

3. Create Separate Microservice Project
Step 1: Create project

Use Spring Initializr

Project: Maven
Group: com.sb02.app
Artifact: sb-02-user-service
Dependencies:
Spring Web

Download and extract:

unzip sb-02-user-service.zip
cd sb-02-user-service
4. Connect Service to BOM
Step 2: Update pom.xml

Add BOM import:

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.sb02.platform</groupId>
            <artifactId>sb-02-parent-bom</artifactId>
            <version>1.0.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
Step 3: Remove versions from dependencies
<dependencies>

    <!-- Version comes from BOM -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Also from BOM -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>

</dependencies>
5. Run the Service
mvn spring-boot:run
6. Verify BOM is Working
mvn dependency:tree

Check:

No versions in service
Versions resolved from BOM
7. Final Architecture
sb-02-parent-bom (repo-1)
│
└── controls versions

----------------------------------

sb-02-user-service (repo-2)
│
└── imports BOM
8. Key Rules (Important)
BOM → only versions
Service → no versions
Always version BOM (1.0.0, 1.1.0)
9. Next Step (High Value)

Expand BOM:

PostgreSQL
Kafka
Micrometer (metrics)

Then:

→ build 2 services using same BOM (user + order)