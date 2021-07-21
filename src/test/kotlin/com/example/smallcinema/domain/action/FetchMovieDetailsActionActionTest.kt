package com.example.smallcinema.domain.action

import com.example.smallcinema.domain.dataprovider.OmdbDataProvider
import com.example.smallcinema.domain.exception.MovieNotFoundOnOmdbException
import com.example.smallcinema.infra.model.OmdbMovie
import com.example.smallcinema.infra.model.Ratings
import io.mockk.MockKAnnotations.init
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class FetchMovieDetailsActionActionTest {

    lateinit var fetchMovieDetailsAction: FetchMovieDetailsAction

    @MockK
    private lateinit var omdbMovieDataProvider: OmdbDataProvider

    @BeforeEach
    fun setUp() {
        init(this)
        fetchMovieDetailsAction = FetchMovieDetailsAction(omdbMovieDataProvider)
    }

    companion object {
        var validImdbId = "tt1013752"
        var invalidImdbId = "invalidId"

        var movie1 = OmdbMovie(
            title = "The Fast and the Furious",
            year = 2001,
            rated = "PG-13",
            released = "22 Jun 2001",
            runtime = "106 min",
            genre = "Action, Crime, Thriller",
            director = "Rob Cohen",
            writer = "Ken Li (magazine article \"Racer X\"), Gary Scott Thompson (screen story), Gary Scott Thompson (screenplay), Erik Bergquist (screenplay), David Ayer (screenplay)",
            actors = "Paul Walker, Vin Diesel, Michelle Rodriguez, Jordana Brewster",
            plot = "Los Angeles police officer Brian O'Conner must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to destroy.",
            language = "English, Spanish",
            country = "USA, Germany",
            awards = "11 wins & 12 nominations.",
            poster = "https://m.media-amazon.com/images/M/MV5BNzlkNzVjMDMtOTdhZC00MGE1LTkxODctMzFmMjkwZmMxZjFhXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg",
            ratings = listOf(Ratings("Internet Movie Database", "6.8/10")),
            metascore = 58,
            imdbRating = 6.8,
            imdbVotes = "322,264",
            imdbID = "tt0232500",
            type = "movie",
            dVD = "01 Jan 2002",
            boxOffice = "$142,542,950",
            production = "Universal Pictures",
            website = "http://www.thefastandthefurious.com",
            response = true
        )
    }

    @Test
    fun `given an imdb id for a movie when fetch details from client then movie details must be returned`() {

        every { omdbMovieDataProvider.getMovieDetailByImdbId(validImdbId) } returns movie1

        val movieSaved = fetchMovieDetailsAction.execute(validImdbId)

        verify(exactly = 1) { omdbMovieDataProvider.getMovieDetailByImdbId(validImdbId) }

        assertEquals(movie1, movieSaved)
    }

    @Test
    fun `given an invalid imdb id for a movie when fetch details from client then throw exception`() {
        every { omdbMovieDataProvider.getMovieDetailByImdbId(invalidImdbId) } returns null

        Assertions.assertThrows(MovieNotFoundOnOmdbException::class.java) {
            fetchMovieDetailsAction.execute(invalidImdbId)
        }

        verify(exactly = 1) { omdbMovieDataProvider.getMovieDetailByImdbId(invalidImdbId) }
    }
}