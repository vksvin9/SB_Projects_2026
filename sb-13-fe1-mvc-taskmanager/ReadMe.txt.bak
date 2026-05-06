========================================Verify-If-React-Exist======================================================
#React Frontend
Step 1 — Verify Node Installation
	Run:
	node -v
	Then:
	npm -v
	If both fail:
	'node' is not recognized
	then Node.js is not installed correctly.
Step 2 — Install Node.js
	Download: Node.js
	Recommended version:
	LTS Version (Recommended for most users)
	During installation:
	IMPORTANT:
	✔ Check:
	Add to PATH
	This is mandatory.
Step 3 — Restart Terminal
	Close:
	CMD
	PowerShell
	IntelliJ Terminal
	VS Code Terminal
	Open again.
Step 4 — Verify Again
	Run:
	node -v
	npm -v
	npx -v
	Expected:
	v22.x.x
	10.x.x
	10.x.x
Step 5 — Create React App
	Now run:
	npx create-react-app task-manager-ui
=========================================Docker+React=====================================================
You can run Node.js, React/Vite, npm, and npx inside Docker without installing Node on Windows, which is common in enterprise teams, microservice environments, CI/CD pipelines, and reproducible development setups.
Option 1 — Quick Temporary Node Container
	Run Node instantly:
	docker run -it --rm node:22 bash
	Verify:
	node -v
	npm -v
	npx -v
Option 2 — Recommended React Development Setup
	Mount your frontend workspace into Docker.
	Step 1 — Go to Workspace
		Example:
		C:\Users\vin\OneDrive\Documents\Workspace-FE
	Step 2 — Run Node Container
		Windows CMD:
		docker run -it --rm -v "C:\Users\vin\OneDrive\Documents\Workspace-FE:/app" -w /app -p 5173:5173 node:22 bash
	Step 3 — Create React App Using Vite
		Inside container:
		npm create vite@latest task-manager-ui -- --template react
		Stop current Vite server: Ctrl + C
	Step 4 — Move Into Project
		cd task-manager-ui
	Step 5 — Install Dependencies
		npm install
	Step 6 — Install Axios + Bootstrap
		npm install axios bootstrap
	Step 7 — Run React App
		npm run dev -- --host
	Open Browser
		http://localhost:5173
	Recommended Production-Like Structure
		Workspace-FE/
		│
		├── task-manager-ui/
		│   ├── src/
		│   ├── public/
		│   ├── package.json
		│   └── vite.config.js
		│
		└── docker/
	Recommended Next Upgrade
	Create dedicated Dockerfile.
		Example:
		FROM node:22
		WORKDIR /app
		COPY package*.json ./
		RUN npm install
		COPY . .
		EXPOSE 5173
		CMD ["npm", "run", "dev", "--", "--host"]
		Then Run
		Build:
		docker build -t task-manager-ui .
		Run:
		docker run -p 5173:5173 task-manager-ui
	Enterprise-Level Future Architecture
		Eventually your stack becomes:
		React Container
			  ↓
		Spring Boot Container
			  ↓
		PostgreSQL Container
			  ↓
		Docker Compose
			  ↓
		Kubernetes
	Excellent for:
		DevOps portfolio
		cloud deployment
		microservices
		AWS ECS/EKS
		DigitalOcean Apps
		CI/CD pipelines
========================================React======================================================
What React Is
	React is a JavaScript library for building UI.
	Instead of:
	Server generates HTML
	React does:
	Browser generates HTML
	using components.
	Traditional Spring MVC
	Browser
	   ↓
	Spring Controller
	   ↓
	Thymeleaf HTML
	   ↓
	Browser shows page
	React Architecture
	Browser
	   ↓
	React App
	   ↓
	API Calls
	   ↓
	Spring Boot Backend
	   ↓
	JSON Response
	   ↓
	React updates UI
Core React Concepts
	Concept		Meaning
	Component	Reusable UI block
	State		Data stored in UI
	Props		Data passed to component
	JSX	HTML 	inside JavaScript
	useState	Store/change UI data
	useEffect	Run code on page load
	Axios		API calling library
Your Current Goal
	Build this:
	React Frontend
	+
	Spring Boot Backend
FIRST Understand React Folder
	Inside:
		task-manager-ui
	you have:
		src/
		public/
		package.json
		vite.config.js
	Important Files
		File			Purpose
		package.json	Project dependencies
		src/main.jsx	React entry point
		src/App.jsx		Main component
		node_modules	Installed libraries
	Open Project in VS Code
		From Windows:
			code .
		inside:
			task-manager-ui
	First React Component
		Open:
			src/App.jsx
			Delete everything.
		Add:
			function App() {
			  return (
				<h1>Hello React</h1>
			  );
			}
			export default App;
		What This Means
			Function Component
			function App()
		Creates UI component.
	Think:
		@Controller
		but for frontend UI.
	return
		return (
		Returns HTML-like UI.
	JSX
		<h1>Hello React</h1>
		HTML inside JavaScript.
	Called:
		JSX
	export default
		export default App;
		Makes component available to React.
	Run React
		Inside container:
		npm run dev -- --host
	Open:
		http://localhost:5173
		You should see:
		Hello React
Next Concept — Dynamic Data
	Update App.jsx:
		function App() {
		  const name = "Vin";
		  return (
			<h1>Hello {name}</h1>
		  );
		}
		export default App;
	What Is {}
		{name}
		Injects JavaScript into JSX.
	Equivalent idea:
		${name}
		in Thymeleaf.
	React State
		Now real React starts.
	useState
		import { useState } from "react";
		Used for changing UI data.
	Example
		import { useState } from "react";
		function App() {
		  const [count, setCount] = useState(0);
		  return (
			<div>
			  <h1>{count}</h1>
			  <button onClick={() => setCount(count + 1)}>
				Increase
			  </button>
			</div>
		  );
		}
		export default App;
	Understanding This
		State Variable
			const [count, setCount]
		Equivalent idea:
			getter + setter
	Initial Value
		useState(0)
		count starts with:
		0
	Updating State
		setCount(count + 1)
		Updates UI automatically.
		s is React's main power.
	Event Handling
		onClick={}
		Equivalent to:
		button.addEventListener(...)
Next Concept — Forms
	React forms are very important for your Task Manager.
	Example Input
		import { useState } from "react";
		function App() {
		  const [title, setTitle] = useState("");
		  return (
			<div>
			  <input
				type="text"
				value={title}
				onChange={(e) => setTitle(e.target.value)}
			  />
			  <h1>{title}</h1>
			</div>
		  );
		}
		export default App;
	Understanding Flow
		User types
			↓
		onChange triggers
			↓
		setTitle updates state
			↓
		React re-renders UI
	Important React Mental Model
		React UI is:
		FUNCTION OF STATE
		When state changes:
		UI updates automatically
		No manual DOM manipulation.
	React vs Thymeleaf
		Thymeleaf				React
		Server HTML				Browser HTML
		Static render			Dynamic render
		Full page reload		Partial UI updates
		Java backend templates	JavaScript components
	Recommended Learning Path
		Do in order:
		1. JSX
		2. Components
		3. Props
		4. useState
		5. useEffect
		6. Forms
		7. Lists
		8. Event Handling
		9. Axios API Calls
		10. Routing
	Your Immediate Next Task
		Practice these one-by-one:
		1. Hello React
		2. Dynamic variable
		3. Counter app
		4. Input textbox
		5. Button click
		6. Render list using map()
	Only after comfortable:
	connect Spring Boot APIs
	use Axios
	build Task Manager UI
==============================================================================================