   FROM openjdk:17
   ADD target/*.jar app.jar
   WORKDIR /app
   EXPOSE 8080
   COPY target/*.jar /app/app.jar
   ENTRYPOINT ["java", "-jar", "/app.jar"]
