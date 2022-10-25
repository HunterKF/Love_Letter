package com.example.loveletter.presentation.joingame

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loveletter.R
import com.example.loveletter.Screen
import com.example.loveletter.TAG
import com.example.loveletter.presentation.createplayer.AvatarImage
import com.example.loveletter.util.joingame.JoinGame
import com.example.loveletter.util.user.HandleUser

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JoinGameScreen(navController: NavHostController) {

    var roomCode by remember {
        mutableStateOf("")
    }
    var playerNickname by remember {
        mutableStateOf("")
    }
    var playerChar by remember {
        mutableStateOf(0)
    }
    val roomFound = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Surface() {


        Column(Modifier.fillMaxSize()) {

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                value = roomCode,
                onValueChange = { newValue ->
                    roomCode = newValue
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words
                ),
                singleLine = true,
                label = {
                    Text(stringResource(R.string.enter_room_code))
                },
                trailingIcon = {
                    IconButton(onClick = {
                        roomFound.value = JoinGame.checkGame(roomCode = roomCode,
                            roomFound = roomFound,
                            context = context)
                    }) {
                        Icon(
                            Icons.Sharp.Search,
                            null
                        )
                    }
                }
            )
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                value = playerNickname,
                onValueChange = { newValue ->
                    playerNickname = newValue
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words
                ),
                singleLine = true,
                label = {
                    Text(stringResource(R.string.enter_player_nickname))
                }
            )

            val icons = listOf(
                R.drawable.bluechar,
                R.drawable.greenchar,
                R.drawable.goldchar,
                R.drawable.pinkchar,
                R.drawable.purplechar,
                R.drawable.redchar
            )

            var selectedIndex by remember {
                mutableStateOf(-1)
            }
            LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 100.dp)) {
                itemsIndexed(icons) { index, icon ->
                    println(icon)
                    AvatarImage(
                        modifier = Modifier.selectable(
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                                println("selectedIndex: $selectedIndex")
                                playerChar = index
                                Log.d(TAG, "playerChar: $playerChar")
                            }
                        ),
                        icon = icon,
                        background = if (selectedIndex == index) Color.Red else Color.Transparent
                    )
                }
            }
            OutlinedButton(onClick = {
                JoinGame.joinGame(roomCode, HandleUser.createPlayer(playerChar, playerNickname))
            }) {
                Text(stringResource(R.string.join_game))
            }
        }
    }
}