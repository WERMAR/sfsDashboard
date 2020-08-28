FROM arm32v7/openjdk:8
ADD target/builderBoard-server.jar builderBoard-server.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "builderBoard-server.jar"]