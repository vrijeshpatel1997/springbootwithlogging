FROM openjdk:8-jdk-alpine
WORKDIR /opt/app
COPY ./target/myapp.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]
