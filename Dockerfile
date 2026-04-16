FROM eclipse-temurin:26
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} blog_application.jar
ENTRYPOINT ["java","-jar","/blog_application.jar"]
EXPOSE 8080