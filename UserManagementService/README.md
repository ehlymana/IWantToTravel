# Getting Started
  
To start locally
  
```
  java -jar target/ums-0.0.1-SNAPSHOT.jar
```
  
To create docker image
  
```
  docker build -t user .
``` 
  
To start using docker
  
```
  docker run -p 8088:8088 user
```