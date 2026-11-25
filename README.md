# Workstation Task API

A Java and Spring Boot demo for assigning work to employees, managing workstations and recording task activity. Try it in Swagger UI using the sample data included with the project.

## Run on your computer

1. Install a **Java 21 JDK**. Open a terminal and run `java -version`. It should show version 21.
2. On GitHub, choose **Code → Download ZIP**, then extract it. Open a terminal in the extracted folder, where `gradlew.bat` and `build.gradle` are visible. If you already use Git, run `git clone https://github.com/RevEtern1ty/My-Project.git` and `cd My-Project` instead.
3. Run the command for your operating system. The first run needs internet access to download Gradle and the Java dependencies.

**Windows PowerShell**

```powershell
.\gradlew.bat bootRun
```

**macOS or Linux**

```bash
./gradlew bootRun
```

4. Leave the terminal open and visit [Swagger UI](http://127.0.0.1:8080/swagger-ui/index.html). Stop the server with **Ctrl+C** in the terminal.

## Try an example

1. In Swagger UI, open **GET /tasks/all** and choose **Try it out → Execute**. A successful request returns HTTP 200 with the seeded tasks.
2. Note a task ID in the response. Run **GET /task-logs/task/{taskId}** with that ID to inspect its activity records.
3. Run **GET /employees/all** or **GET /workstations/all** to see the other sample data.

| URL | What it shows |
| --- | --- |
| [Swagger UI](http://127.0.0.1:8080/swagger-ui/index.html) | Interactive API documentation |
| [Tasks](http://127.0.0.1:8080/tasks/all) | Seeded task list in JSON |
| [Health](http://127.0.0.1:8080/actuator/health) | Application status |

The sample data is loaded into an in-memory HSQLDB database each time the application starts. Changes made while exploring are discarded on restart. The demo listens on your computer only. It is designed for local exploration.

## Project layout

- **Controller** exposes the REST endpoints and validates requests. MapStruct maps API DTOs and database entities.
- **Service** owns business rules and resolves relations between tasks, employees and workstations.
- **Repository** uses Spring Data JPA with HSQLDB. [Database diagram](docs/ER-diagramm.jpg)
- **Error handling** maps request validation and service errors to a shared JSON response.

Run the JUnit tests with `./gradlew test` on macOS/Linux or `.\gradlew.bat test` on Windows.

## Troubleshooting

- **Java 21 not found.** Install a JDK, set `JAVA_HOME` to its folder, then reopen the terminal.
- **Port 8080 already in use.** Add `--args="--server.port=8081"` after `bootRun` and use port 8081 in the browser.
- **Inspecting the database in a SQL tool.** Add `--args="--demo.hsql-server.enabled=true"` after `bootRun` and connect to `jdbc:hsqldb:hsql://127.0.0.1:9001/mydb` as `sa` with the empty demo password.
