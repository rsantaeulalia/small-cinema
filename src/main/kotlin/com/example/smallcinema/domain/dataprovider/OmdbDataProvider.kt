package com.example.smallcinema.domain.dataprovider

import com.example.smallcinema.domain.model.ImdbMovie

interface OmdbDataProvider {

    fun getMovieDetailByImdbId(imdbId: String): ImdbMovie?

}