package com.example.smallcinema.domain.dataprovider

import com.example.smallcinema.infra.model.Movie

interface MovieDataProvider {

    fun save(movie: Movie): Movie

    fun update(movie: Movie): Movie

    fun findAll(): List<Movie>

}