# Yumii – Spring Boot + Angular monorepo

Backend (Spring Boot 4.1, Java 21, Gradle 9) and frontend (Angular 22, TypeScript, SCSS) in one Gradle build.
`./gradlew build` produces a single executable jar that serves the Angular app and the REST API.

## Modules

| Module                | Purpose                                                          |
|-----------------------|------------------------------------------------------------------|
| `yumii-platform`    | BOM – central dependency versions (Spring Boot BOM)              |
| `yumii-domain`      | Domain models + ports (interfaces), no framework deps            |
| `yumii-service`     | Business logic / use cases                                       |
| `yumii-persistence` | JPA entities + adapters implementing the domain ports            |
| `yumii-endpoint`    | REST controllers, DTOs, error handling, SPA forwarding           |
| `yumii-application` | `@SpringBootApplication`, config; bundles the webui into the jar |
| `yumii-webui`       | Angular app, built via the node-gradle plugin                    |

Dependency direction: `application → endpoint → service → domain ← persistence`

Versions live in `gradle/libs.versions.toml`.

## First-time setup

The Gradle wrapper jar/scripts are not included. Generate them once (needs a local Gradle install),
or just open the project in IntelliJ, which reads `gradle/wrapper/gradle-wrapper.properties`:

```
gradle wrapper --gradle-version 9.8.1
```

## Run

Full app (backend serves the built frontend):

```
./gradlew :yumii-application:bootRun
# http://localhost:8080          Angular app
# http://localhost:8080/api/posts REST API
# http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:yumii)
```

Development with hot reload (two terminals):

```
./gradlew :yumii-application:bootRun -PskipWebui
cd yumii-webui && npm install && npm start   # http://localhost:4200, /api proxied to :8080
```

## Build & test

```
./gradlew build                 # backend + frontend, tests included
./gradlew build -PskipWebui     # backend only
java -jar yumii-application/build/libs/yumii-application-0.0.1-SNAPSHOT.jar
```

## Frontend layout

```
src/app/
  core/api/        HTTP services + models
  layout/shell/    header / main / footer
  features/posts/  example feature (lazy loaded)
src/styles/        SCSS tokens (_variables.scss), import with @use 'variables' as *;
```

## Renaming

Replace `yumii` / `com.example.yumii` with your project name and package (module folders,
`settings.gradle.kts`, Java packages, `angular.json` project name).
