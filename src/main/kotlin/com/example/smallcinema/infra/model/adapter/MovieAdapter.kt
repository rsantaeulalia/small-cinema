package com.example.smallcinema.infra.model.adapter

import com.example.smallcinema.domain.model.Movie
import com.example.smallcinema.domain.model.ShowTime
import com.example.smallcinema.domain.model.TimeSchedule
import java.time.DayOfWeek
import com.example.smallcinema.infra.model.Movie as MovieInfra
import com.example.smallcinema.infra.model.ShowTime as ShowTimeInfra
import com.example.smallcinema.infra.model.TimeSchedule as TimeScheduleInfra

fun Movie.toInfra(): MovieInfra {
    return MovieInfra(
        this.title,
        this.description,
        this.beginDate,
        this.endDate,
        this.showTimes.map { showTime -> showTime.toInfra() })
}

fun ShowTime.toInfra(): ShowTimeInfra {
    return ShowTimeInfra(this.day.name, this.schedule.map { timeSchedule -> timeSchedule.toInfra() })
}

fun TimeSchedule.toInfra(): TimeScheduleInfra {
    return TimeScheduleInfra(this.time, this.price)
}

fun MovieInfra.toDomain(): Movie {
    return Movie(
        null,
        this.title,
        this.description,
        null,
        this.beginDate,
        this.endDate,
        this.showTimes.map { showTime -> showTime.toDomain() },
        listOf()
    )
}

fun ShowTimeInfra.toDomain(): ShowTime {
    return ShowTime(
        null,
        DayOfWeek.valueOf(this.day),
        this.schedule.map { timeSchedule -> timeSchedule.toDomain() })
}

fun TimeScheduleInfra.toDomain(): TimeSchedule {
    return TimeSchedule(null, this.time, this.price)
}