FROM openjdk:11-jre-slim
CMD ["./gradlew", "build"]
COPY build/libs/*SNAPSHOT.jar /app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/app.jar"]