FROM openjdk:17-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8081
CMD ["java", "-jar", "app.jar", "--spring.config.location=file:/app/config/application.yml"] 