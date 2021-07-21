package com.example.smallcinema.domain.action

import com.example.smallcinema.domain.dataprovider.OmdbDataProvider
import com.example.smallcinema.domain.exception.MovieNotFoundOnImdbException
import com.example.smallcinema.domain.model.OmdbMovie
import org.springframework.stereotype.Service

@Service
class FetchMovieDetailsAction(private val omdbDataProvider: OmdbDataProvider) {
    fun execute(imdbId: String): OmdbMovie {
        return omdbDataProvider.getMovieDetailByImdbId(imdbId) ?: throw MovieNotFoundOnImdbException(imdbId)
    }
}
