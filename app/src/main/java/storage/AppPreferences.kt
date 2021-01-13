package storage

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(ctx: Context) {
    private val data: SharedPreferences =
        ctx.getSharedPreferences("TETRIS_APP_PREFERENCES", Context.MODE_PRIVATE)

    fun saveHighScore(highScore: Int) {
        data.edit().putInt("HIGH_SCORE", highScore).apply()
    }

    fun getHighScore() : Int {
        return data.getInt("HIGH_SCORE", 0)
    }

    fun clearHighScore() {
        data.edit().putInt("HIGH_SCORE", 0).apply()
    }
}