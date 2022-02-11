FROM openjdk:17
COPY target/freelance.jar .
ENTRYPOINT ["java", "-jar", "freelance.jar"]
