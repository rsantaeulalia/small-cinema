package com.example.smallcinema.infra.dataprovider.repository

import com.example.smallcinema.domain.dataprovider.MovieDataProvider
import com.example.smallcinema.domain.model.Movie
import com.example.smallcinema.infra.dataprovider.repository.mongo.MongoMovieRepository
import org.springframework.stereotype.Component

@Component
class MovieRepository(private val mongoRepository: MongoMovieRepository) : MovieDataProvider {
    override fun save(movie: Movie): Movie {
        return mongoRepository.save(movie)
    }

    override fun update(movie: Movie): Movie {
        return mongoRepository.save(movie)
    }

    override fun findAll(): List<Movie> {
        return mongoRepository.findAll()
    }
}