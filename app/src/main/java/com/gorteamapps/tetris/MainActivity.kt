package com.gorteamapps.tetris

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import storage.AppPreferences
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private val tvHighScore : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide();

        val btnNewGame : Button = findViewById(R.id.btn_new_game)
        val btnResetScore : Button = findViewById(R.id.btn_reset_score)
        val btnExit : Button = findViewById(R.id.btn_exit)

        btnNewGame.setOnClickListener(this::btnNewGameClick)
        btnResetScore.setOnClickListener(this::btnResetScoreClick)
        btnExit.setOnClickListener(this::btnExitClick)
    }

    private fun btnNewGameClick(view : View) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("SetTextI18n")
    private fun btnResetScoreClick(view : View) {
        val preferences = AppPreferences(this)
        preferences.clearHighScore()
        tvHighScore?.text = "HIGH SCORE ${preferences.getHighScore()}"
        Snackbar.make(view, "Score successfully reset", Snackbar.LENGTH_SHORT).show()
    }

    private fun btnExitClick(view : View) {
        exitProcess(0)
    }
}