package com.example.hangman

sealed class PlayState {
    class Running(val lettersUsed: String,
                  val underscoreWord: String,
                  val drawable: Int) : PlayState()
    class Lost(val wordToGuess: String) : PlayState()
    class Won(val wordToGuess: String) : PlayState()
}