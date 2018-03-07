Potato bags API
========
Test task for EPAM interview

Task description is [here](https://github.com/davidluckystar/epam-potato-test-task/blob/master/files/lgi-java-code-assignment.pdf)

## Running the app
API is written using Spring Boot, gradlew

To start the app simply open the console and run `./gradlew bootRun` or `gradlew.bat bootRun`

<img src="https://github.com/davidluckystar/epam-potato-test-task/blob/master/files/boot-run.png" width="100%" style="display: inline-block">

You should see smth like this

<img src="https://github.com/davidluckystar/epam-potato-test-task/blob/master/files/app-started.png" width="100%" style="display: inline-block">

Then open browser [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html); if you should see the swagger interface this means the app is started.

<img src="https://github.com/davidluckystar/epam-potato-test-task/blob/master/files/swagger-ui.png" width="100%" style="display: inline-block">

## Testing
Run `./gradlew test` in console, tests will run, you should see the result

<img src="https://github.com/davidluckystar/epam-potato-test-task/blob/master/files/run-tests.png" width="100%" style="display: inline-block">

There are only `WebTestClient`-based integration tests now.

Open [swagger](http://localhost:8080/swagger-ui.html), click on GET operation => "Try it out" button

<img src="https://github.com/davidluckystar/epam-potato-test-task/blob/master/files/swagger-get.png" width="100%" style="display: inline-block">

You should see one potato bag in response (i've added one dummy value as initial data)

Now click on POST add potato bag operation, copy example request from model on the right

<img src="https://github.com/davidluckystar/epam-potato-test-task/blob/master/files/swagger-post.png" width="100%" style="display: inline-block">

Set valid data: 1 < potatoes < 100, pack date < future, 1 < price < 50, one of known suppliers:
 * De Coster
 * Owel
 * Patatas Ruben
 * Yunnan Spices

click "Try it out" - chech that ID of the bag is in the response

Try creating bag with incorrect data - expect 400 with error message.
