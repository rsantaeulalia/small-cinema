package com.example.smallcinema.infra.dataprovider.repository

import com.example.smallcinema.domain.dataprovider.MovieDataProvider
import com.example.smallcinema.infra.dataprovider.repository.mongo.MongoMovieRepository
import com.example.smallcinema.infra.model.Movie
import org.springframework.stereotype.Component

@Component
class MovieRepository(private val mongoRepository: MongoMovieRepository) : MovieDataProvider {
    override fun save(movie: Movie): Movie {
        TODO("Not yet implemented")
    }

    override fun update(movie: Movie): Movie {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Movie> {
        TODO("Not yet implemented")
    }
}