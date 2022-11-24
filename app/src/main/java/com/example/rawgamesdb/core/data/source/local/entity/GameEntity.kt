package com.example.rawgamesdb.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "added")
    val added: Int,

    @ColumnInfo(name = "suggestionsCount")
    val suggestionsCount: Int,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "metacritic")
    val metacritic: Int,

    @ColumnInfo(name = "playtime")
    val playtime: Int,

    @ColumnInfo(name = "backgroundImage")
    val backgroundImage: String,

    @ColumnInfo(name = "tba")
    val tba: Boolean,

    @ColumnInfo(name = "ratingTop")
    val ratingTop: Int,

    @ColumnInfo(name = "reviewsTextCount")
    val reviewsTextCount: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "ratingsCount")
    val ratingsCount: Int,

    @ColumnInfo(name = "updated")
    val updated: String,

    @ColumnInfo(name = "slug")
    val slug: String,

    @ColumnInfo(name = "released")
    val released: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean
)