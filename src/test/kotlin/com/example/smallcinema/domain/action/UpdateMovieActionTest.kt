package com.example.smallcinema.domain.action

import com.example.smallcinema.domain.dataprovider.MovieDataProvider
import com.example.smallcinema.domain.exception.MovieNotFoundException
import com.example.smallcinema.domain.model.Movie
import com.example.smallcinema.domain.model.Review
import com.example.smallcinema.domain.model.ShowTime
import com.example.smallcinema.domain.model.TimeSchedule
import io.mockk.MockKAnnotations.init
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalDate


class UpdateMovieActionTest {

    lateinit var updateMovieAction: UpdateMovieAction

    @MockK
    private lateinit var movieDataProvider: MovieDataProvider

    @BeforeEach
    fun setUp() {
        init(this)
        updateMovieAction = UpdateMovieAction(movieDataProvider)
    }

    companion object {
        var movieTitle = "Fast and Furious"
        var showtimes = listOf(
            ShowTime(
                null, DayOfWeek.MONDAY, listOf(TimeSchedule(null, "14:30", 5.5F))
            ),
            ShowTime(
                null, DayOfWeek.TUESDAY, listOf(TimeSchedule(null, "14:30", 5.5F))
            ),
            ShowTime(
                null, DayOfWeek.WEDNESDAY, listOf(TimeSchedule(null, "14:30", 5.5F))
            )
        )

        var movie1 = Movie(
            null, movieTitle, null, "tt1596343", LocalDate.now().minusDays(7),
            LocalDate.now().plusDays(7), listOf(
                ShowTime(
                    null, DayOfWeek.FRIDAY, listOf(TimeSchedule(null, "14:30", 5.5F))
                )
            ), listOf(Review(null, "Test Customer", "Best movie ever", 5F))
        )
    }

    @Test
    fun `given an list of showtimes for a movie when update then a modified movie must be returned`() {
        val movieWithShowtimes = movie1.copy(showTimes = movie1.showTimes.plus(showtimes))

        every { movieDataProvider.findByTitle(movieTitle) } returns movie1
        every { movieDataProvider.save(movieWithShowtimes) } returns movieWithShowtimes

        val movieSaved = updateMovieAction.execute(movieTitle, showtimes)

        verify(exactly = 1) { movieDataProvider.findByTitle(movieTitle) }
        verify(exactly = 1) { movieDataProvider.save(movieWithShowtimes) }
        assertEquals(movieWithShowtimes, movieSaved)
    }

    @Test
    fun `given an list of showtimes for a movie that doesn't exist when update movie then an exception is thrown`() {
        every { movieDataProvider.findByTitle(movieTitle) } returns null

        Assertions.assertThrows(MovieNotFoundException::class.java) {
            updateMovieAction.execute(movieTitle, showtimes)
        }

        verify(exactly = 1) { movieDataProvider.findByTitle(movieTitle) }
    }
}