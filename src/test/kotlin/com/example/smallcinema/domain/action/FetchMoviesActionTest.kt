package com.example.smallcinema.domain.action

import com.example.smallcinema.domain.dataprovider.MovieDataProvider
import com.example.smallcinema.domain.model.Movie
import com.example.smallcinema.domain.model.Review
import com.example.smallcinema.domain.model.ShowTime
import com.example.smallcinema.domain.model.TimeSchedule
import io.mockk.MockKAnnotations.init
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime

class FetchMoviesActionTest {

    lateinit var fetchMoviesAction: FetchMoviesAction

    @MockK
    private lateinit var movieDataProvider: MovieDataProvider

    @BeforeEach
    fun setUp() {
        init(this)
        fetchMoviesAction = FetchMoviesAction(movieDataProvider)
    }

    companion object {
        var movie1 = Movie(
            null, "Furious 7", null, "tt2820852", LocalDate.now().minusDays(7),
            LocalDate.now().plusDays(7), listOf(
                ShowTime(
                    null, DayOfWeek.FRIDAY, listOf(
                        TimeSchedule(null, LocalDateTime.now().minusHours(3), 5.5F),
                        TimeSchedule(null, LocalDateTime.now().minusHours(1), 5.5F)
                    )
                )
            ), listOf()
        )

        var movie2 = Movie(
            null, "2 Fast 2 Furious", null, "tt0322259", LocalDate.now().minusDays(7),
            LocalDate.now().plusDays(7), listOf(
                ShowTime(
                    null, DayOfWeek.FRIDAY, listOf(TimeSchedule(null, LocalDateTime.now().minusHours(3), 5.5F))
                ),
                ShowTime(
                    null,
                    DayOfWeek.SATURDAY,
                    listOf(TimeSchedule(null, LocalDateTime.now().minusHours(3), 5.5F))
                )
            ), listOf()
        )

        var movie3 = Movie(
            null, "Fast Five", null, "tt1596343", LocalDate.now().minusDays(7),
            LocalDate.now().plusDays(7), listOf(
                ShowTime(
                    null, DayOfWeek.FRIDAY, listOf(TimeSchedule(null, LocalDateTime.now().minusHours(3), 5.5F))
                )
            ), listOf(Review(null, "Test Customer", "Best movie ever", 5F))
        )
    }

    @Test
    fun `given a execute action on fetch movies when fetch all then movies list must be returned`() {
        val moviesList = listOf(movie1, movie2)

        every { movieDataProvider.findAll() } returns moviesList

        val retrievedMovies = fetchMoviesAction.execute()

        verify(exactly = 1) { movieDataProvider.findAll() }
        assertEquals(retrievedMovies, moviesList)
    }

    @Test
    fun `given a execute action on fetch movies when no movies exists on repository then an empty list must be returned`() {

        every { movieDataProvider.findAll() } returns emptyList()

        val retrievedMovies = fetchMoviesAction.execute()

        verify(exactly = 1) { movieDataProvider.findAll() }
        assertTrue(retrievedMovies.isEmpty())
    }
}