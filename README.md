# average-price
Sample application to experiment with Spring Boot, Kafka, Influxdb and docker.

Basically, it just display the average of the last X prices on user request, with X an input parameter. The user input/output takes place on the web application.


## Environment setup
1. Make sure to have docker compose installed in host machine
```
$ docker-compose --version
docker-compose version 1.22.0, build f46880fe
```
2. Start the application
```
$ docker-compose up
```
3. Access the application in local environment with `http://localhost:8081`

## Further Impromvements
1. ~~Influxdb query for the average X price~~
2. ~~web frontend, to be implemented in data consumer first~~
3. ~~Dockerize the jars~~
4. ~~onsolidate the app and the dependencies with docker compose~~
    * ~~Kafka~~
    * ~~Influxdb~~
    * ~~Consumer~~
    * ~~Producer~~
5. Extract the web UI into separated services
6. Use Spring Cloud to coordinate with different components
    * Extract the Web UI to a separate spring service from `Consumer`
    * Externalize configuration in `spring-cloud-config`
    * Adopt Spring Cloud Eureka for service discovery
    * `Web UI` invoke `Consumer` with `feign` client
7. Deploy the app in kubernetes
