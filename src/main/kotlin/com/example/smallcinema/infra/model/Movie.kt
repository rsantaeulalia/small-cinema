package com.example.smallcinema.infra.model


data class Movie(val title: String,
                 val description: String? = null,
                 val showTimes: List<ShowTime>)
