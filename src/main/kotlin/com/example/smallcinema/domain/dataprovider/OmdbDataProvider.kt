package com.example.smallcinema.domain.dataprovider

import com.example.smallcinema.infra.model.OmdbMovie

interface OmdbDataProvider {

    fun getMovieDetailByImdbId(imdbId: String): OmdbMovie?

}