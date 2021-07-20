package com.example.smallcinema.domain.model

data class Movie(val id: Long, val title: String, val description: String, val imdbId: String, val showTimes: List<ShowTime>)
