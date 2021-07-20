package com.example.smallcinema.domain.model

data class Movie(
    val id: Long,
    val title: String,
    val description: String? = null,
    val imdbId: String,
    val showTimes: List<ShowTime>,
    val reviews: List<Review>
)
