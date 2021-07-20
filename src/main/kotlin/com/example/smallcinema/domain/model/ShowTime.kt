package com.example.smallcinema.domain.model

import java.time.DayOfWeek
import java.time.LocalDate

data class ShowTime(
    val id: Long? = null,
    val day: DayOfWeek,
    val beginDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val schedule: List<TimeSchedule>
)
