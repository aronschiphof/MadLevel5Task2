package com.example.madlevel5task2.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.madlevel5task2.dao.GameDao
import com.example.madlevel5task2.database.GameBacklogRoomDatabase
import com.example.madlevel5task2.model.Game

class GameRepository(context: Context) {

    private val gameDao: GameDao

    init {
        val database = GameBacklogRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun getAllGames(): LiveData<List<Game>>{
        return gameDao.getAllGames()
    }

    suspend fun deleteAll(){
        gameDao.deleteAll()
    }

    suspend fun deleteGame(game: Game){
        gameDao.deleteGame(game)
    }

    suspend fun insertGame(game: Game){
        gameDao.insertGame(game)
    }

}