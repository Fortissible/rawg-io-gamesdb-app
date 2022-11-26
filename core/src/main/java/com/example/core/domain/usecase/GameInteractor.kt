package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Game
import com.example.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameInteractor @Inject constructor(private val gameRepository: IGameRepository):GameUseCase {
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