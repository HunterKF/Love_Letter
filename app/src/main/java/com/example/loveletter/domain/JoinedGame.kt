package com.example.loveletter.domain

data class JoinedGame(
    val roomCode: String,
    val roomNickname: String,
    val myTurn: Boolean,
    var ready: Boolean
) {
    constructor() : this ("", "", false, false)
}
