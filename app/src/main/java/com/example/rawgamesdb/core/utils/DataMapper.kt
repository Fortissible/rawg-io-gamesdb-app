package com.example.rawgamesdb.core.utils

import com.example.rawgamesdb.core.data.source.local.entity.GameEntity
import com.example.rawgamesdb.core.data.source.remote.response.ResultsItem
import com.example.rawgamesdb.core.domain.model.Game

object DataMapper {
    fun mapGameResponseToEntities(gameResponse: List<ResultsItem>): List<GameEntity> {
        val gameEntityList = ArrayList<GameEntity>()
        gameResponse.map { game ->
            val gameEntity = GameEntity(
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
                favorite = false
            )
            gameEntityList.add(gameEntity)
        }
        return gameEntityList
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
}