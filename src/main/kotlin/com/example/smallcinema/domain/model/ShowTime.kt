package com.example.smallcinema.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.DayOfWeek
import java.time.LocalDate

@Document
data class ShowTime(
    @Id
    val id: String? = null,
    val day: DayOfWeek,
    val schedule: List<TimeSchedule>
)
