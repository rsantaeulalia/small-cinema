package com.example.smallcinema.infra.model

data class OmdbMovie(
    var title: String?,
    var year: Int?,
    var rated: String?,
    var released: String?,
    var runtime: String?,
    var genre: String?,
    var director: String?,
    var writer: String?,
    var actors: String?,
    var plot: String?,
    var language: String?,
    var country: String?,
    var awards: String?,
    var poster: String?,
    var ratings: List<Ratings>?,
    var metascore: Int?,
    var imdbRating: Double?,
    var imdbVotes: String?,
    var imdbID: String?,
    var type: String?,
    var dVD: String?,
    var boxOffice: String?,
    var production: String?,
    var website: String?,
    var response: Boolean?
)

data class Ratings(
    val source: String,
    val value: String
)
