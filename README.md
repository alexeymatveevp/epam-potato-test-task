Potato bags API
========
Test task for EPAM interview

<img src="https://github.com/davidluckystar/epam-potato-test-task/blob/master/files/logo.png" width="10%" style="display: inline-block">

Task description is [here](https://github.com/davidluckystar/epam-potato-test-task/blob/master/files/lgi-java-code-assignment.pdf)

## Running the app
**Prerequisites**: java8 runtime, `java` in `PATH` or correct `JAVA_HOME`

Clone or download this repo

Open the console, go to repo directory and run `./gradlew bootRun` or `gradlew.bat bootRun`

<img src="https://github.com/davidluckystar/epam-potato-test-task/blob/master/files/boot-run.png" width="80%" style="display: inline-block">

Dependencies will be downloaded but eventually you should see smth like this

<img src="https://github.com/davidluckystar/epam-potato-test-task/blob/master/files/app-started.png" width="80%" style="display: inline-block">

Then open browser [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html); if you should see the swagger interface this means the app is started.

<img src="https://github.com/davidluckystar/epam-potato-test-task/blob/master/files/swagger-ui.png" width="80%" style="display: inline-block">

Use either swagger to make HTTP calls or other HTTP client:
* `GET /potato-bags?size=3` - gets all potato bags with limited size (3=default)
* `POST /potato-bags` - creates a new potato bag (see json model in swagger)

## Testing
Run `./gradlew test` in console, tests will run, you should see the result

<img src="https://github.com/davidluckystar/epam-potato-test-task/blob/master/files/run-tests.png" width="80%" style="display: inline-block">

There are only `WebTestClient`-based integration tests now.

Open [swagger](http://localhost:8080/swagger-ui.html), click on GET operation => "Try it out" button

<img src="https://github.com/davidluckystar/epam-potato-test-task/blob/master/files/swagger-get.png" width="80%" style="display: inline-block">

You should see one potato bag in response (i've added one dummy value as initial data)

Now click on POST add potato bag operation, copy example request from model on the right

<img src="https://github.com/davidluckystar/epam-potato-test-task/blob/master/files/swagger-post.png" width="80%" style="display: inline-block">

Set valid data: 1 < potatoes < 100, pack date < future, 1 < price < 50, one of known suppliers:
 * De Coster
 * Owel
 * Patatas Ruben
 * Yunnan Spices

click "Try it out" - chech that ID of the bag is in the response

Try creating bag with incorrect data - expect 400 with error message.

## About the app
This simple app uses:
* Spring Boot as web framework
* java memory for storing data
* swagger for documenting API
* spring WebTestClient for integration testing
* logback for logging
* gradle as build system
* no monitoring
