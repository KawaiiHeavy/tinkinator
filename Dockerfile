FROM openjdk:13
EXPOSE 8080
ADD target/tinkinator.jar tinkinator.jar
ENTRYPOINT ["java", "-jar", "/tinkinator.jar"]