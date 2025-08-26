## IPL Stats Viewer & Player Performance Predictor

A full-stack web app to:
- Explore IPL player stats by teams and countries with fast search/filtering
- Predict next-match player performance for batters and bowlers using Hugging Face via Spring integration
- Deliver a responsive SPA user experience with React and Tailwind CSS
- Persist and query data in PostgreSQL
- Package and deploy with Docker

- Live demo: `http://swastikgupta-iplStats.netlify.app`

---

### Features

- **Player stats browser**
  - View players by role (batting/bowling)
  - Search players, teams, and countries
  - Team-wise and country-wise player listings

- **Prediction engine**
  - Hugging Face Router API integration via Spring service
  - Numeric score (0–100) predicted from player stats

- **Data storage**
  - PostgreSQL with Spring Data JPA

- **Frontend**
  - React SPA with Tailwind CSS classes
  - Responsive layout and client-side routing

- **Deployment**
  - Backend configurable via environment variables
  - Docker-ready backend
  - Frontend hosted on Netlify

---

### Tech Stack

- **Frontend**: React 19, React Router 7, Tailwind CSS 4, Vite
- **Backend**: Spring Boot 3 (Web, Data JPA), Lombok
- **AI**: Hugging Face Router (`/v1/chat/completions`) via `RestTemplate` (configurable model)
- **Database**: PostgreSQL
- **Deployment**: Docker (backend), Netlify (frontend)

---

### Architecture & Folder Structure

High-level flow: React SPA → Spring Boot REST APIs → PostgreSQL → Hugging Face Router (for predictions).

```text
iplStatsAndPerformancePredictor/
  frontend/                 # React SPA (Vite)
    src/
      components/           # Pages and UI components
      predictPerformance.js # Calls backend /api/predict
      main.jsx              # SPA routes
  backend/                  # Spring Boot application
    src/main/java/com/swastik/IplStatsAndPerformancePredictor
      controller/           # REST controllers
      service/              # Business logic & HF integration
      dto/                  # Request/response DTOs
      model/, repository/   # JPA models and repositories
    src/main/resources/
      application.properties
  web_Scraping/             # Data extraction and CSVs (optional seeding/reference)
```

---

### Installation & Setup

Prerequisites:
- Node.js 18+
- Java 21
- Maven 3.9+
- PostgreSQL 14+
- Hugging Face API key (for predictions)

Clone:
```bash
git clone <your-repo-url>
cd iplStatsAndPerformancePredictor
```

#### Backend (Spring Boot)

1) Create a PostgreSQL database and user.

2) Set environment variables (Windows PowerShell example):
```powershell
$env:DATASOURCE_URL="jdbc:postgresql://localhost:5432/iplstats"
$env:DATASOURCE_USERNAME="postgres"
$env:DATASOURCE_PASSWORD="postgres"
$env:FRONTEND_URL="http://localhost:5173"
$env:HF_API_KEY="<your-hf-api-key>"
$env:HF_MODEL="meta-llama/Meta-Llama-3.1-8B-Instruct"
```

3) Build and run:
```powershell
cd backend
./mvnw spring-boot:run
# Server runs on port 8001 by default
```

Notes:
- `spring.jpa.hibernate.ddl-auto=update` will create/update tables.
- `spring.jpa.show-sql=true` logs SQL queries.

#### Frontend (React)

1) Configure API base URL for local dev in `.env`:
```bash
cd ../frontend
# .env
VITE_BACKEND_URL=http://localhost:8001
```

2) Install and run:
```bash
npm install
npm run dev
# App on http://localhost:5173
```

---

### Docker (Backend)

A production-ready Dockerfile example for the Spring Boot service:

```dockerfile
# backend/Dockerfile
FROM maven:3.9-eclipse-temurin-21 as build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/IplStatsAndPerformancePredictor-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8001
ENV SERVER_PORT=8001
ENTRYPOINT ["java","-jar","/app/app.jar"]
```

Build and run:
```powershell
cd backend
docker build -t ipl-backend .
docker run -p 8001:8001 --env DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/iplstats `
  --env DATASOURCE_USERNAME=postgres --env DATASOURCE_PASSWORD=postgres `
  --env HF_API_KEY=<your-hf-api-key> --env HF_MODEL=meta-llama/Meta-Llama-3.1-8B-Instruct `
  --env FRONTEND_URL=http://localhost:5173 ipl-backend
```

Optional:
- Add `docker-compose.yml` with PostgreSQL for fully containerized development.

---

### API Documentation

Base URL:
- Local: `http://localhost:8001`
- Production: set `VITE_BACKEND_URL` accordingly in the frontend

All endpoints are prefixed with `/api`.

#### Teams and Countries

- GET `/api/teams`
  - Returns all IPL teams.
  - Sample response:
```json
[
  {
    "teamId": "MI",
    "teamName": "Mumbai Indians",
    "teamCode": "MI"
  }
]
```

- GET `/api/countries`
  - Returns all countries.
```json
[
  {
    "countryId": "IND",
    "countryName": "India"
  }
]
```

#### Players

- GET `/api/players?role=batting|bowling`
  - Returns all players by role.
