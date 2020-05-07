FROM openjdk:8-alpine
WORKDIR twitter-service
COPY build/libs/twitter-service-1.0.jar .
EXPOSE 8181
CMD ["java", "-jar", "twitter-service-1.0.jar"]