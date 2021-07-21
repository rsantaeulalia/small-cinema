package com.example.smallcinema.domain.action

import com.example.smallcinema.domain.dataprovider.MovieDataProvider
import com.example.smallcinema.domain.model.Movie
import com.example.smallcinema.domain.model.ShowTime
import com.example.smallcinema.domain.model.TimeSchedule
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import java.time.DayOfWeek
import java.time.LocalDate

@Configuration
class LoadInitialDataAction(private val movieDataProvider: MovieDataProvider) {
    companion object {
        var showTimes = listOf(
            ShowTime(
                null, DayOfWeek.MONDAY, listOf(
                    TimeSchedule(null, "14:00", 5.5F),
                    TimeSchedule(null, "16:00", 7.5F)
                )
            ),
            ShowTime(
                null, DayOfWeek.TUESDAY, listOf(
                    TimeSchedule(null, "14:00", 5.5F),
                    TimeSchedule(null, "16:00", 7.5F)
                )
            )
        )

        var movies = listOf(
            Movie(
                null,
                "The Fast and the Furious",
                null,
                "tt0232500",
                LocalDate.now(),
                LocalDate.now().plusDays(14),
                showTimes,
                listOf()
            ),
            Movie(
                null,
                "2 Fast 2 Furious",
                null,
                "tt0322259",
                LocalDate.now(),
                LocalDate.now().plusDays(15),
                showTimes,
                listOf()
            ),
            Movie(
                null,
                "The Fast and the Furious: Tokyo Drift",
                null,
                "tt0463985",
                LocalDate.now(),
                LocalDate.now().plusDays(15),
                showTimes,
                listOf()
            ),
            Movie(
                null, "Fast & Furious", null, "tt1013752", LocalDate.now(), LocalDate.now().plusDays(15), showTimes,
                listOf()
            ),
            Movie(
                null, "Fast Five", null, "tt1596343", LocalDate.now(), LocalDate.now().plusDays(15), showTimes,
                listOf()
            ),
            Movie(
                null, "Fast & Furious 6", null, "tt1905041", LocalDate.now(), LocalDate.now().plusDays(15), showTimes,
                listOf()
            ),
            Movie(
                null, "Furious 7", null, "tt2820852", LocalDate.now(), LocalDate.now().plusDays(15), showTimes,
                listOf()
            ),
            Movie(
                null,
                "The Fate of the Furious",
                null,
                "tt4630562",
                LocalDate.now(),
                LocalDate.now().plusDays(15),
                showTimes,
                listOf()
            )
        )
    }

    @EventListener(ApplicationReadyEvent::class)
    fun execute() {
        movies.forEach { movieDataProvider.save(it) }
    }
}
