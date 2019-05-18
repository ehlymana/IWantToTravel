FROM openjdk:8-alpine
RUN apk --no-cache add netcat-openbsd
COPY /target/zuul-proxy-0.0.1-SNAPSHOT.jar /opt/zuul-proxy-0.0.1-SNAPSHOT.jar
COPY ApiGateway-entrypoint.sh C:/Users/pc/Desktop/IWantToTravel/zuul-proxy/ApiGateway-entrypoint.sh
RUN chmod 775 C:/Users/pc/Desktop/IWantToTravel/zuul-proxy/ApiGateway-entrypoint.sh
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar /opt/zuul-proxy-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080