package com.example.rawgamesdb.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val added: Int?,
    val suggestionsCount: Int?,
    val rating: Double?,
    val metacritic: Int?,
    val playtime: Int?,
    val backgroundImage: String?,
    val tba: Boolean?,
    val ratingTop: Int?,
    val reviewsTextCount: String?,
    val name: String?,
    val id: Int?,
    val ratingsCount: Int?,
    val updated: String?,
    val slug: String?,
    val released: String?,
    var favorite: Boolean,
    val description: String?
):Parcelable