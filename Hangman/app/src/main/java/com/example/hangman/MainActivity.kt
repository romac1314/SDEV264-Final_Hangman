
package com.example.hangman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val play: Button = findViewById(R.id.play)
        val help: Button = findViewById(R.id.help)
        val preferences: Button = findViewById(R.id.preferences)


        play.setOnClickListener{
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        help.setOnClickListener{
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }


        preferences.setOnClickListener{
            val intent = Intent(this, PreferencesActivity::class.java)
            startActivity(intent)
        }

    }
}