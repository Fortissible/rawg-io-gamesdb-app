package com.example.rawgamesdb.core.domain.usecase

import com.example.rawgamesdb.core.domain.model.Game
import com.example.rawgamesdb.core.domain.repository.IGameRepository

class GameInteractor (private val gameRepository: IGameRepository):GameUseCase {
    override fun getAllGameFromApi(key: String) =
        gameRepository.getAllGameFromApi(key)

    override fun getGameDetail(id: String, key: String) =
        gameRepository.getGameDetail(id,key)

    override fun getAllFavouritedGame() =
        gameRepository.getAllFavouritedGame()

    override suspend fun deleteFavouriteGame(game: Game) =
        gameRepository.deleteFavouriteGame(game)

    override suspend fun insertFavourite(game: Game) =
        gameRepository.insertFavourite(game)
}