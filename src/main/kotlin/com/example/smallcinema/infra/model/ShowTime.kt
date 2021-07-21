package com.example.smallcinema.infra.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.DayOfWeek

data class ShowTime(
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val day: DayOfWeek,
    val schedule: List<TimeSchedule>,
)