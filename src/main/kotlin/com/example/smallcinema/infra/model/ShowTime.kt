package com.example.smallcinema.infra.model

import java.time.DayOfWeek
import java.time.LocalDate

data class ShowTime(
    val day: DayOfWeek,
    val schedule: List<TimeSchedule>,
    val endDate: LocalDate? = null,
    val beginDate: LocalDate? = null
)