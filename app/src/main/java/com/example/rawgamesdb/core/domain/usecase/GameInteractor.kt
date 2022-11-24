package com.example.rawgamesdb.core.domain.usecase

import com.example.rawgamesdb.core.domain.model.Game
import com.example.rawgamesdb.core.domain.model.GameDetail
import com.example.rawgamesdb.core.domain.repository.IGameRepository

class GameInteractor (private val gameRepository: IGameRepository):GameUseCase {
    override fun getAllGameFromApi(key: String) = gameRepository.getAllGameFromApi(key)

    override fun getGameDetailFromApi(id: String, key: String) =
        gameRepository.getGameDetailFromApi(id,key)

    override fun getAllFavouritedGame() = gameRepository.getAllFavouritedGame()

    override fun deleteFavouriteGame(game: GameDetail) =
        gameRepository.deleteFavouriteGame(game)

    override suspend fun insertFavourite(game: GameDetail) =
        gameRepository.insertFavourite(game)
}