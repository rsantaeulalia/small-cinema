package com.example.smallcinema.domain.action

import com.example.smallcinema.domain.dataprovider.MovieDataProvider
import com.example.smallcinema.domain.exception.MovieNotFoundException
import com.example.smallcinema.domain.model.Movie
import com.example.smallcinema.domain.model.ShowTime
import org.springframework.stereotype.Service

@Service
class UpdateMovieAction(private val movieDataProvider: MovieDataProvider) {
    fun execute(movieTitle: String, showTimes: List<ShowTime>): Movie {
        return movieDataProvider.findByTitle(movieTitle)?.let {
            movieDataProvider.save(it.copy(showTimes = it.showTimes.plus(showTimes)))
        } ?: throw MovieNotFoundException(movieTitle)
    }
}
