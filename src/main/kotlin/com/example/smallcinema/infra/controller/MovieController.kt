package com.example.smallcinema.infra.controller

import com.example.smallcinema.domain.action.AddMovieReviewAction
import com.example.smallcinema.domain.action.FetchMoviesAction
import com.example.smallcinema.domain.action.UpdateMovieAction
import com.example.smallcinema.infra.model.Movie
import com.example.smallcinema.infra.model.Review
import com.example.smallcinema.infra.model.ShowTime
import com.example.smallcinema.infra.model.adapter.toDomain
import com.example.smallcinema.infra.model.adapter.toInfra
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping(value = ["/v1/api/small-cinema/"])
@RestController
class MovieController(
    private val fetchMoviesAction: FetchMoviesAction,
    private val addMovieReviewAction: AddMovieReviewAction,
    private val updateMovieAction: UpdateMovieAction
) {

    @GetMapping(value = ["/movies"])
    fun getAllMovies(): ResponseEntity<List<Movie>> {
        val movies = fetchMoviesAction.execute().map { movie -> movie.toInfra() }
        return ResponseEntity.ok(movies)
    }

    @PostMapping(value = ["/movies/{movieTitle}/reviews"])
    fun addReviewToMovie(@PathVariable movieTitle: String, @RequestBody review: Review): ResponseEntity<Movie> {
        return ResponseEntity.ok(
            addMovieReviewAction.execute(
                movieTitle,
                review.customerName,
                review.comment,
                review.rating
            ).toInfra()
        )
    }

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
}