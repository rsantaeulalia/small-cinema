package com.example.smallcinema.infra.controller

import com.example.smallcinema.domain.action.AddMovieReviewAction
import com.example.smallcinema.domain.action.FetchMovieDetailsAction
import com.example.smallcinema.domain.action.FetchMoviesAction
import com.example.smallcinema.domain.action.UpdateMovieAction
import com.example.smallcinema.infra.model.Movie
import com.example.smallcinema.infra.model.OmdbMovie
import com.example.smallcinema.infra.model.Review
import com.example.smallcinema.infra.model.ShowTime
import com.example.smallcinema.infra.model.adapter.toDomain
import com.example.smallcinema.infra.model.adapter.toInfra
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@SecurityScheme(
    name = "basicAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "basic"
)
@RequestMapping(value = ["/v1/api/small-cinema"])
@RestController
class MovieController(
    private val fetchMoviesAction: FetchMoviesAction,
    private val addMovieReviewAction: AddMovieReviewAction,
    private val updateMovieAction: UpdateMovieAction,
    private val fetchMovieDetailsAction: FetchMovieDetailsAction
) {

    @Operation(summary = "Get all Movies showtimes ", operationId = "getAllMovies")
    @ApiResponses(
        ApiResponse(
            responseCode = "200", description = "Movies retrieved correctly", content = [Content(
                schema = Schema(implementation = Movie::class),
                examples = arrayOf(
                    ExampleObject(
                        name = "movie", value =
                        """ 
                        {
                            "title": "The Fast and the Furious",
                            "description": "Los Angeles police officer Brian O'Conner must decide where his loyalty really
                             lies when he becomes enamored with the street racing world he has been sent undercover to destroy.",
                            "beginDate": "22 Jul 2021",
                            "endDate": "29 Jul 2021",
                            "showTimes": [{
                                "day": "MONDAY",
                                "schedule": [{
                                    "time": "14:30",
                                    "price": "5.5"
                                }]
                            }]
                        }
                        """
                    )
                )
            )]
        ),
        ApiResponse(responseCode = "500", description = "Some backend error, check error description")
    )
    @GetMapping(value = ["/movies"])
    fun getAllMovies(): ResponseEntity<List<Movie>> {
        val movies = fetchMoviesAction.execute().map { movie -> movie.toInfra() }
        return ResponseEntity.ok(movies)
    }

    @Operation(summary = "Send review for a movie", operationId = "addReviewToMovie")
    @ApiResponses(
        ApiResponse(
            responseCode = "201", description = "Review successfully created", content = [Content(
                schema = Schema(implementation = Movie::class),
                examples = arrayOf(
                    ExampleObject(
                        name = "movie", value =
                        """ 
                       {
                            "title": "The Fast and the Furious",
                            "description": "Los Angeles police officer Brian O'Conner must decide where his loyalty really
                             lies when he becomes enamored with the street racing world he has been sent undercover to destroy.",
                            "beginDate": "22 Jul 2021",
                            "endDate": "29 Jul 2021",
                            "showTimes": [{
                                "day": "MONDAY",
                                "schedule": [{
                                    "time": "14:30",
                                    "price": "5.5"
                                }]
                            }]
                        }
                        """
                    )
                )
            )]
        ),
        ApiResponse(responseCode = "404", description = "Movie title not found"),
        ApiResponse(responseCode = "500", description = "Some backend error, check error description")
    )
    @PostMapping(value = ["/movies/{movieTitle}/reviews"])
    fun addReviewToMovie(@PathVariable movieTitle: String, @RequestBody review: Review): ResponseEntity<Movie> {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            addMovieReviewAction.execute(
                movieTitle,
                review.customerName,
                review.comment,
                review.rating
            ).toInfra()
        )
    }

    @Operation(summary = "Send new showtimes for a Movie", operationId = "updateMovieShowTimes",
        security = [SecurityRequirement(name = "basicAuth")]
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200", description = "Showtimes successfully send", content = [Content(
                schema = Schema(implementation = Movie::class),
                examples = arrayOf(
                    ExampleObject(
                        name = "movie", value =
                        """ 
                        {
                            "title": "The Fast and the Furious",
                            "description": "Los Angeles police officer Brian O'Conner must decide where his loyalty really
                             lies when he becomes enamored with the street racing world he has been sent undercover to destroy.",
                            "beginDate": "22 Jul 2021",
                            "endDate": "29 Jul 2021",
                            "showTimes": [{
                                "day": "MONDAY",
                                "schedule": [{
                                    "time": "14:30",
                                    "price": "5.5"
                                }]
                            }]
                        }
                        """
                    )
                )
            )]
        ),
        ApiResponse(responseCode = "404", description = "Movie title not found"),
        ApiResponse(responseCode = "500", description = "Some backend error, check error description")
    )
    @PutMapping(value = ["/movies/{movieTitle}"])
    fun updateMovieShowTimes(
        @PathVariable movieTitle: String,
        @RequestBody showTimes: List<ShowTime>
    ): ResponseEntity<Movie> {
        return ResponseEntity.ok(
            updateMovieAction.execute(
                movieTitle,
                showTimes.map { showTime -> showTime.toDomain() }
            ).toInfra()
        )
    }

    @Operation(summary = "Get movie details from OMDB Api", operationId = "getMovieDetails")
    @ApiResponses(
        ApiResponse(
            responseCode = "200", description = "Movie details successfully retrieved", content = [Content(
                schema = Schema(implementation = OmdbMovie::class),
                examples = arrayOf(
                    ExampleObject(
                        name = "omdbMovie", value =
                        """ 
                            {
                              "Title": "The Fast and the Furious",
                              "Year": "2001",
                              "Rated": "PG-13",
                              "Released": "22 Jun 2001",
                              "Runtime": "106 min",
                              "Genre": "Action, Crime, Thriller",
                              "Director": "Rob Cohen",
                              "Writer": "Ken Li (magazine article \"Racer X\"), Gary Scott Thompson (screen story), Gary Scott Thompson (screenplay), Erik Bergquist (screenplay), David Ayer (screenplay)",
                              "Actors": "Paul Walker, Vin Diesel, Michelle Rodriguez, Jordana Brewster",
                              "Plot": "Los Angeles police officer Brian O'Conner must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to destroy.",
                              "Language": "English, Spanish",
                              "Country": "USA, Germany",
                              "Awards": "11 wins & 12 nominations.",
                              "Poster": "https://m.media-amazon.com/images/M/MV5BNzlkNzVjMDMtOTdhZC00MGE1LTkxODctMzFmMjkwZmMxZjFhXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg",
                              "Ratings": [
                                {
                                  "Source": "Internet Movie Database",
                                  "Value": "6.8/10"
                                }
                              ],
                              "Metascore": "58",
                              "imdbRating": "6.8",
                              "imdbVotes": "322,264",
                              "imdbID": "tt0232500",
                              "Type": "movie",
                              "DVD": "01 Jan 2002",
                              "BoxOffice": "${'$'}142,542,950",
                              "Production": "Universal Pictures",
                              "Website": "http://www.thefastandthefurious.com",
                              "Response": "True"
                            }
                        """
                    )
                )
            )]
        ),
        ApiResponse(responseCode = "404", description = "Movie not found on OMDB"),
        ApiResponse(responseCode = "500", description = "Some backend error, check error description")
    )
    @GetMapping(value = ["/movies/{imdbId}/details"])
    fun getMovieDetails(@PathVariable imdbId: String): ResponseEntity<OmdbMovie> {
        return ResponseEntity.ok(fetchMovieDetailsAction.execute(imdbId))
    }
}