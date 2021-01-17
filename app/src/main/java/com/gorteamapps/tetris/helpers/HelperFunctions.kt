package com.gorteamapps.tetris.helpers

fun array2dOfBytes(sizeOuter: Int, sizeInner: Int): Array<ByteArray> =
    Array(sizeOuter) { ByteArray(sizeInner) }