package com.example.madlevel5task2.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.madlevel5task2.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val gameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val games: LiveData<List<Game>> = gameRepository.getAllGames()

    fun insertGame(game: Game){
        mainScope.launch {
            gameRepository.insertGame(game)
        }
    }

    fun deleteGame(game: Game){
        mainScope.launch {
            gameRepository.deleteGame(game)
        }
    }

    fun deleteAllGames(){
        mainScope.launch {
            gameRepository.deleteAll()
        }
    }
}