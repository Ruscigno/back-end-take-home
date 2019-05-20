[![Build Status](https://travis-ci.org/Ruscigno/back-end-take-home.svg?branch=master)](https://travis-ci.org/Ruscigno/back-end-take-home)
[![Heroku](https://heroku-badge.herokuapp.com/?app=heroku-badge&style=flat)](https://ruscigno-guestlogix.herokuapp.com/swagger-ui.html)

# Architectural considerations

- I tried to use SOLID, Clean Code and DRY (Do not Repeat Yourself) concepts, using generics for example, but since I had little time to complete the challenge, I understood that adding this complexity to such a simple project would not be advantageous. Therefore, some entities, repositories, and services have duplicate code.
- I also considered the use of HATEOAS, so I could return a simplistic response (as I do) but with great added value. Due to the time I also discarded it.
- I found some Dijkstra's examples on the internet, however, after testing them, I thought they did not perform satisfactorily. So I adapted for my need the one that had the best performance.
- I know Postgres has a function that could be used for this calculation, but I considered adding a DB to the project was out of scope and would increase the deploy complexity.
- In order to make testing easier, I added Swagger to the project
- For the sake of simplicity, I simply load all the data into memory on startup.

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

# Compilation
You should have Java 1.8, Maven and Git client already installed

1. clone: `git clone https://github.com/Ruscigno/back-end-take-home.git`
2. compile: `mvn clean package -DskipTests`
3. run: `java -jar target/Ruscigno.Guestlogix-0.0.1-SNAPSHOT.jar`
4. go to: `http://localhost:8080/swagger-ui.html`

### Make docker image
You should have Docker and Docker Compose already installed
1. clone: `git clone https://github.com/Ruscigno/back-end-take-home.git`
2. build: `docker build --rm -t back-end-take-home:latest .`
3. run: `docker-compose -f "docker-compose.yml" up`
4. go to: `http://localhost:8080/swagger-ui.html`

### Running Tests
1. compile: `mvn test`