package com.example.hangman

import kotlin.random.Random

class GameplayCode {

    private var lettersUsed: String = ""
    private lateinit var underscoreWord: String
    private lateinit var wordToGuess: String
    private val maxTries = 8
    private var currentTries = 0
    private var drawable: Int = R.drawable.hm

    fun startNewGame(): PlayState {
        lettersUsed = ""
        currentTries = 0
        drawable = R.drawable.hm8
        val randomIndex = Random.nextInt(0, GameConstants.words.size)
        wordToGuess = GameConstants.words[randomIndex]
        generateUnderscores(wordToGuess)
        return getPlayState()
    }

    fun generateUnderscores(word: String) {
        val sb = StringBuilder()
        word.forEach { char ->
            if (char == '/') {
                sb.append('/')
            } else {
                sb.append("_")
            }
        }
        underscoreWord = sb.toString()
    }

    fun play(letter: Char): PlayState {
        if (lettersUsed.contains(letter)) {
            return PlayState.Running(lettersUsed, underscoreWord, drawable)
        }

        lettersUsed += letter
        val indexes = mutableListOf<Int>()

        wordToGuess.forEachIndexed { index, char ->
            if (char.equals(letter, true)) {
                indexes.add(index)
            }
        }

        var finalUnderscoreWord = "" + underscoreWord // _ _ _ _ _ _ _ -> E _ _ _ _ _ _
        indexes.forEach { index ->
            val sb = StringBuilder(finalUnderscoreWord).also { it.setCharAt(index, letter) }
            finalUnderscoreWord = sb.toString()
        }

        if (indexes.isEmpty()) {
            currentTries++
        }

        underscoreWord = finalUnderscoreWord
        return getPlayState()
    }

    private fun getHangmanDrawable(): Int {
        return when (currentTries) {
            0 -> R.drawable.hm
            1 -> R.drawable.hm1
            2 -> R.drawable.hm2
            3 -> R.drawable.hm3
            4 -> R.drawable.hm4
            5 -> R.drawable.hm5
            6 -> R.drawable.hm6
            7 -> R.drawable.hm7
            8 -> R.drawable.hm8
            else -> R.drawable.hm8
        }
    }

    private fun getPlayState(): PlayState {
        if (underscoreWord.equals(wordToGuess, true)) {
            return PlayState.Won(wordToGuess)
        }

        if (currentTries == maxTries) {
            return PlayState.Lost(wordToGuess)
        }

        drawable = getHangmanDrawable()
        return PlayState.Running(lettersUsed, underscoreWord, drawable)
    }
}