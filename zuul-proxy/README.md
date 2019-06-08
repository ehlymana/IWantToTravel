# Getting Started
  
To start locally
  
```
  java -jar target/zuul-proxy-0.0.1-SNAPSHOT.jar
```
  
To create docker image
  
```
  docker build -t proxy .
``` 
  
To start using docker
  
```
  docker run -p 8084:8084 proxy
```