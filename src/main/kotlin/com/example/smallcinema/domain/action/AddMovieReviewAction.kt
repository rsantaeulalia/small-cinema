package com.example.smallcinema.domain.action

import com.example.smallcinema.domain.dataprovider.MovieDataProvider
import com.example.smallcinema.domain.exception.MovieNotFoundException
import com.example.smallcinema.domain.model.Movie
import com.example.smallcinema.domain.model.Review
import org.springframework.stereotype.Service

@Service
class AddMovieReviewAction(private val movieDataProvider: MovieDataProvider) {
    fun execute(movieTitle: String, customerName: String?, comment: String?, rating: Float): Movie {
        return movieDataProvider.findByTitle(movieTitle)?.let {
            movieDataProvider.save(it.copy(reviews = it.reviews.plus(Review(null, customerName, comment, rating))))
        } ?: throw MovieNotFoundException(movieTitle)
    }
}
