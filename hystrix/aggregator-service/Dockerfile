FROM openjdk:8-alpine
WORKDIR aggregator-service
COPY build/libs/aggregator-service-1.0.jar .
EXPOSE 8080
CMD ["java", "-jar", "aggregator-service-1.0.jar"]