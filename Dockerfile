FROM gradle:8.1.1-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean build --no-daemon -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /app/build/libs/library-api-0.0.1-SNAPSHOT.jar library-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "library-api.jar"]