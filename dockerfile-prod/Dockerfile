FROM eclipse-temurin:26
ARG JAR_FILE=../build/libs/*.jar
COPY ${JAR_FILE} blog_application.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod", "-jar","/blog_application.jar"]
EXPOSE 8080