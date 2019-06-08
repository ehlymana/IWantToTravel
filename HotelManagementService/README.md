# Getting Started
  
To start locally
  
```
  java -jar target/hotelmanagementservice-0.0.1-SNAPSHOT.jar
```
  
To create docker image
  
```
  docker build -t hotel .
``` 
  
To start using docker
  
```
  docker run -p 8089:8089 hotel
```