FROM amazoncorretto:11-alpine-jdk
MAINTAINER Santiago
COPY target/BackEnd-0.0.1-SNAPSHOT.jar  BackEnd-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/BackEnd-0.0.1-SNAPSHOT.jar"]