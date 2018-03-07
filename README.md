Potato bags API
========
Test task for EPAM interview

Task description is [here](link)

## Running the app
API is written using Spring Boot, gradlew

To start the app simply open the console and run `./gradlew bootRun` or `gradlew.bat bootRun`

You should see smth like this

Then open browser [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html); if you should see the swagger interface this means the app is started.
 

## Testing
Run `./gradlew test` in console, tests will run, you should see the result

There are only `WebTestClient`-based integration tests now.

Open [swagger](http://localhost:8080/swagger-ui.html), click on GET operation => "Try it out" button

You should see one potato bag in response (i've added one dummy value as initial data)

Now click on POST add potato bag operation, copy example request from model on the right

Set valid data: 1 < potatoes < 100, pack date < future, 1 < price < 50, one of known suppliers:
 * De Coster
 * Owel
 * Patatas Ruben
 * Yunnan Spices

click "Try it out" - chech that ID of the bag is in the response

Try creating bag with incorrect data - expect 400 with error message.