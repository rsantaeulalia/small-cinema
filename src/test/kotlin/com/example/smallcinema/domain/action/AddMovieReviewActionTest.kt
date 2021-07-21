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


class AddMovieReviewActionTest {

    lateinit var addReviewAction: AddMovieReviewAction

    @MockK
    private lateinit var movieDataProvider: MovieDataProvider

    @BeforeEach
    fun setUp() {
        init(this)
        addReviewAction = AddMovieReviewAction(movieDataProvider)
    }

    companion object {
        var review = "Amazing movie"
        var rating = 5F
        var movieTitle = "Fast Five"

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
    fun `given an review for a movie when add review then movies must be returned`() {
        val movieWithReview = movie1.copy(reviews = movie1.reviews.plus(Review(null, null, review, rating)))

        every { movieDataProvider.findByTitle(movieTitle) } returns movie1
        every { movieDataProvider.save(movieWithReview) } returns movieWithReview

        val movieSaved = addReviewAction.execute(movieTitle, null, review, rating)

        verify(exactly = 1) { movieDataProvider.findByTitle(movieTitle) }
        verify(exactly = 1) { movieDataProvider.save(movieWithReview) }
        assertEquals(movieWithReview, movieSaved)
    }

    @Test
    fun `given an review for a movie that doesn't exist when add review then an exception is thrown`() {
        every { movieDataProvider.findByTitle(movieTitle) } returns null

        Assertions.assertThrows(MovieNotFoundException::class.java) {
            addReviewAction.execute(
                movieTitle,
                null,
                review,
                rating
            )
        }

        verify(exactly = 1) { movieDataProvider.findByTitle(movieTitle) }
    }
}