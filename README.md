<!-- ABOUT THE PROJECT -->
## About The Project

This is an API microservice built in Kotlin for a small cinema, which only plays movies from the Fast & Furious franchise.
Some operations that can be made are:
* Update show times and prices for their movie catalog
* Fetch movie times
* Fetch details about one of their movies (e.g. name, description, release date, rating, IMDb rating, and runtime).
* Leave a review rating (from 1-5 stars) about a particular movie

### Built With

* [Kotlin](https://kotlinlang.org/)
* [Spring](https://spring.io/)
* [MongoDb](https://www.mongodb.com/)

<!-- GETTING STARTED -->
## Getting Started

In order to run it locally the app have an embedded MongoDB to perform some tests.

### Prerequisites

To use the OMDB api client to get the movie details you'll need to add an api key that must be on the following config
* application.properties
  ```sh
  imdb-api.apikey={YOURKEY}
  ```
  
You also need and user and password admin to perform the "admins only" action. That in this case it's only the update showtimes movies.
* application.properties
  ```sh
  spring.security.user.name=adminUser
  spring.security.user.password=adminPassword
  ```


<!-- USAGE EXAMPLES -->
## Usage

When runs the application, the swagger is available on:
  ```sh
  /swagger-ui.html
  ```

the OpenApi doc is available on:
  ```sh
  /api-docs
  ```

<!-- CONTACT -->
## Contact

Rodrigo Santa Eulalia - rodrigo.santaeulalia@gmail.com

Project Link: [https://github.com/your_username/repo_name](https://github.com/your_username/repo_name)