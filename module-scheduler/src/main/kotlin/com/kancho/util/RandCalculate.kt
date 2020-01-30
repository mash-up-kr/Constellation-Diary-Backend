package com.kancho.util

import java.util.*


const val START_INDEX: Int = 0


fun randIndex(size: Int):
        Int = randIndex(START_INDEX, size)

fun randIndex(from: Int, to: Int): Int {
    val rand = Random()
    rand.setSeed(System.currentTimeMillis())
    return rand.nextInt(to - from) + from
}