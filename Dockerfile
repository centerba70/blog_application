FROM openjdk:25
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} invoice_scanner.jar
ENTRYPOINT ["java","-jar","/invoice_scanner.jar"]
EXPOSE 8080