## Introduction

culturaLocale is a software engineering project developed during the 2023/2024 university year by a team comprising of:
- Giuseppe Screnci
- Vasil Ferecka
- Damiano Buzzo

## Project Structure

### Visual Paradigm
`/visualParadigmProject` contains all iterations of the VPP schemas for the purposes of tracking and understanding the software engineering process.

### Dockerfile

The Dockerfile for culturaLocale has been written with simplicity in mind.

- **Base Image**: We use `eclipse-temurin:21.0.2_13-jdk-jammy` as our base image to both compile and run our application.

- **Port Exposure and Execution**: Port 8080 is exposed for the web server, and the application is executed with the specified `ENTRYPOINT`. Please visit http://localhost:8080 to interact with the application after following the instructions to run it.

### Docker Compose File

In `docker-compose.yml`, we define two services: `db` and `app`. The database service uses a PostgreSQL image, and the application service builds from the Dockerfile in the root directory.

We specify environment variables in the compose file: this approach allows us to maintain the `application.properties` file for local and development environments.

## Building and running the Project

culturaLocale can be run on Linux, Windows, and macOS using the command:

```bash
docker-compose up --build
```

Additionally, for ease of use, executable `run.sh` (for Unix-like systems) and `run.bat` (for Windows) files are provided in the repository.