FROM openjdk:8-alpine
WORKDIR github-service
COPY build/libs/github-service-1.0.jar .
EXPOSE 8282
CMD ["java", "-jar", "github-service-1.0.jar"]