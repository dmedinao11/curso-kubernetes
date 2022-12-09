FROM openjdk:8-jdk-slim-buster as builder

ARG MSCV_NAME=msvc-usuarios

WORKDIR /app/$MSCV_NAME

COPY ./pom.xml /app
COPY ./$MSCV_NAME/.mvn ./.mvn
COPY ./$MSCV_NAME/mvnw .
COPY ./$MSCV_NAME/pom.xml .

RUN bash ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r target

COPY ./$MSCV_NAME/src ./src

RUN bash ./mvnw clean package -Dmaven.test.skip

FROM openjdk:8-jdk-slim-buster

ARG MSCV_NAME=msvc-usuarios

WORKDIR /app

COPY --from=builder /app/$MSCV_NAME/target/usuarios-0.0.1-SNAPSHOT.jar .

ENV PORT 8001

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "usuarios-0.0.1-SNAPSHOT.jar"]
