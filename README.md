# Project Title

Using twitter app

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software.

```
1. Java 1.8 or above
2. Maven 3.5 or above
```
### How to Run
##### (master)
```
1. Change the keys in twitter-config.yaml to access your twitter account
2. Run command 'mvn clean install' to create executable jar file
3. Run command java 'java -jar target/twitter-1.0-SNAPSHOT-shaded.jar' to execute jar file.
```

##### (restful-twitter)

```
1. Change the keys in twitter-config.yaml to access your twitter account
2. Run command 'mvn clean install' to create executable jar file
3. Run Command 'java -jar target/twitter-1.0-SNAPSHOT-shaded.jar server src/main/resources/yaml/twitter-data.yaml'
4. Using Postman call:
    (POST) http://localhost:8080/api/1.0/twitter/tweet 
    (GET) http://localhost:8080/api/1.0/twitter/timeline
```