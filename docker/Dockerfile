FROM openjdk:12
EXPOSE 8080
ARG JAR_FILE=rss-1.0.0-SNAPSHOT.jar
COPY ${JAR_FILE} rss.jar
ENTRYPOINT ["java","-jar","rss.jar"]