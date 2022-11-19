#!/bin/bash
cd app
./mvnw clean package
java -jar /app/target/greeting-service-0.0.1-SNAPSHOT.jar --spring.config.location=/app/application.properties