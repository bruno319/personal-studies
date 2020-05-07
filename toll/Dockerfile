FROM ubuntu:18.04
USER root
RUN apt-get update
RUN apt-get -y install openjdk-8-jdk --no-install-recommends

COPY build/libs/toll-1.0-SNAPSHOT-all.jar .

EXPOSE 8090
CMD ["java", "-jar", "toll-1.0-SNAPSHOT-all.jar"]
