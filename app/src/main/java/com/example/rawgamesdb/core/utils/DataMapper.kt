package com.example.rawgamesdb.core.utils

import com.example.rawgamesdb.core.data.source.local.entity.GameEntity
import com.example.rawgamesdb.core.data.source.remote.response.GameDetailResponse
import com.example.rawgamesdb.core.data.source.remote.response.ResultsItem
import com.example.rawgamesdb.core.domain.model.Game

object DataMapper {

    fun mapGameResponseToDomain(gameResponse: List<ResultsItem>):List<Game> {
        val gameList = ArrayList<Game>()
        gameResponse.map {
            val game = Game(
                added = it.added,
                suggestionsCount = it.suggestionsCount,
                rating = it.rating,
                metacritic = it.metacritic,
                playtime = it.playtime,
                backgroundImage = it.backgroundImage,
                tba = it.tba,
                ratingTop = it.ratingTop,
                reviewsTextCount = it.reviewsTextCount,
                name = it.name,
                id = it.id,
                ratingsCount = it.ratingsCount,
                updated = it.updated,
                slug = it.slug,
                released = it.released,
                description = null,
                favorite = false
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapGameDetailResponseToEntities(gameDetailResponse: GameDetailResponse):GameEntity {
        return GameEntity(
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
            description = gameDetailResponse.description,
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
            favorite = game.favorite,
            description = game.description
        )
    }

    fun mapGameEntitiesToDomain(game:GameEntity?): Game =
        Game(
            added = game?.added,
            suggestionsCount = game?.suggestionsCount,
            rating = game?.rating,
            metacritic = game?.metacritic,
            playtime = game?.playtime,
            backgroundImage = game?.backgroundImage,
            tba = game?.tba,
            ratingTop = game?.ratingTop,
            reviewsTextCount = game?.reviewsTextCount,
            name = game?.name,
            id = game?.id,
            ratingsCount = game?.ratingsCount,
            updated = game?.updated,
            slug = game?.slug,
            released = game?.released,
            favorite = game?.favorite ?: false,
            description = game?.description
        )

    fun mapGameDomainToEntities(game:Game): GameEntity = GameEntity(
            added = game.added!!,
            suggestionsCount = game.suggestionsCount!!,
            rating = game.rating!!,
            metacritic = game.metacritic!!,
            playtime = game.playtime!!,
            backgroundImage = game.backgroundImage!!,
            tba = game.tba!!,
            ratingTop = game.ratingTop!!,
            reviewsTextCount = game.reviewsTextCount!!,
            name = game.name!!,
            id = game.id!!,
            ratingsCount = game.ratingsCount!!,
            updated = game.updated!!,
            slug = game.slug!!,
            released = game.released!!,
            favorite = game.favorite,
            description = game.description
        )

    fun mapGameDetailResponseToDomain(gameResponse: GameDetailResponse) : Game = Game(
        added = gameResponse.added,
        suggestionsCount = gameResponse.suggestionsCount,
        rating = gameResponse.rating,
        metacritic = gameResponse.metacritic,
        playtime = gameResponse.playtime,
        backgroundImage = gameResponse.backgroundImage,
        tba = gameResponse.tba,
        ratingTop = gameResponse.ratingTop,
        reviewsTextCount = gameResponse.reviewsTextCount,
        name = gameResponse.name,
        id = gameResponse.id,
        ratingsCount = gameResponse.ratingsCount,
        updated = gameResponse.updated,
        slug = gameResponse.slug,
        released = gameResponse.released,
        favorite = false,
        description = gameResponse.description
    )

    fun getGameDetailEmpty():Game = Game(
        added = null,
        suggestionsCount = null,
        rating = null,
        metacritic = null,
        playtime = null,
        backgroundImage = null,
        tba = null,
        ratingTop = null,
        reviewsTextCount = null,
        name = null,
        id = null,
        ratingsCount = null,
        updated = null,
        slug = null,
        released = null,
        description = null,
        favorite = false
    )
}