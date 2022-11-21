package com.example.rawgamesdb.core.data.source.local.room

import androidx.room.*
import com.example.rawgamesdb.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM game")
    fun getAllGame(): Flow<List<GameEntity>>

    @Query("SELECT * FROM game WHERE favorite = 1")
    fun getAllFavouritedGame(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteGame(game:GameEntity)

    @Update
    fun updateFavouriteGame(game:GameEntity)
}