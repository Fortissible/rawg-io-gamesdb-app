package com.example.rawgamesdb.core.utils

import com.example.rawgamesdb.core.data.source.local.entity.GameEntity
import com.example.rawgamesdb.core.data.source.remote.response.GameDetailResponse
import com.example.rawgamesdb.core.data.source.remote.response.LoginResponse
import com.example.rawgamesdb.core.data.source.remote.response.ResultsItem
import com.example.rawgamesdb.core.domain.model.Game
import com.example.rawgamesdb.core.domain.model.LoginToken

object DataMapper {
    fun mapGameDetailResponseToDomain(gameDetailResponse: GameDetailResponse):Game {
        return Game(
            added = gameDetailResponse.added,
            suggestionsCount = gameDetailResponse.suggestionsCount,
            rating = gameDetailResponse.rating,
            metacritic = gameDetailResponse.metacritic,
            playtime = gameDetailResponse.playtime,
            backgroundImage = gameDetailResponse.backgroundImage,
            tba = gameDetailResponse.tba,
            ratingTop = gameDetailResponse.ratingTop,
            reviewsTextCount = gameDetailResponse.reviewsTextCount,
            name = gameDetailResponse.name,
            id = gameDetailResponse.id,
            ratingsCount = gameDetailResponse.ratingsCount,
            updated = gameDetailResponse.updated,
            slug = gameDetailResponse.slug,
            released = gameDetailResponse.released,
            favorite = false
        )
    }

    fun mapGamesEntitiesToDomain(games:List<GameEntity>): List<Game> = games.map { game ->
        Game(
            added = game.added,
            suggestionsCount = game.suggestionsCount,
            rating = game.rating,
            metacritic = game.metacritic,
            playtime = game.playtime,
            backgroundImage = game.backgroundImage,
            tba = game.tba,
            ratingTop = game.ratingTop,
            reviewsTextCount = game.reviewsTextCount,
            name = game.name,
            id = game.id,
            ratingsCount = game.ratingsCount,
            updated = game.updated,
            slug = game.slug,
            released = game.released,
            favorite = game.favorite
        )
    }

    fun mapGameDomainToEntities(game:Game): GameEntity = GameEntity(
            added = game.added,
            suggestionsCount = game.suggestionsCount,
            rating = game.rating,
            metacritic = game.metacritic,
            playtime = game.playtime,
            backgroundImage = game.backgroundImage,
            tba = game.tba,
            ratingTop = game.ratingTop,
            reviewsTextCount = game.reviewsTextCount,
            name = game.name,
            id = game.id,
            ratingsCount = game.ratingsCount,
            updated = game.updated,
            slug = game.slug,
            released = game.released,
            favorite = game.favorite
        )

    fun mapLoginResponseToDomain(response:LoginResponse):LoginToken =
        LoginToken(
            token = response.token
        )
}