# Getting Started
  
To start locally
  
```
  java -jar target/reviewservice-0.0.1-SNAPSHOT.jar
```
  
To create docker image
  
```
  docker build -t review .
``` 
  
To start using docker
  
```
  docker run -p 8086:8086 review
```