package com.example.smallcinema.infra.model

import java.time.LocalDate


data class Movie(val title: String,
                 val description: String? = null,
                 val beginDate: LocalDate? = null,
                 val endDate: LocalDate? = null,
                 val showTimes: List<ShowTime>)
