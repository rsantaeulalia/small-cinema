package com.example.smallcinema.domain.action

import com.example.smallcinema.domain.dataprovider.MovieDataProvider
import com.example.smallcinema.domain.model.Movie
import org.springframework.stereotype.Service

@Service
class FetchMoviesAction(private val movieDataProvider: MovieDataProvider) : SmallCinemaAction<List<Movie>> {
    override fun execute(): List<Movie> {
        return movieDataProvider.findAll()
    }
}
