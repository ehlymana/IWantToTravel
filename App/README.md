# Getting Started
  
To start locally
  
```
  java -jar target/hbs-0.0.1-SNAPSHOT.jar
```
  
To create docker image
  
```
  docker build -t app .
``` 
  
To start using docker
  
```
  docker run -p 8761:8761 app
```