```json
[
  {
    "playerId": "123",
    "playerName": "Virat Kohli",
    "teamName": "RCB",
    "teamCode": "RCB",
    "countryName": "India",
    "matches": 250,
    "innings": 230,
    "runs": 7500,
    "highestScore": "113",
    "average": "38.2",
    "strikeRate": 130.5,
    "fifties": 52,
    "centuries": 5,
    "fours": 700,
    "sixes": 220
  }
]
```

- GET `/api/player?playerId=<id>&role=batting|bowling`
  - Returns a player by ID and role.
  - Response body matches the relevant stats DTO for the role.

#### Search

- GET `/api/player/search?keyword=<text>`
  - Search players by name.

- GET `/api/team/search?keyword=<text>`
  - Search teams by keyword.

- GET `/api/country/search?keyword=<text>`
  - Search countries by keyword.

#### Team-specific

- GET `/api/team/{teamId}`
  - Fetch team by ID.

- GET `/api/team/players?teamId=<id>&role=batting|bowling`
  - Fetch players for a team and role.

#### Country-specific

- GET `/api/country/{countryId}`
  - Fetch country by ID.

- GET `/api/country/players?countryId=<id>&role=batting|bowling`
  - Fetch players for a country and role.

#### Prediction

- POST `/api/predict/`
  - Predicts next-match performance score (0–100) for a given player stats payload.
  - Request body varies by role:
    - For batting: `role="batting"` and `battingStatsDTO` field
    - For bowling: `role="bowling"` and `bowlingStatsDTO` field
  - Response: plain text numeric value (e.g., `"76"`).

Request (batting):
```json
{
  "role": "batting",
  "battingStatsDTO": {
    "playerId": "123",
    "playerName": "Shubman Gill",
    "teamName": "GT",
    "teamCode": "GT",
    "CountryName": "India",
    "matches": 90,
    "innings": 88,
    "runs": 2750,
    "highestScore": "129",
    "average": "40.2",
    "strikeRate": 137.4,
    "fifties": 21,
    "centuries": 3,
    "fours": 280,
    "sixes": 85
  }
}
```

Request (bowling):
```json
{
  "role": "bowling",
  "bowlingStatsDTO": {
    "playerId": "321",
    "playerName": "Rashid Khan",
    "teamName": "GT",
    "teamCode": "GT",
    "countryName": "Afghanistan",
    "matches": 110,
    "innings": 106,
    "wickets": 149,
    "overs": 420.0,
    "economy": 6.9,
    "foursConceded": 350,
    "sixesConceded": 120,
    "bowlingAvg": 20.5,
    "bowlingStrikeRate": 17.5,
    "fourWickets": 5,
    "fiveWickets": 2
  }
}
```

Response (text):
```
78
```

DTO Field Reference:
- Batting (`battingStatsDTO`)
  - `playerId`, `playerName`, `teamName`, `teamCode`, `CountryName`, `matches`, `innings`, `runs`, `highestScore`, `average`, `strikeRate`, `fifties`, `centuries`, `fours`, `sixes`
- Bowling (`bowlingStatsDTO`)
  - `playerId`, `playerName`, `teamName`, `teamCode`, `countryName`, `matches`, `innings`, `wickets`, `overs`, `economy`, `foursConceded`, `sixesConceded`, `bowlingAvg`, `bowlingStrikeRate`, `fourWickets`, `fiveWickets`

---

### Frontend Routes / Pages

SPA routes (from `src/main.jsx`):
- `/` → Landing page
- `/teams` → Teams listing (loader-driven)
- `/teams/player/:teamId` → Team players (loader-driven)
- `/countries` → Countries listing (loader-driven)
- `/countries/player/:countryId` → Country players (loader-driven)
- `/players` → All players view
- `*` → Not Found

Prediction usage:
- `src/predictPerformance.js` posts to `${VITE_BACKEND_URL}/api/predict/` with the appropriate DTO keyed by role.

---

### Environment Variables

Backend (`application.properties` reads these):
- `DATASOURCE_URL` (e.g., `jdbc:postgresql://localhost:5432/iplstats`)
- `DATASOURCE_USERNAME`
- `DATASOURCE_PASSWORD`
- `FRONTEND_URL` (CORS allowlist origin, e.g., `http://localhost:5173` or your Netlify URL)
- `HF_API_KEY` (Hugging Face token)
- `HF_MODEL` (e.g., `meta-llama/Meta-Llama-3.1-8B-Instruct`)
- Defaults: `server.port=8001`, `spring.jpa.hibernate.ddl-auto=update`, `spring.jpa.show-sql=true`

Frontend:
- `.env` → `VITE_BACKEND_URL` (e.g., `http://localhost:8001` or your API host)
- Netlify: configure `VITE_BACKEND_URL` in site environment settings

---

### Contribution Guidelines

- Fork the repository
- Create a feature branch:
```bash
git checkout -b feat/<feature-name>
```
- Commit with conventional messages:
```bash
git commit -m "feat(players): add infinite scroll to players list"
```
- Push and open a Pull Request:
```bash
git push origin feat/<feature-name>
```
- PRs should include:
  - Description of change
  - Screenshots for UI changes
  - Tests or steps to verify functionality

---

### Author

- Swastik Gupta
