# Getting Started
  
To start locally
  
```
  java -jar target/ReservationsService-0.0.1-SNAPSHOT.jar
```
  
To create docker image
  
```
  docker build -t reservation .
``` 
  
To start using docker
  
```
  docker run -p 8087:8087 reservation
```