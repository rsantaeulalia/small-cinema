package com.example.smallcinema.domain.dataprovider

import com.example.smallcinema.domain.model.OmdbMovie

interface OmdbDataProvider {

    fun getMovieDetailByImdbId(imdbId: String): OmdbMovie?

}