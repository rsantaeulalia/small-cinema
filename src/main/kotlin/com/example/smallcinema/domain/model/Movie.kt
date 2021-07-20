package com.example.smallcinema.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
data class Movie(
    @Id
    val id: String? = null,
    val title: String,
    val description: String? = null,
    val imdbId: String? = null,
    val beginDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val showTimes: List<ShowTime>,
    val reviews: List<Review>
)
