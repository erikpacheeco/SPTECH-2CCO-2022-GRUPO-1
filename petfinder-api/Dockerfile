FROM openjdk:11
VOLUME /tmp

# COPY target/*.jar app.jar
COPY /target/petfinder-api-0.0.1-SNAPSHOT.jar ./petfinder.jar

CMD ["java", "-jar", "./petfinder.jar", "--spring.profiles.active=prod"]