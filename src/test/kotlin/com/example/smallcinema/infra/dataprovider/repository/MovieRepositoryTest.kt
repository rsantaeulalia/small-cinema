package com.example.smallcinema.infra.dataprovider.repository

import com.example.smallcinema.domain.model.Movie
import com.example.smallcinema.domain.model.Review
import com.example.smallcinema.domain.model.ShowTime
import com.example.smallcinema.domain.model.TimeSchedule
import com.example.smallcinema.infra.dataprovider.repository.mongo.MongoMovieRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime

@DataMongoTest
@ExtendWith(SpringExtension::class)
class MovieRepositoryTest {

    @Autowired
    private lateinit var movieRepository: MongoMovieRepository

    lateinit var movie: Movie
    lateinit var listOfMovies: List<Movie>
    lateinit var persistedMovie: Movie

    @Test
    fun `given a Movie to save when the repository save method is called then is correctly persisted`() {
        movie = Movie(
            null, "Furious 7", null, "tt2820852", LocalDate.now().minusDays(7),
            LocalDate.now().plusDays(7), listOf(
                ShowTime(
                    null, DayOfWeek.FRIDAY, listOf(TimeSchedule(null, LocalDateTime.now().minusHours(3), 5.5F))
                )
            ), listOf(Review(null, "Test Customer", "Best movie ever", 5F))
        )

        persistedMovie = movieRepository.save(movie)

        Assertions.assertNotNull(persistedMovie)
        Assertions.assertEquals(persistedMovie.title, movie.title)
        Assertions.assertEquals(persistedMovie.showTimes.size, 1)
        Assertions.assertEquals(persistedMovie.showTimes[0].schedule.size, 1)
        Assertions.assertEquals(persistedMovie.reviews.size, 1)
    }

    @Test
    fun `given a previously saved Movie when the repository update method is called then is correctly modified`() {
        movie = Movie(
            null, "Furious 7", null, "tt2820852", LocalDate.now().minusDays(7),
            LocalDate.now().plusDays(7), listOf(
                ShowTime(
                    null, DayOfWeek.FRIDAY, listOf(TimeSchedule(null, LocalDateTime.now().minusHours(3), 5.5F))
                )
            ), listOf(Review(null, "Test Customer", "Best movie ever", 5F))
        )

        persistedMovie = movieRepository.save(movie)

        persistedMovie = movieRepository.save(
            persistedMovie.copy(
                title = "Furious 9", showTimes = persistedMovie.showTimes.plus(
                    ShowTime(
                        null, DayOfWeek.MONDAY, listOf(TimeSchedule(null, LocalDateTime.now().minusHours(5), 2.5F))
                    )
                )
            )
        )

        Assertions.assertNotNull(persistedMovie)
        Assertions.assertEquals(persistedMovie.title, "Furious 9")
        Assertions.assertEquals(persistedMovie.showTimes.size, 2)
        Assertions.assertEquals(persistedMovie.showTimes[0].schedule.size, 1)
        Assertions.assertEquals(persistedMovie.reviews.size, 1)
    }

    @Test
    fun `given a list of movies when the repository findAll method is called then the current size is correct`() {
        movieRepository.saveAll(
            listOf(
                Movie(
                    null, "Furious 7", null, "tt2820852", LocalDate.now().minusDays(7),
                    LocalDate.now().plusDays(7), listOf(
                        ShowTime(
                            null, DayOfWeek.FRIDAY, listOf(
                                TimeSchedule(null, LocalDateTime.now().minusHours(3), 5.5F),
                                TimeSchedule(null, LocalDateTime.now().minusHours(1), 5.5F)
                            )
                        )
                    ), listOf()
                ),
                Movie(
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
                ),
                Movie(
                    null, "Fast Five", null, "tt1596343", LocalDate.now().minusDays(7),
                    LocalDate.now().plusDays(7), listOf(
                        ShowTime(
                            null, DayOfWeek.FRIDAY, listOf(TimeSchedule(null, LocalDateTime.now().minusHours(3), 5.5F))
                        )
                    ), listOf(Review(null, "Test Customer", "Best movie ever", 5F))
                )
            )
        )

        listOfMovies = movieRepository.findAll()

        Assertions.assertEquals(movieRepository.count(), 3)
        Assertions.assertEquals(listOfMovies.size, 3)
        Assertions.assertTrue(listOfMovies.any { movie -> movie.title == "Fast Five" })
    }

    @AfterEach
    fun clear() {
        movieRepository.deleteAll()
    }
}