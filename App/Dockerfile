FROM openjdk:8-alpine
ENV APP_HOME /opt
EXPOSE 8761
COPY /target/hbs-0.0.1-SNAPSHOT.jar $APP_HOME
CMD ["java", "-jar", "/opt/hbs-0.0.1-SNAPSHOT.jar"]