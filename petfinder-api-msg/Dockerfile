FROM openjdk:11
VOLUME /tmp

COPY /target/petfinder-api-msg-0.0.1-SNAPSHOT.jar ./petfinder-msg.jar

CMD ["java", "-jar", "./petfinder-msg.jar"]