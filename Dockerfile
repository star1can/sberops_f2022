FROM openjdk:17-oracle
WORKDIR /app
ADD target/greeting-service-0.0.1-SNAPSHOT.jar .
ADD application-external.properties application.properties
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/greeting-service-0.0.1-SNAPSHOT.jar"]
CMD ["--spring.config.location=/app/application.properties"]