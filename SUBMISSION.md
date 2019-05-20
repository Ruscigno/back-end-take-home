## Doubts
- Should I return only a list of values with the IATA3 code?
Yes
- Should I consider the smallest route for the same company?
No
- Can I load the data in memory?
Yes
- Should I show a result for an airport that is in 'routes.csv' but not in 'airlines.csv' or 'airports.csv'?
Yes
- Should I create a cache for already calculated routes?
No
- Is the database variable?
No

## How to use
> Method: GET
Endpoint: /api/v1/routes/shortest

#### Example

##### Request:
> GET http://localhost:8080/api/v1/routes/shortest?destination=BRU&origin=ABJ

##### Response:
```json
[
    "ABJ",
    "BRU"
]
```
#### Heroku
https://ruscigno-guestlogix.herokuapp.com/swagger-ui.html

### Some notes

 - There are inconsistencies in * .csv data.
 - Since this exercise only asks for a shortest route endpoint, I did not include any other endpoint.
 - As I am participating in the Toronto LEAP event, I have many other tech tests to do, so I kept this one as simple as possible
 - I simply load all the data into memory on startup.
 - The project includes a Swagger 2 frontend for convenience.

### Compilation
You should have Java 1.8, Maven and Git client already installed

1. clone: `git clone https://github.com/Ruscigno/back-end-take-home.git`
2. compile: `mvn clean package -DskipTests`
3. run: `java -jar target/Ruscigno.Guestlogix-0.0.1-SNAPSHOT.jar`

### Make docker image
You should have Docker and Docker Compose already installed
1. clone: `git clone https://github.com/Ruscigno/back-end-take-home.git`
2. build: `docker build --rm -t back-end-take-home:latest .`
3. run: `docker-compose -f "docker-compose.yml" up`

### Running Tests
1. compile: `mvn test`