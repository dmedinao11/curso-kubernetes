FROM openjdk:8-jdk-slim-buster as builder

WORKDIR /app/msvc-cursos

COPY ./pom.xml /app
COPY ./msvc-cursos/.mvn ./.mvn
COPY ./msvc-cursos/mvnw .
COPY ./msvc-cursos/pom.xml .

RUN bash ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r target

COPY ./msvc-cursos/src ./src

RUN bash ./mvnw clean package -Dmaven.test.skip

FROM openjdk:8-jdk-slim-buster

WORKDIR /app

COPY --from=builder /app/msvc-cursos/target/cursos-0.0.1-SNAPSHOT.jar .

ENV PORT 8002

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "cursos-0.0.1-SNAPSHOT.jar"]
