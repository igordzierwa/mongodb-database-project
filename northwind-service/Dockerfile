FROM openjdk:11-jdk-slim
EXPOSE 8080
COPY . app/
RUN cd app; ./mvnw -Dmaven.test.skip package
ENTRYPOINT [ "java", "-jar", "/app/target/northwind-service.jar" ]
