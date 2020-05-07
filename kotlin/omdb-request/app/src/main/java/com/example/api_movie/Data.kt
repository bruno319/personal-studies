package com.example.api_movie
import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("Response")
    val Response: String,
    @SerializedName("Search")
    val Movies: List<Movie>,
    @SerializedName("totalResults")
    val totalResults: String
)

data class Movie(
    @SerializedName("Poster")
    val Poster: String,
    @SerializedName("Title")
    val Title: String,
    @SerializedName("Type")
    val Type: String,
    @SerializedName("Year")
    val Year: String,
    @SerializedName("imdbID")
    val imdbID: String
)