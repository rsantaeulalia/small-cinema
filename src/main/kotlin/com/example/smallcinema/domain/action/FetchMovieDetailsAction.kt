package com.example.smallcinema.domain.action

import com.example.smallcinema.domain.dataprovider.OmdbDataProvider
import com.example.smallcinema.domain.exception.MovieNotFoundOnOmdbException
import com.example.smallcinema.infra.model.OmdbMovie
import org.springframework.stereotype.Service

@Service
class FetchMovieDetailsAction(private val omdbDataProvider: OmdbDataProvider) {
    fun execute(imdbId: String): OmdbMovie {
        return omdbDataProvider.getMovieDetailByImdbId(imdbId) ?: throw MovieNotFoundOnOmdbException(imdbId)
    }
}
