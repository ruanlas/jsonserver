FROM openjdk:11-jdk
VOLUME /tmp
COPY target/*.jar mockapiserver.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "mockapiserver.jar"]