package com.gorteamapps.tetris.models

import com.gorteamapps.tetris.helpers.array2dOfBytes

class Frame(private val width: Int) {
    private val data: ArrayList<ByteArray> = ArrayList()

    fun addRow(byteStr: String) : Frame {
        val row = ByteArray(byteStr.length)

        for (i in byteStr.indices) {
            row[i] = byteStr[i].toByte()
        }

        data.add(row)
        return this
    }

    fun as2dByteArray() : Array<ByteArray> {
        val arr = array2dOfBytes(data.size, width)
        return data.toArray(arr)
    }
}