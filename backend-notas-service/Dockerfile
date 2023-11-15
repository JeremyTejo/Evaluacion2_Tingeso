FROM openjdk:17
ARG JAR_FILE=target/backend-notas-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE}  backend-notas-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/backend-notas-service-0.0.1-SNAPSHOT.jar"]