package com.example.smallcinema.infra.dataprovider.repository.mongo

import com.example.smallcinema.domain.model.Movie
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoMovieRepository : MongoRepository<Movie, Long>