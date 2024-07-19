FROM maven:3.9.7-amazoncorretto-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM amazoncorretto:21
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
