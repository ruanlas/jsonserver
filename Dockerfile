FROM openjdk:11-jdk
VOLUME /tmp
COPY target/*.jar jsonserver.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "jsonserver.jar"]