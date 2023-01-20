# QJJFoodTrucks
## The Challenge
As the song says, "you've got to play the hand you're dealt", and in this case your hand is to implement something to help us manage our food truck habit.

Our team loves to eat. They are also a team that loves variety, so they also like to discover new places to eat.

In fact, we have a particular affection for food trucks. One of the great things about Food Trucks in San Francisco is that the city releases a list of them as open data.

Your assignment is to make it possible for our teams to do something interesting with this food trucks data.
## Solution
This project is build from java and Spring Boot Framework, also use Mysql as persistence tool.<br>
I applied DDD(Domain Driven Design) system design pattern in this project. <br>
We have five layers: <br>
- presentation 
Contains FoodTruckController
- transport
- persistence
- service

## How to run this project
This project is using gradle as build tool, first you need to build jar file with gradle:<br>

In your project root directory, run
```
 ./gradlew clean build
```
Then you can run application and mysql in docker, make sure you have start docker in your environment, then run
```
docker-compose up --build
```
Then you can access <code>http://localhost:8080/foodtruck/list</code> to get all foodtruck data.<br>

Another api is for checking nearby foodtrucks according to position and distance:<br>
<code>
http://localhost:8080/foodtruck/search?latitude=37.78623677446536&longitude=-122.38906620560476&maxDistance=300
</code><br>
Then you can get all fooftrucks which is 300 meters around given coordinate(x=-122.38906620560476, y=37.78623677446536) <br>
