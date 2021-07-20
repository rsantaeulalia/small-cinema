package com.example.smallcinema.domain.model

import java.time.DayOfWeek
import java.time.LocalDate

data class ShowTime(
    val id: Long,
    val day: DayOfWeek,
    val beginDate: LocalDate,
    val endDate: LocalDate,
    val schedule: List<TimeSchedule>
)
