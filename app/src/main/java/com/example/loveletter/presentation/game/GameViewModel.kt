package com.example.loveletter.presentation.game

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loveletter.TAG
import com.example.loveletter.util.game.GameServer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class GameViewModel : ViewModel() {

    private val loadingState = MutableStateFlow<GameState>(GameState.Loading)

    val state = loadingState.asStateFlow()


    val roomCode = mutableStateOf("1234")

    fun observeRoom() {
        viewModelScope.launch {
            Log.d(TAG, "roomCode in viewModel: ${roomCode.value}")
            GameServer.subscribeToRealtimeUpdates(roomCode.value).map { gameRoom ->
                GameState.Loaded(
                    gameRoom = gameRoom
                )
            }.collect {
                loadingState.emit(it)
            }
        }
    }
}