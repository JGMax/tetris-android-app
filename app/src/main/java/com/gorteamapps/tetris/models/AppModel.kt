package com.gorteamapps.tetris.models

import android.graphics.Point
import com.gorteamapps.tetris.constants.CellConstants
import com.gorteamapps.tetris.constants.FieldConstants
import com.gorteamapps.tetris.helpers.array2dOfBytes
import com.gorteamapps.tetris.storage.AppPreferences

class AppModel {
    var score = 0
    private var preferences: AppPreferences? = null

    var currentBlock: Block? = null
    var currentState: String = Statuses.AWAITING_START.name

    private var field: Array<ByteArray> = array2dOfBytes(
        FieldConstants.ROW_COUNT.value,
        FieldConstants.COLUMN_COUNT.value
    )

    fun setPreferences(preferences: AppPreferences?) {
        this.preferences = preferences
    }

    private fun setCellStatus(row: Int, column: Int, status: Byte?) {
        if (status != null) {
            field[row][column] = status
        }
    }

    fun getCellStatus(row: Int, column: Int): Byte? {
        return field[row][column]
    }

    fun isGameAwaitingStart() = currentState == Statuses.AWAITING_START.name

    fun isGameActive() = currentState == Statuses.ACTIVE.name

    fun isGameOver() = currentState == Statuses.OVER.name

    private fun boostScore() {
        score += 10
        if (score > preferences?.getHighScore() as Int) {
            preferences?.saveHighScore(score)
        }
    }

    private fun generateNextBlock() {
        currentBlock = Block.createBlock()
    }

    private fun validTranslation(position: Point, shape: Array<ByteArray>): Boolean {
        return if (position.x < 0 || position.y < 0) {
            false
        } else if (position.x + shape[0].size > FieldConstants.COLUMN_COUNT.value) {
            false
        } else if (position.y + shape.size > FieldConstants.ROW_COUNT.value) {
            false
        } else {
            for (i in shape.indices) {
                for (j in shape[i].indices) {
                    val x = position.x + j
                    val y = position.y + i
                    if (CellConstants.EMPTY.value != shape[i][j] &&
                        CellConstants.EMPTY.value != field[y][x]) {
                       return false
                    }
                }
            }
            true
        }
    }

    enum class Statuses {
        AWAITING_START,ACTIVE, INACTIVE, OVER
    }

    enum class Motions {
        LEFT, RIGHT, DOWN, ROTATE
    }
}