# NearMe – Proximity-Based Search Backend
**NearMe** is a backend for a proximity-based location search, similar to Google Places. It accepts user location and query (title and tags), and returns the most relevant results, sorted by distance using the Haversine formula.

---

## 🚀 Features

- Accepts user latitude and longitude
- Search places by title, tags, or both
- Calculates great-circle distance (Haversine formula)
- Returns paginated and sorted list of matching places

---

## 📦 Tech Stack

| Layer       | Technology                         |
|-------------|------------------------------------|
| Language    | Java 17+                           |
| Framework   | Spring Boot 3                      |

---

## 📁 Project Structure

```
src/main/java/com/nearme/
├── controller/         # REST endpoints
├── service/            # Business logic
├── dao/                # Data Access Abstraction
├── repository/         # Spring Data JPA
├── model/              # JPA Entities
├── dto/                # Request/Response Objects
├── util/               # Utility Classes (e.g. Distance Calculator)
└── exception/          # Global Exception Handler
```

---

## 📄 Sample API Request

### `POST /api/places/search`

**Request Body**:
```json
{
  "latitude": 12.9716,
  "longitude": 77.5946,
  "title": "cafe",
  "tags": ["coffee", "restaurant"],
  "page": 0,
  "size": 10
}
```

**Response**:
```json
{
  "content": [
    {
      "id": 1,
      "title": "Cafe Coffee Day",
      "latitude": 12.9721,
      "longitude": 77.5949,
      "tags": ["cafe", "coffee"],
      "distance": 0.13
    }
  ],
  "totalElements": 25,
  "totalPages": 3,
  "number": 0,
  "size": 10,
  "first": true,
  "last": false
}
```

---

## 🧮 Distance Calculation

Uses the **Haversine formula** in Java to compute accurate spherical distances between user and place locations.

```java
DistanceCalculator.calculate(lat1, lon1, lat2, lon2);
```

---

## ⚙️ Configuration

### `application.yml`
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:nearme-db;DB_CLOSE_DELAY=-1
    driver-class-name: org.h2.Driver
    username: sa
    password:
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---

### 🔧 Prerequisites

Make sure you have the following installed:

- 🛠️ Java 17+
- 🧰 Maven 3.6+
- 💻 Any IDE (IntelliJ, VS Code, Eclipse) or terminal

---

## ▶️ Getting Started Locally

Follow these steps to run the NearMe backend application on your local machine.

---

### 🚀 Run Using Maven (CLI)

```bash
git clone https://github.com/your-username/NearMe.git
cd NearMe
./mvnw spring-boot:run
```

---

### 🛠️ Run Using Docker

```Dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/NearMe-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]
```

```bash
  docker build -t nearme-app .
```

```bash
  docker run -p 8090:8090 nearme-app
```

---
### 💻 Run Using Your IDE

Follow these steps to run the project from your favorite Java IDE:

1️⃣ **Open Project**
- Launch your IDE (e.g., IntelliJ, Eclipse, VS Code)
- Select: 📁 File → Open → Choose the cloned NearMe project folder
- Ensure it’s imported as a Maven project

2️⃣ **Build & Run**
- Navigate to: `src/main/java/com/nearme/NearMeApplication.java`
- Right-click → ▶️ Run `NearMeApplication`

3️⃣ **Access the Application**
- 🌐 App Running At: [http://localhost:8090](http://localhost:8090)

4️⃣ **Explore the In-Memory Database**
- 🗃️ H2 Console: [http://localhost:8090/h2-console](http://localhost:8090/h2-console)
- Username: `sa`, Password: _(leave blank)_

5️⃣ **Test APIs Visually**
- 📘 Swagger UI: [http://localhost:8090/swagger-ui.html](http://localhost:8090/swagger-ui.html)

---

## 👨‍💻 Author

Made with ❤️ by Starreh

---
