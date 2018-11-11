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
### How to Run (Change branch to see feature)
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

#### (configuration_files)

```
1. Change the keys in the file 'process.sh' to access your twitter account.
2. Run command 'mvn clean install' to create executable jar file.
3. Run command './process.sh' to start your service.
4. Using Postman call:
    (POST) http://localhost:8080/api/1.0/twitter/tweet 
    (GET) http://localhost:8080/api/1.0/twitter/timeline
```

#### (logging)

```
1. Added logging in application.
```

#### (servicesAndResources, pojos)

```
1. Create a file 'keys.sh' which contains your keys to access twitter, you have to export those keys.
2. Run command 'mvn clean install' to create executable jar file.
3. Run command './process.sh' to start your service.
4. Using Postman call:
    (POST) http://localhost:8080/api/1.0/twitter/tweet 
    (GET) http://localhost:8080/api/1.0/twitter/timeline
```

#### (streams, optionals)

```
1. Create a file 'keys.sh' which contains your keys to access twitter, you have to export those keys in the file.
2. Run command 'mvn clean install' to create executable jar file.
3. Run command './process.sh' to start your service.
4. Using Postman call:
    (POST) http://localhost:8080/api/1.0/twitter/tweet 
    (GET) http://localhost:8080/api/1.0/twitter/timeline
    (GET) http://localhost:8080/api/1.0/twitter/timeline?filter=filterWord
```

#### (dependencyInjection, caching)

```
1. Create a file 'keys.sh' which contains your keys to access twitter, you have to export those keys in the file.
2. Run command 'mvn clean install' to create executable jar file.
3. Run command './process.sh' to start your service.
4. Using Postman call:
    (POST) http://localhost:8080/api/1.0/twitter/tweet 
    (POST) http://localhost:8080/api/1.0/twitter/tweet?tweet=Hello Twitter
    (GET) http://localhost:8080/api/1.0/twitter/timeline
    (GET) http://localhost:8080/api/1.0/twitter/timeline?filter=filterWord
```

#### (createBlocks)

```
1. Create a file 'keys.sh' which contains your keys to access twitter, you have to export those keys in the file.
2. Run command 'mvn clean install' to create executable jar file.
3. Run command './process.sh' to start your service.
4. Open 'localhost:8080/index.html' in browser.
5. Click get timeline to fetch timeline.
6. Click on individial timeline to open in twitter.
```