# PlainJDBC-React-Example

### Stack:
* Java8, Springboot, Lombok
* Plain JDBC, various connectionPools (tomcat, hikariCP, dbcp, c3p0) 
* Stored procedures, functions, triggers, etc.
* Spock
* React
* jsonDoc, jasperReports
* Docker-compose, adminer, mysql

#### Build:
* Go to `jdbcexample` folder: `cd jdbcexample`
* Run command : `./gradlew clean build docker`

#### Starting app:
* Go to project root folder.
* Run command: `docker-compose up`

### Documentation:
* Visit http://localhost:8080/jsondoc to see apiDoc JSON.
* Visit http://localhost:8080 to see apiDoc UI.