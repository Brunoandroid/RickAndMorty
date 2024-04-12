package com.example.rickandmorty.data.model.character


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    @SerializedName("created")
    val created: String = "",
    @SerializedName("episode")
    val episode: List<String> = emptyList(),
    @SerializedName("gender")
    val gender: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image")
    val image: String = "",
    @SerializedName("location")
    val location: Location = Location(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("origin")
    val origin: Origin = Origin(),
    @SerializedName("species")
    val species: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("url")
    val url: String = ""
): Parcelable