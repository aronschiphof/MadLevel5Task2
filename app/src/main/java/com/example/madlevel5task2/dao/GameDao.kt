package com.example.madlevel5task2.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.madlevel5task2.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM GameTable")
    fun getAllGames(): LiveData<List<Game>>

    @Query("DELETE FROM GameTable")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteGame(game: Game)

    @Insert
    suspend fun insertGame(game: Game)
}