# Phase-Wise Development Plan: Spring Boot AI Chatbot with Ollama
This project is designed as your first AI integration project using Spring Boot, Spring AI, and Ollama.
---
# Final Outcome
You will build:
* Local AI chatbot running on your machine
* Spring Boot backend
* Ollama-hosted local LLM
* Thymeleaf + Bootstrap frontend
* Chat history
* Error handling
* Production-style layered architecture
---
# Technology Stack
| Layer          | Technology       |
| -------------- | ---------------- |
| Language       | Java 21          |
| Framework      | Spring Boot 3.x  |
| AI Integration | Spring AI        |
| LLM Runtime    | Ollama           |
| Model          | llama3.2         |
| Frontend       | Thymeleaf        |
| Styling        | Bootstrap 5      |
| Build Tool     | Maven            |
| Config         | application.yml  |
| Utilities      | Lombok, DevTools |
---
# Phase 0: AI Fundamentals
## Objective
Understand how the components interact.
## Concepts to Learn
* Large Language Model (LLM)
* Prompt
* Response
* Tokens
* Context Window
* Temperature
* Local vs Cloud Models
## Architecture
```text
User -> Browser -> Spring Boot -> Spring AI -> Ollama -> Local Model -> Response
```
## Deliverable
Clear understanding of:
* What Ollama does
* What Spring AI does
* How prompts and responses flow
---
# Phase 1: Install Ollama
## Objective
Set up local AI runtime.
## Step 1: Download Ollama
[Ollama Official Website](https://ollama.com?utm_source=chatgpt.com)
Install for Windows.
## Step 2: Verify Installation
```bash
ollama --version
```
## Step 3: Pull a Model
```bash
ollama pull llama3.2
```
## Step 4: Run a Quick Test
```bash
ollama run llama3.2
```
Type:
```text
What is Java?
```
Exit with:
```text
/bye
```
## Step 5: Verify API
Open in browser:
[Ollama Local API Endpoint](http://localhost:11434?utm_source=chatgpt.com)
You should see a simple status message.
## Deliverable
* Ollama installed
* Model downloaded
* Local API running
---
# Phase 2: Create Spring Boot Project
## Objective
Generate base application.
## Dependencies
* Spring Web
* Thymeleaf
* Spring AI Ollama
* Lombok
* DevTools
## Project Settings
* Name: `vin-ai-chatbot`
* Package: `com.vin`
* Java: 21
* Build: Maven
## Deliverable
Application starts successfully.
---
# Phase 3: Configure application.yml
## Objective
Connect Spring Boot to Ollama.
## Configuration
* Server port
* Application name
* Ollama base URL
* Model name
* Timeout settings
## Deliverable
Spring Boot recognizes Ollama model configuration.
---
# Phase 4: Create Basic Package Structure
## Objective
Set up maintainable architecture.
```text
com.vin
├── controller
├── service
├── service.impl
├── dto
├── exception
├── config
├── util
└── VinAiChatbotApplication
```
## Deliverable
Organized project structure.
---
# Phase 5: Build AI Service Layer
## Objective
Create service that sends prompts to Ollama.
## Responsibilities
* Accept user message
* Call Spring AI `ChatClient`
* Return AI response
## Deliverable
Service method:
```java
String ask(String prompt)
```
---
# Phase 6: Build REST API
## Objective
Expose chatbot endpoint.
## Endpoint
```http
POST /api/chat
```
## Request
```json
{
  "message": "Explain Spring Boot"
}
```
## Response
```json
{
  "response": "Spring Boot is..."
}
```
## Deliverable
API testable with Postman.
---
# Phase 7: Test Backend
## Objective
Validate end-to-end AI communication.
## Flow
1. Send HTTP request
2. Spring Boot receives prompt
3. Calls Ollama
4. Returns model response
## Deliverable
Functional REST API chatbot.
---
# Phase 8: Build Web UI
## Objective
Create user-friendly chat interface.
## Features
* Prompt textarea
* Send button
* Response area
* Loading spinner
## Technologies
* Thymeleaf
* Bootstrap
## Deliverable
Chat accessible in browser.
---
# Phase 9: Add Chat History
## Objective
Store conversation in session.
## Deliverable
Previous messages remain visible until cleared.
---
# Phase 10: Add Clear Chat Feature
## Objective
Reset session history.
## Deliverable
“Clear Chat” button empties conversation.
---
# Phase 11: Exception Handling
## Objective
Handle failures gracefully.
## Scenarios
* Ollama not running
* Timeout
* Model missing
* Invalid input
## Deliverable
Friendly error messages.
---
# Phase 12: Logging
## Objective
Track requests and responses.
## Logs
* Incoming prompt
* AI response time
* Errors
## Deliverable
Useful console logs for debugging.
---
# Phase 13: Prompt Templates
## Objective
Control AI behavior.
## Example
```text
You are a Java expert. Explain clearly.
Question: {userPrompt}
```
## Deliverable
Consistent, high-quality responses.
---
# Phase 14: UI Enhancements
## Features
* Enter-to-send
* Auto-scroll
* Copy response
* Dark mode
* Typing animation
---
# Phase 15: Model Switching
## Objective
Use different local models.
## Examples
* `llama3.2`
* `mistral`
* `phi3`
## Deliverable
Model name configurable in YAML.
---
# Phase 16: Dockerization
## Objective
Containerize the application.
## Deliverable
Run chatbot with Docker.
---
# Phase 17: Production Improvements
## Enhancements
* Rate limiting
* Security
* Persistent chat storage
* Monitoring
---
# Recommended Development Order
| Step | Phase                                |
| ---- | ------------------------------------ |
| 1    | Phase 0 – AI Fundamentals            |
| 2    | Phase 1 – Install Ollama             |
| 3    | Phase 2 – Create Spring Boot Project |
| 4    | Phase 3 – Configuration              |
| 5    | Phase 4 – Package Structure          |
| 6    | Phase 5 – AI Service                 |
| 7    | Phase 6 – REST API                   |
| 8    | Phase 7 – Backend Testing            |
| 9    | Phase 8 – Web UI                     |
| 10   | Phase 9 – Chat History               |
| 11   | Phase 10+ – Enhancements             |
---
# Minimal Viable Product (MVP)
Complete through Phase 8 to have:
* Working local AI chatbot
* Spring Boot backend
* Ollama integration
* Browser-based chat UI
---
# Learning Outcomes
After completing this project, you will understand:
* How local LLMs work
* How to integrate AI into Spring Boot
* Prompt engineering basics
* REST API integration
* Web UI development
---
# Best Models for Beginners
| Model      | RAM Requirement | Use Case                     |
| ---------- | --------------: | ---------------------------- |
| `llama3.2` |         ~4–8 GB | Best starting point          |
| `phi3`     |     Lightweight | Faster on lower-end systems  |
| `mistral`  |           ~8 GB | Strong general-purpose model |
---
# Typical Commands
```bash
ollama pull llama3.2
ollama list
ollama run llama3.2
ollama ps
```
---
# Suggested Milestones
| Milestone | Result                            |
| --------- | --------------------------------- |
| M1        | Ollama installed                  |
| M2        | Spring Boot app starts            |
| M3        | REST endpoint returns AI response |
| M4        | Browser chat works                |
| M5        | Chat history and clear button     |
---
# Project Folder Name
```text
vin-ai-chatbot
```
---
# Recommended First Implementation Scope
For your first AI project, complete only:
* Phase 1: Ollama Setup
* Phase 2: Spring Boot Setup
* Phase 3: Configuration
* Phase 4: Package Structure
* Phase 5: AI Service
* Phase 6: REST API
* Phase 7: Testing
* Phase 8: Web UI
This provides the strongest foundation for future AI-powered applications such as document Q&A, resume analyzers, coding assistants, and enterprise copilots.