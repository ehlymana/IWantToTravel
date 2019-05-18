FROM openjdk:8-jdk-alpine

ARG dburl
ARG dbuser
ARG dbpass

RUN apk --no-cache add netcat-openbsd
COPY target/ReservationsService-0.0.1-SNAPSHOT.jar /opt/ReservationsService-0.0.1-SNAPSHOT.jar

RUN echo "spring.datasource.url=$dburl">C:\Users\pc\Desktop\IWantToTravel\ReservationsService\src\main\resources\application.properties
RUN echo "spring.datasource.username=$dbuser">>C:\Users\pc\Desktop\IWantToTravel\ReservationsService\src\main\resources\application.properties
RUN echo "spring.datasource.password=$dbpass">>C:\Users\pc\Desktop\IWantToTravel\ReservationsService\src\main\resources\application.properties

ENTRYPOINT ["sh", "-c"]

CMD ["java -jar /opt/ReservationsService-0.0.1-SNAPSHOT.jar"]
EXPOSE 8087