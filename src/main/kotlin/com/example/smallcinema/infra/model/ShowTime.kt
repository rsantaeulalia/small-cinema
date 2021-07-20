package com.example.smallcinema.infra.model

import java.time.DayOfWeek

data class ShowTime(
    val day: DayOfWeek,
    val schedule: List<TimeSchedule>,
)