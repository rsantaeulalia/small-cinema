package com.example.smallcinema.infra.dataprovider.http

import com.example.smallcinema.domain.dataprovider.OmdbDataProvider
import com.example.smallcinema.domain.exception.ImdbClientFailureException
import com.example.smallcinema.domain.exception.MovieNotFoundOnImdbException
import com.example.smallcinema.domain.model.OmdbMovie
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import kong.unirest.Unirest
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class OmdbClient(
    @Value("\${imdb-api.url}") private val imdbUrl: String,
    @Value("\${imdb-api.location}") val location: String,
    @Value("\${imdb-api.apikey}") val apiKey: String,
    private val objectMapper: ObjectMapper
) : OmdbDataProvider {

    override fun getMovieDetailByImdbId(imdbId: String): OmdbMovie? {
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_VALUES, true)
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
        return runCatching {
            val response = Unirest.get("$imdbUrl${location.replace("{apiKey}", apiKey).replace("{imdbID}", imdbId)}")
                .header("Content-Type", "application/json")
                .asJson()

            when (response?.status?.let { HttpStatus.valueOf(it) }) {
                HttpStatus.ACCEPTED, HttpStatus.OK -> {
                    objectMapper.readValue(response.body.toString(), OmdbMovie::class.java)
                }
                else -> throw MovieNotFoundOnImdbException(imdbId)
            }
        }.onFailure { e ->
            throw ImdbClientFailureException()
        }.getOrDefault(null)
    }
}