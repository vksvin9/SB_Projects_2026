1️⃣ Stop old container (if running)
	docker ps
	Copy container ID, then:
	docker stop <container_id>
2️⃣ Remove old container
	docker rm <container_id>
	🔥 Shortcut (recommended)
	Remove all containers for this image in one shot:
	docker rm -f $(docker ps -aq --filter ancestor=vin/sb-09-docker2-api) 2>/dev/null
3️⃣ Rebuild image (after YAML change)
	docker build -t vin/sb-09-docker2-api .
4️⃣ Run container (simple logging)
	docker run -p 8080:8080 vin/sb-09-docker2-api
🔁 Full flow (copy-paste ready)
	docker rm -f $(docker ps -aq --filter ancestor=vin/sb-09-docker2-api) 2>/dev/null
	docker build -t vin/sb-09-docker2-api .
	docker run -p 8080:8080 vin/sb-09-docker2-api
🔍 Verify logs inside container
	docker exec -it <container_id> bash
	ls /logs

---

# Test
curl http://localhost:8080/api/posts

---

Where your logs are now

Since you switched to YAML-only logging and no volume, logs are stored inside the container filesystem.

📍 Location inside container
/logs/app.log
🔍 How to access logs
1️⃣ Get container ID
docker ps
2️⃣ Enter container
docker exec -it <container_id> bash
3️⃣ Go to logs folder
cd /logs
ls

👉 You should see:

app.log
app.log.1
app.log.2
...
4️⃣ View logs
cat app.log

or live:

tail -f app.log
⚠️ Important
These logs are TEMPORARY

👉 If container stops or is removed:

Logs will be LOST ❌
🧠 Alternative (quick view without entering container)
docker exec <container_id> ls /logs

---
Good. Now move to persistent + stable logging (no breakage).

🎯 Goal
Keep current working setup ✔
Add volume safely ✔
Logs persist outside container ✔
1️⃣ Create host folder
mkdir C:/docker-logs
2️⃣ Stop & remove old container
docker rm -f $(docker ps -aq --filter ancestor=vin/sb-09-docker2-api) 2>/dev/null
3️⃣ Run with volume
docker run -p 8080:8080 -v C:/docker-logs:/logs vin/sb-09-docker2-api
🧠 What changed
/logs (container)
   ↓
C:/docker-logs (your system)
🔍 Verify
1. Hit API
curl http://localhost:8080/api/posts
2. Check folder
C:/docker-logs/app.log
📦 Rotation behavior (your YAML)
app.log
app.log.1
app.log.2
...
(max 10 files, 10MB each)
⚠️ Important
Now logs persist after container delete ✔
No OneDrive issue ✔
No logback conflict ✔
🧠 Stable Architecture (what you now have)
Spring Boot → YAML logging → /logs
                           ↓
Docker volume → C:/docker-logs